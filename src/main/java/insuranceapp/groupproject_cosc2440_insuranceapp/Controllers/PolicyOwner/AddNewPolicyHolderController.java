/**
 * @author Group 14
 */
package insuranceapp.groupproject_cosc2440_insuranceapp.Controllers.PolicyOwner;

import insuranceapp.groupproject_cosc2440_insuranceapp.Models.InsuranceCard;
import insuranceapp.groupproject_cosc2440_insuranceapp.Models.PolicyHolder;
import insuranceapp.groupproject_cosc2440_insuranceapp.Models.PolicyOwnerModel;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AddNewPolicyHolderController implements Initializable {
    public TextField fullNameTxtField;
    public TextField usernameTxtField;
    public TextField pwdTxtField;
    public TextField phoneNumTxtField;
    public TextField emailTxtField;
    public TextField addressTxtField;
    public Button saveButton;
    public Label errorLbl;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        saveButton.setOnAction(actionEvent -> {
            // Save the new policy holder
            if (checkEnoughInfo()) {
                errorLbl.setVisible(false);
                // Save the new policy holder
                String id = PolicyOwnerModel.getInstance().generateIdForPolicyHolder();
                InsuranceCard insuranceCard = PolicyOwnerModel.getInstance().generateInsuranceCard(id);
                PolicyHolder policyHolder = new PolicyHolder(id, fullNameTxtField.getText(), insuranceCard.getCardNumber(), phoneNumTxtField.getText(), emailTxtField.getText(), addressTxtField.getText(), PolicyOwnerModel.getInstance().getPolicyOwner().getId());
                if (PolicyOwnerModel.getInstance().getDatabaseDriver().insertNewPolicyHolder(policyHolder) && PolicyOwnerModel.getInstance().getDatabaseDriver().insertInsuranceCard(insuranceCard) && PolicyOwnerModel.getInstance().getDatabaseDriver().insertAccountData(usernameTxtField.getText(), pwdTxtField.getText(), id, "PH")) {
                    PolicyOwnerModel.getInstance().addPolicyHolders(policyHolder);
                    PolicyOwnerModel.getInstance().getPolicyOwnerViewFactory().updateBeneficiariesView();
                    PolicyOwnerModel.getInstance().getDatabaseDriver().recordActivityHistory("ADD NEW POLICY HOLDER " + policyHolder.getId(), PolicyOwnerModel.getInstance().getPolicyOwner().getId());
                    PolicyOwnerModel.getInstance().getPolicyOwnerViewFactory().closeCurrentSubStage();
                }
            } else {
                errorLbl.setVisible(true);
            }
        });

    }

    public boolean checkEnoughInfo() {
        return !(fullNameTxtField.getText().isEmpty()) && !(pwdTxtField.getText().isEmpty()) && !(usernameTxtField.getText().isEmpty()) &&!(phoneNumTxtField.getText().isEmpty()) && !(emailTxtField.getText().isEmpty()) && !(addressTxtField.getText().isEmpty());
    }


}
