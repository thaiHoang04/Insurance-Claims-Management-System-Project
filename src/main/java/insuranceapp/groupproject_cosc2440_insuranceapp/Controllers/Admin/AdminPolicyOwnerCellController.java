/**
 * @author Group 14
 */
package insuranceapp.groupproject_cosc2440_insuranceapp.Controllers.Admin;

import insuranceapp.groupproject_cosc2440_insuranceapp.Models.AdminModel;
import insuranceapp.groupproject_cosc2440_insuranceapp.Models.PolicyOwner;
import insuranceapp.groupproject_cosc2440_insuranceapp.Models.PolicyOwnerModel;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminPolicyOwnerCellController implements Initializable {
    public Label policyOwnerIdLbl;
    public Label policyOwnerNameLbl;
    public Label policyOwnerFeeLbl;
    public Button showPolicyHolderList;
    public Button updateInfoBtn;
    public Button deleteBtn;


    private PolicyOwner policyOwner;

    public AdminPolicyOwnerCellController(PolicyOwner policyOwner) {
        this.policyOwner = policyOwner;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        policyOwnerIdLbl.setText(String.valueOf(policyOwner.getId()));
        policyOwnerNameLbl.setText(policyOwner.getName());
        policyOwnerFeeLbl.setText(String.valueOf(policyOwner.getFee()));
        showPolicyHolderList.setOnAction(event -> {
            // Show policy holder list
            PolicyOwnerModel.getInstance().evaluatePolicyOwnerCred(policyOwner.getId(), policyOwner.getName(), Double.toString(policyOwner.getFee()));
            PolicyOwnerModel.getInstance().resetDependents();
            PolicyOwnerModel.getInstance().resetPolicyHolders();
            PolicyOwnerModel.getInstance().resetPolicyOwnerModel();
            PolicyOwnerModel.getInstance().getPolicyOwnerViewFactory().showPolicyHolderView();
            PolicyOwnerModel.getInstance().getPolicyOwnerViewFactory().resetViewFactory();
        });
        updateInfoBtn.setOnAction(event -> {
            // Update policy owner info
            PolicyOwnerModel.getInstance().evaluatePolicyOwnerCred(policyOwner.getId(), policyOwner.getName(), Double.toString(policyOwner.getFee()));
            PolicyOwnerModel.getInstance().getPolicyOwnerViewFactory().showUpdateInfoWindow();
        });
        deleteBtn.setOnAction(event -> {
            // Delete policy owner
            if (AdminModel.getInstance().getDatabaseDriver().deletePolicyOwnerById(policyOwner.getId())
                    && AdminModel.getInstance().getDatabaseDriver().deleteAccountData(policyOwner.getId())
                    && AdminModel.getInstance().getDatabaseDriver().deleteClaimByPolicyOwnerId(policyOwner.getId())
                    && AdminModel.getInstance().getDatabaseDriver().deleteInsuranceCardByPolicyOwnerId(policyOwner.getId())
                    && AdminModel.getInstance().getDatabaseDriver().deleteDependentByPolicyOwnerId(policyOwner.getId())
                    && AdminModel.getInstance().getDatabaseDriver().deletePolicyHolderByPolicyOwnerID(policyOwner.getId())){
                AdminModel.getInstance().getDatabaseDriver().deleteAccountData(policyOwner.getId());
                AdminModel.getInstance().getPolicyOwners().remove(policyOwner);
                AdminModel.getInstance().getAdminViewFactory().updatePolicyOwnerView();
            }
        });
    }
}
