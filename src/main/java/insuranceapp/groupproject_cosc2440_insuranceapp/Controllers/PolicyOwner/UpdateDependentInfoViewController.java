/**
 * @author Group 14
 */
package insuranceapp.groupproject_cosc2440_insuranceapp.Controllers.PolicyOwner;

import insuranceapp.groupproject_cosc2440_insuranceapp.Models.Dependent;
import insuranceapp.groupproject_cosc2440_insuranceapp.Models.PolicyHolderModel;
import insuranceapp.groupproject_cosc2440_insuranceapp.Models.PolicyOwnerModel;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class UpdateDependentInfoViewController implements Initializable {
    public TextField nameTxtField;
    public TextField pwdTextField;
    public TextField phoneTxtField;
    public TextField emailTxtField;
    public TextField addressTxtField;
    public Button updateBtn;
    private Dependent dependent;

    public UpdateDependentInfoViewController(Dependent dependent) {
        this.dependent = dependent;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ResultSet rs = PolicyOwnerModel.getInstance().getDatabaseDriver().getAccountDataById(dependent.getId());
        String username = null, password = null;
        try {
            if (rs.next()) {
                username = rs.getString("username");
                password = rs.getString("password");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        nameTxtField.setText(dependent.getFullName());
        pwdTextField.setText(password);
        phoneTxtField.setText(dependent.getPhoneNumber());
        emailTxtField.setText(dependent.getEmail());
        addressTxtField.setText(dependent.getAddress());
        String finalUsername = username;
        updateBtn.setOnAction(actionEvent -> {
            PolicyOwnerModel.getInstance().getDatabaseDriver().updateAccountData(finalUsername, pwdTextField.getText(), dependent.getId());
            PolicyOwnerModel.getInstance().getDatabaseDriver().updateDependent(dependent.getId(), nameTxtField.getText(), phoneTxtField.getText(), emailTxtField.getText(), addressTxtField.getText());
            PolicyOwnerModel.getInstance().updateDependents(dependent.getId(), nameTxtField.getText(), phoneTxtField.getText(), emailTxtField.getText(), addressTxtField.getText());
            PolicyHolderModel.getInstance().updateDependent(dependent.getId(), nameTxtField.getText(), phoneTxtField.getText(), emailTxtField.getText(), addressTxtField.getText());
            PolicyOwnerModel.getInstance().updateDependentsOfCurrentPolicyHolder(dependent.getId(), nameTxtField.getText(), phoneTxtField.getText(), emailTxtField.getText(), addressTxtField.getText());
            PolicyOwnerModel.getInstance().getPolicyOwnerViewFactory().closeCurrent2ndSubStage();
            if (PolicyOwnerModel.getInstance().getCurrentSelectedPolicyHolder() != null) {
                PolicyOwnerModel.getInstance().getDatabaseDriver().recordActivityHistory("UPDATE DEPENDENT INFORMATION OF " + dependent.getId() + " FROM " + PolicyOwnerModel.getInstance().getCurrentSelectedPolicyHolder().getId(), PolicyOwnerModel.getInstance().getPolicyOwner().getId());
            } else {
                PolicyHolderModel.getInstance().getDatabaseDriver().recordActivityHistory("UPDATE DEPENDENT INFORMATION OF " + dependent.getId() + " FROM " + PolicyHolderModel.getInstance().getPolicyHolder().getId(), PolicyHolderModel.getInstance().getPolicyHolder().getId());
            }

        });
    }
}
