/**
 * @author Group 14
 */
package insuranceapp.groupproject_cosc2440_insuranceapp.Controllers.PolicyOwner;

import insuranceapp.groupproject_cosc2440_insuranceapp.Models.PolicyHolder;
import insuranceapp.groupproject_cosc2440_insuranceapp.Models.PolicyOwnerModel;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class PolicyHolderCellController implements Initializable {
    public Label policyHolderPhoneNumLbl;
    public Label policyHolderEmailLbl;
    public Label policyHolderIdLbl;
    public Label policyHolderNameLbl;
    public Button updateInfoBtn;
    public Button deleteBtn;

    public PolicyHolder policyHolder;
    public Button showDependentList;

    public PolicyHolderCellController(PolicyHolder policyHolder) {
        this.policyHolder = policyHolder;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateCellInfo(policyHolder);
        showDependentList.setOnAction(event -> {
            PolicyOwnerModel.getInstance().setCurrentSelectedPolicyHolder(this.policyHolder);
            PolicyOwnerModel.getInstance().getPolicyOwnerViewFactory().showDependentListView();
        });
        updateInfoBtn.setOnAction(event -> {
            PolicyOwnerModel.getInstance().setCurrentSelectedPolicyHolder(this.policyHolder);
            PolicyOwnerModel.getInstance().getPolicyOwnerViewFactory().showUpdatePolicyHolderView();
        });
        deleteBtn.setOnAction(event -> {
            if (PolicyOwnerModel.getInstance().deletePolicyHolder(policyHolder)) {
                PolicyOwnerModel.getInstance().getDependents().removeIf(dependent -> dependent.getPolicyHolderId().equals(policyHolder.getId()));
                PolicyOwnerModel.getInstance().getPolicyHolders().remove(policyHolder);
                PolicyOwnerModel.getInstance().updatePolicyHolderView();
                PolicyOwnerModel.getInstance().getViewFactory().getNotificationMsg().set("Delete policy holder Success");
                PolicyOwnerModel.getInstance().getDatabaseDriver().recordActivityHistory("DELETE POLICY HOLDER " + policyHolder.getId(), PolicyOwnerModel.getInstance().getPolicyOwner().getId());
            }
        });
    }

    public void updateCellInfo(PolicyHolder policyHolder) {
        policyHolderIdLbl.textProperty().bind(policyHolder.idProperty());
        policyHolderNameLbl.textProperty().bind(policyHolder.fullNameProperty());
        policyHolderEmailLbl.textProperty().bind(policyHolder.insuranceCardProperty());
        policyHolderPhoneNumLbl.textProperty().bind(policyHolder.phoneNumberProperty());
    }
}
