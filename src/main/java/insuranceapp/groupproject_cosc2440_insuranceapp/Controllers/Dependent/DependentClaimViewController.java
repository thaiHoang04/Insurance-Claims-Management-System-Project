/**
 * @author Group 14
 */
package insuranceapp.groupproject_cosc2440_insuranceapp.Controllers.Dependent;

import insuranceapp.groupproject_cosc2440_insuranceapp.Models.Claim;
import insuranceapp.groupproject_cosc2440_insuranceapp.Models.DependentModel;
import insuranceapp.groupproject_cosc2440_insuranceapp.Models.PolicyOwnerModel;
import insuranceapp.groupproject_cosc2440_insuranceapp.Views.ClaimCellForDependentFactory;
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

public class DependentClaimViewController implements Initializable {
    public Label dependentNameLbl;
    public Label totalClaimLbl;
    public TextField searchClaimTxtField;
    public Button searchBtn;
    public MenuItem newOpt;
    public MenuItem pendingOpt;
    public MenuItem approveOpt;
    public MenuItem requiredOpt;
    public MenuItem updatedOpt;
    public MenuItem declinedOpt;
    public ListView<Claim> claim_listview;
    public StackPane errorContainer;
    public Label errorLbl;
    public MenuItem viewInfoOpt;

    private Executor executor;
    private String filterStatus;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initData();
        dependentNameLbl.textProperty().bind(DependentModel.getInstance().getDependent().fullNameProperty());
        totalClaimLbl.setText(String.valueOf(DependentModel.getInstance().getClaims().size()));
        claim_listview.setItems(DependentModel.getInstance().getClaims());
        claim_listview.setCellFactory(e -> new ClaimCellForDependentFactory());
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
        viewInfoOpt.setOnAction(event -> {
            DependentModel.getInstance().getDependentViewFactory().showViewDependentInfoWindow();
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

    public void initData() {
        if (DependentModel.getInstance().getClaims().isEmpty()) {
            DependentModel.getInstance().setClaims();
        }
    }

    public void searchClaim(String searchString, String filterStatus) {
        Task<List<Claim>> claimSearchTask = new Task<List<Claim>>() {
            @Override
            public List<Claim> call() throws Exception {
                return PolicyOwnerModel.getInstance().getDatabaseDriver().getListClaimByInsuredPersonId(searchString, filterStatus ,DependentModel.getInstance().getDependent().getId());
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
                return PolicyOwnerModel.getInstance().getDatabaseDriver().getListClaimByStatusWithInsuredPersonID(status, DependentModel.getInstance().getDependent().getId());
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
