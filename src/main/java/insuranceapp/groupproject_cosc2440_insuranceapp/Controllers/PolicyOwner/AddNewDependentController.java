/**
 * @author Group 14
 */
package insuranceapp.groupproject_cosc2440_insuranceapp.Controllers.PolicyOwner;

import insuranceapp.groupproject_cosc2440_insuranceapp.Models.Dependent;
import insuranceapp.groupproject_cosc2440_insuranceapp.Models.PolicyHolder;
import insuranceapp.groupproject_cosc2440_insuranceapp.Models.PolicyHolderModel;
import insuranceapp.groupproject_cosc2440_insuranceapp.Models.PolicyOwnerModel;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AddNewDependentController implements Initializable {
    public TextField nameTxtField;
    public TextField pwdTxtField;
    public TextField usernameTxtField;
    public TextField phoneNumTxtField;
    public TextField emailTxtField;
    public TextField addressTxtField;
    public Button saveBtn;
    public Label errorLbl;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        saveBtn.setOnAction(event -> {
            if (checkEnoughInfo()) {
                errorLbl.setVisible(false);
                String id = PolicyOwnerModel.getInstance().generateIdForDependent();
                Dependent dependent = new Dependent(id, nameTxtField.getText(), PolicyOwnerModel.getInstance().getCurrentSelectedPolicyHolder().getInsuranceCard() ,phoneNumTxtField.getText(), emailTxtField.getText(), addressTxtField.getText(), PolicyOwnerModel.getInstance().getCurrentSelectedPolicyHolder().getId(), PolicyOwnerModel.getInstance().getCurrentSelectedPolicyHolder().getPolicyOwnerId());
                if (PolicyOwnerModel.getInstance().getDatabaseDriver().insertNewDependent(dependent) && PolicyOwnerModel.getInstance().getDatabaseDriver().insertAccountData(usernameTxtField.getText(), pwdTxtField.getText(), id, "D")){
                    PolicyOwnerModel.getInstance().getPolicyOwnerViewFactory().updateBeneficiariesView();
                    if (!PolicyHolderModel.getInstance().getDependents().isEmpty()) {
                        PolicyHolderModel.getInstance().addDependent(dependent);
                        PolicyHolderModel.getInstance().getDatabaseDriver().recordActivityHistory("ADD NEW DEPENDENT " + dependent.getId(), PolicyHolderModel.getInstance().getPolicyHolder().getId());
                        PolicyHolderModel.getInstance().updateDependentView();
                    } else {
                        PolicyOwnerModel.getInstance().addDependents(dependent);
                        PolicyOwnerModel.getInstance().addDependentsOfCurrentPolicyHolder(dependent);
                        PolicyOwnerModel.getInstance().getDatabaseDriver().recordActivityHistory("ADD NEW DEPENDENT " + dependent.getId() + " FOR " + PolicyOwnerModel.getInstance().getCurrentSelectedPolicyHolder().getId(), PolicyOwnerModel.getInstance().getPolicyOwner().getId());
                    }

                    PolicyOwnerModel.getInstance().getPolicyOwnerViewFactory().closeCurrent2ndSubStage();

                } else {
                    errorLbl.setVisible(true);
                }
            } else {
                errorLbl.setVisible(true);
            }
        });
    }

    public boolean checkEnoughInfo() {
        return !(nameTxtField.getText().isEmpty()) && !(pwdTxtField.getText().isEmpty()) && !(usernameTxtField.getText().isEmpty()) &&!(phoneNumTxtField.getText().isEmpty()) && !(emailTxtField.getText().isEmpty()) && !(addressTxtField.getText().isEmpty());
    }
}
