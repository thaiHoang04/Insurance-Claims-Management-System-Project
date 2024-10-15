/**
 * @author Group 14
 */
package insuranceapp.groupproject_cosc2440_insuranceapp.Controllers.PolicyOwner;

import insuranceapp.groupproject_cosc2440_insuranceapp.Models.Dependent;
import insuranceapp.groupproject_cosc2440_insuranceapp.Models.PolicyHolderModel;
import insuranceapp.groupproject_cosc2440_insuranceapp.Models.PolicyOwnerModel;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class DependentCellController implements Initializable {
    public Label dependentIdLbl;
    public Label dependentNameLbl;
    public Label dependentEmailLbl;
    public Label dependentPhoneNumLbl;
    public Button updateInfoBtn;
    public Button deleteBtn;
    private final Dependent dependent;

    public DependentCellController(Dependent dependent) {
        this.dependent = dependent;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateCellInfo(dependent);
        updateInfoBtn.setOnAction(event -> {
            PolicyOwnerModel.getInstance().getPolicyOwnerViewFactory().showUpdateDependentView(dependent);
        });
        if (!PolicyHolderModel.getInstance().getDependents().isEmpty()) {
            deleteBtn.setDisable(true);
            deleteBtn.setVisible(false);
        }
        deleteBtn.setOnAction(event -> {
            PolicyOwnerModel.getInstance().getDatabaseDriver().deleteDependent(dependent.getId());
            PolicyOwnerModel.getInstance().getDatabaseDriver().deleteClaimByCustomerId(dependent.getId());
            PolicyOwnerModel.getInstance().getDatabaseDriver().deleteAccountData(dependent.getId());
            PolicyOwnerModel.getInstance().getDependentsOfCurrentPolicyHolder().remove(dependent);
            PolicyOwnerModel.getInstance().getDependents().remove(dependent);
            PolicyOwnerModel.getInstance().getDatabaseDriver().recordActivityHistory("DELETE DEPENDENT " + dependent.getId(), PolicyOwnerModel.getInstance().getPolicyOwner().getId());
        });
    }

    public void updateCellInfo(Dependent dependent) {
        dependentIdLbl.textProperty().bind(dependent.idProperty());
        dependentNameLbl.textProperty().bind(dependent.fullNameProperty());
        dependentEmailLbl.textProperty().bind(dependent.insuranceCardProperty());
        dependentPhoneNumLbl.textProperty().bind(dependent.phoneNumberProperty());
    }
}
