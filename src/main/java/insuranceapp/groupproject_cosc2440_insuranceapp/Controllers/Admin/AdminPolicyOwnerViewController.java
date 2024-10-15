/**
 * @author Group 14
 */
package insuranceapp.groupproject_cosc2440_insuranceapp.Controllers.Admin;

import insuranceapp.groupproject_cosc2440_insuranceapp.Models.AdminModel;
import insuranceapp.groupproject_cosc2440_insuranceapp.Models.PolicyOwner;
import insuranceapp.groupproject_cosc2440_insuranceapp.Views.PolicyOwnerCellFactory;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AdminPolicyOwnerViewController implements Initializable {
    public Label numberOfPolicyOwner;
    public ListView<PolicyOwner> policyOwner_listview;
    public TextField searchBar;
    public Label noPolicyHolderLbl;
    public Button addNewPolicyOwner;

    private Executor executor;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initData();
        AdminModel.getInstance().getAdminViewFactory().setAdminPolicyOwnerViewController(this);
        numberOfPolicyOwner.setText(String.valueOf(AdminModel.getInstance().getPolicyOwners().size()));
        policyOwner_listview.setItems(AdminModel.getInstance().getPolicyOwners());
        policyOwner_listview.setCellFactory(e -> new PolicyOwnerCellFactory());
        addNewPolicyOwner.setOnAction(e -> {
            AdminModel.getInstance().getAdminViewFactory().showAddNewPolicyOwner();
        });
        searchBar.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String string, String t1) {
                if (t1.isEmpty()) {
                    policyOwner_listview.getItems().setAll(AdminModel.getInstance().getDatabaseDriver().getListPolicyOwnerForAdmin(""));
                    noPolicyHolderLbl.setVisible(false);
                } else {
                    searchOwner(t1);
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
        if (AdminModel.getInstance().getPolicyOwners().isEmpty()) {
            AdminModel.getInstance().setPolicyOwners();
        }
    }

    public void searchOwner(String searchString) {
        Task<List<PolicyOwner>> policyOwnerSearchTask = new Task<List<PolicyOwner>>() {
            @Override
            public List<PolicyOwner> call() throws Exception {
                return AdminModel.getInstance().getDatabaseDriver().getListPolicyOwnerForAdmin(searchString);
            }
        };

        policyOwnerSearchTask.setOnFailed(e -> {
            policyOwnerSearchTask.getException().printStackTrace();
        });

        policyOwnerSearchTask.setOnSucceeded(e -> {
            if (policyOwnerSearchTask.getValue().isEmpty()) {
                noPolicyHolderLbl.setVisible(true);
            } else {
                noPolicyHolderLbl.setVisible(false);
            }
            policyOwner_listview.getItems().setAll(policyOwnerSearchTask.getValue());
        });

        executor.execute(policyOwnerSearchTask);
    }
}
