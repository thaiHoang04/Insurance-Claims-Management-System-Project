/**
 * @author Group 14
 */
package insuranceapp.groupproject_cosc2440_insuranceapp.Controllers.PolicyOwner;

import insuranceapp.groupproject_cosc2440_insuranceapp.Models.Claim;
import insuranceapp.groupproject_cosc2440_insuranceapp.Models.DependentModel;
import insuranceapp.groupproject_cosc2440_insuranceapp.Models.PolicyOwnerModel;
import insuranceapp.groupproject_cosc2440_insuranceapp.Views.ClaimCellForPolicyOwner;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class PolicyOwnerClaimViewController implements Initializable {
    public ListView claim_listview;
    public Button createNewClaimBtn;
    public Label totalClaimLbl;
    public TextField searchClaimTxtField;
    public Button searchBtn;
    public Label policyOwnerNameLbl;
    public Label totalPendingClaimLbl;
    public MenuItem newOpt;
    public MenuItem pendingOpt;
    public MenuItem approveOpt;
    public MenuItem requiredOpt;
    public MenuItem declinedOpt;
    public MenuItem updatedOpt;
    public StackPane errorContainer;
    public Label errorLbl;
    public MenuItem updateInfoOpt;

    private Executor executor;
    private String filterStatus;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initData();
        policyOwnerNameLbl.textProperty().bind(PolicyOwnerModel.getInstance().getPolicyOwner().nameProperty());
        PolicyOwnerModel.getInstance().getPolicyOwnerViewFactory().setPolicyOwnerClaimViewController(this);
        PolicyOwnerModel.getInstance().getPolicyOwnerViewFactory().updateClaimView();
        claim_listview.setItems(PolicyOwnerModel.getInstance().getClaims());
        claim_listview.setCellFactory(e -> new ClaimCellForPolicyOwner());
        createNewClaimBtn.setOnAction(event -> {
            PolicyOwnerModel.getInstance().getPolicyOwnerViewFactory().showAddNewClaimWindow();
        });
        newOpt.setOnAction(event -> {
            filterStatus = "NEW";
            filterClaim("NEW");
        });
        pendingOpt.setOnAction(event -> {
            filterStatus = "PENDING";
            filterClaim("PENDING");
        });
        approveOpt.setOnAction(event -> {
            filterStatus = "APPROVED";
            filterClaim("APPROVED");
        });
        requiredOpt.setOnAction(event -> {
            filterStatus = "REQUIRED";
            filterClaim("REQUIRED");
        });
        declinedOpt.setOnAction(event -> {
            filterStatus = "DECLINED";
            filterClaim("DECLINED");
        });
        updatedOpt.setOnAction(event -> {
            filterStatus = "UPDATED";
            filterClaim("UPDATED");
        });
        updateInfoOpt.setOnAction(event -> {
            PolicyOwnerModel.getInstance().getPolicyOwnerViewFactory().showUpdateInfoWindow();
        });
        searchClaimTxtField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String string, String t1) {
                if (!t1.isEmpty()) {
                    searchClaim(t1, filterStatus);
                } else {
                    initData();
                    errorLbl.setText("");
                }
            }
        });
        executor = Executors.newCachedThreadPool(r -> {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t;
        });

    }

    public void updateTotalClaim(String totalClaim) {
        totalClaimLbl.setText(totalClaim);
    }

    public void updateTotalPendingClaim(String totalClaim) {
        totalPendingClaimLbl.setText(totalClaim);
    }

    public void initData() {
        if (PolicyOwnerModel.getInstance().getClaims().isEmpty()) {
            PolicyOwnerModel.getInstance().setClaims();
        }
    }
    public void searchClaim(String searchString, String filterStatus) {
        Task<List<Claim>> claimSearchTask = new Task<List<Claim>>() {
            @Override
            public List<Claim> call() throws Exception {
                return PolicyOwnerModel.getInstance().getDatabaseDriver().getListClaimByPolicyOwnerId(searchString, filterStatus ,PolicyOwnerModel.getInstance().getPolicyOwner().getId());
            }
        };

        claimSearchTask.setOnFailed(e -> {
            claimSearchTask.getException().printStackTrace();
        });

        claimSearchTask.setOnSucceeded(e -> {
                    if (claimSearchTask.getValue().isEmpty()) {
                        errorLbl.setText("No claim found");
                        errorContainer.setDisable(false);
                    } else {
                        errorLbl.setText("");
                        errorContainer.setDisable(true);
                    }
                    claim_listview.getItems().setAll(claimSearchTask.getValue());
        });

        executor.execute(claimSearchTask);
    }

    public void filterClaim(String status) {
        Task<List<Claim>> claimFilterTask = new Task<List<Claim>>() {
            @Override
            public List<Claim> call() throws Exception {
                return PolicyOwnerModel.getInstance().getDatabaseDriver().getListClaimByStatusWithPolicyOwnerID(status, DependentModel.getInstance().getDependent().getId());
            }
        };
        claimFilterTask.setOnFailed(e -> {
            claimFilterTask.getException().printStackTrace();
        });

        claimFilterTask.setOnSucceeded(e -> {
            if (claimFilterTask.getValue().isEmpty()) {
                errorLbl.setText("No claim found");
                errorContainer.setDisable(false);
            } else {
                errorLbl.setText("");
                errorContainer.setDisable(true);
            }
            claim_listview.getItems().setAll(claimFilterTask.getValue());
        });

        executor.execute(claimFilterTask);
    }
}
