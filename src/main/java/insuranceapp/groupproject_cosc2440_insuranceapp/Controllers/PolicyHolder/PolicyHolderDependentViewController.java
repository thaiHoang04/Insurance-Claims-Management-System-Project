/**
 * @author Group 14
 */
package insuranceapp.groupproject_cosc2440_insuranceapp.Controllers.PolicyHolder;

import insuranceapp.groupproject_cosc2440_insuranceapp.Models.*;
import insuranceapp.groupproject_cosc2440_insuranceapp.Views.DependentCellFactoryForPolicyOwner;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class PolicyHolderDependentViewController implements Initializable {
    public MenuItem updateInfoOpt;
    public Label policyHolderNameLbl;
    public Label numberOfDependent;
    public ListView<Dependent> dependent_listview;
    public TextField searchBar;
    public Label noDependentLbl;

    private Executor executor;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initData();
        policyHolderNameLbl.setText(PolicyHolderModel.getInstance().getPolicyHolder().getFullName());
        dependent_listview.setItems(PolicyHolderModel.getInstance().getDependents());
        dependent_listview.setCellFactory(e -> new DependentCellFactoryForPolicyOwner());
        numberOfDependent.setText(String.valueOf(PolicyHolderModel.getInstance().getDependents().size()));
        int numOfDependents = PolicyHolderModel.getInstance().getDependents().size();
        noDependentLbl.setVisible(numOfDependents == 0);
        updateInfoOpt.setOnAction(event -> {
            PolicyHolderModel.getInstance().getPolicyHolderViewFactory().showUpdatePolicyHolderInfoWindow();
        });
        searchBar.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String string, String t1) {
                if (!t1.isEmpty()) {
                    searchDependent(t1);
                } else {
                    initData();
                    noDependentLbl.setText("");
                }
            }
        });
        executor = Executors.newCachedThreadPool(r -> {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t;
        });
    }

    public void initData() {
        if (PolicyHolderModel.getInstance().getDependents().isEmpty()) {
            PolicyHolderModel.getInstance().setDependents();
        }
    }

    public void searchDependent(String searchString) {
        Task<List<Dependent>> dependentSearchTask = new Task<List<Dependent>>() {
            @Override
            public List<Dependent> call() throws Exception {
                return AdminModel.getInstance().getDatabaseDriver().searchListDependentById(searchString, PolicyHolderModel.getInstance().getPolicyHolder().getInsuranceCard());
            }
        };

        dependentSearchTask.setOnFailed(e -> {
            dependentSearchTask.getException().printStackTrace();
        });

        dependentSearchTask.setOnSucceeded(e -> {
            if (dependentSearchTask.getValue().isEmpty()) {
                noDependentLbl.setVisible(true);
            } else {
                noDependentLbl.setVisible(false);
            }
            dependent_listview.getItems().setAll(dependentSearchTask.getValue());
        });

        executor.execute(dependentSearchTask);
    }
}
