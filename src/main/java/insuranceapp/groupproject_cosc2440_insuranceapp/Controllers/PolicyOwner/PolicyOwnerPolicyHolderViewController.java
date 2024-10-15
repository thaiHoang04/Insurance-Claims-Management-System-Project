/**
 * @author Group 14
 */
package insuranceapp.groupproject_cosc2440_insuranceapp.Controllers.PolicyOwner;

import insuranceapp.groupproject_cosc2440_insuranceapp.Models.AdminModel;
import insuranceapp.groupproject_cosc2440_insuranceapp.Models.PolicyHolder;
import insuranceapp.groupproject_cosc2440_insuranceapp.Models.PolicyOwner;
import insuranceapp.groupproject_cosc2440_insuranceapp.Models.PolicyOwnerModel;
import insuranceapp.groupproject_cosc2440_insuranceapp.Views.PolicyHolderCellFactory;
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

public class  PolicyOwnerPolicyHolderViewController implements Initializable {
    public ListView policyHolder_listview;
    public Button addNewPolicyHolderBtn;
    public Label numberOfDependents;
    public Label numberOfPolicyHolder;
    public Label annualFeeLbl;
    public Label policyOwnerNameLbl;
    public MenuItem updateInfoOpt;
    public Label noPolicyHolderLbl;
    public TextField searchBar;

    private Executor executor;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initData();
        PolicyOwnerModel.getInstance().setPolicyOwnerPolicyHolderViewController(this);
        noPolicyHolderLbl.setVisible(false);
        policyOwnerNameLbl.textProperty().bind(PolicyOwnerModel.getInstance().getPolicyOwner().nameProperty());
        PolicyOwnerModel.getInstance().getPolicyOwnerViewFactory().setPolicyOwnerBeneficiariesViewController(this);
        PolicyOwnerModel.getInstance().getPolicyOwnerViewFactory().updateBeneficiariesView();
        policyHolder_listview.setItems(PolicyOwnerModel.getInstance().getPolicyHolders());
        policyHolder_listview.setCellFactory(e -> new PolicyHolderCellFactory());
        searchBar.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String string, String t1) {
                if (!t1.isEmpty()) {
                    searchPolicyHolder(t1);
                } else {
                    initData();
                    noPolicyHolderLbl.setVisible(false);
                }
            }
        });
        updateInfoOpt.setOnAction(event -> {
            PolicyOwnerModel.getInstance().getPolicyOwnerViewFactory().showUpdateInfoWindow();
        });
        addNewPolicyHolderBtn.setOnAction(event -> {
            PolicyOwnerModel.getInstance().getPolicyOwnerViewFactory().showAddNewPolicyHolderWindow();
        });
        executor = Executors.newCachedThreadPool(r -> {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t;
        });
    }

    public void updateTotalPolicyHolder(String numberOfPolicyHolder) {
        this.numberOfPolicyHolder.setText(numberOfPolicyHolder);
    }

    public void updateAnnualFee(Double totalAnnualFee) {
        annualFeeLbl.setText(Double.toString(totalAnnualFee));
    }

    public void initData() {
        if (PolicyOwnerModel.getInstance().getPolicyHolders().isEmpty()) {
            PolicyOwnerModel.getInstance().setPolicyHolders();
        }
        if (PolicyOwnerModel.getInstance().getDependents().isEmpty()) {
            PolicyOwnerModel.getInstance().setDependents();
        }
    }

    public void searchPolicyHolder(String searchString) {
        Task<List<PolicyHolder>> policyHolderSearchTask = new Task<List<PolicyHolder>>() {
            @Override
            public List<PolicyHolder> call() throws Exception {
                return AdminModel.getInstance().getDatabaseDriver().getListPolicyHolderById(searchString, PolicyOwnerModel.getInstance().getPolicyOwner().getId());
            }
        };

        policyHolderSearchTask.setOnFailed(e -> {
            policyHolderSearchTask.getException().printStackTrace();
        });

        policyHolderSearchTask.setOnSucceeded(e -> {
            if (policyHolderSearchTask.getValue().isEmpty()) {
                noPolicyHolderLbl.setVisible(true);
            } else {
                noPolicyHolderLbl.setVisible(false);
            }
            policyHolder_listview.getItems().setAll(policyHolderSearchTask.getValue());
        });

        executor.execute(policyHolderSearchTask);
    }
}
