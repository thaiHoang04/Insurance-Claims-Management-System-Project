/**
 * @author Group 14
 */
package insuranceapp.groupproject_cosc2440_insuranceapp.Controllers.PolicyOwner;

import insuranceapp.groupproject_cosc2440_insuranceapp.Models.PolicyHolder;
import insuranceapp.groupproject_cosc2440_insuranceapp.Models.PolicyOwnerModel;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class UpdatePolicyHolderInfoViewForPolicyOwnerController implements Initializable {
    public TextField nameTxtField;
    public TextField pwdTxtField;
    public TextField phoneTxtField;
    public TextField emailTxtField;
    public TextField addressTxtField;
    public Button saveBtn;
    private PolicyHolder policyHolder;

    public UpdatePolicyHolderInfoViewForPolicyOwnerController(PolicyHolder policyHolder) {
        this.policyHolder = policyHolder;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ResultSet rs = PolicyOwnerModel.getInstance().getDatabaseDriver().getAccountDataById(policyHolder.getId());
        String username = null, password = null;
        try {
            if (rs.next()) {
                username = rs.getString("username");
                password = rs.getString("password");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        nameTxtField.setText(policyHolder.getFullName());
        pwdTxtField.setText(password);
        phoneTxtField.setText(policyHolder.getPhoneNumber());
        emailTxtField.setText(policyHolder.getEmail());
        addressTxtField.setText(policyHolder.getAddress());
        String finalUsername = username;
        saveBtn.setOnAction(actionEvent -> {
            PolicyOwnerModel.getInstance().getDatabaseDriver().updateAccountData(finalUsername, pwdTxtField.getText(), policyHolder.getId());
            PolicyOwnerModel.getInstance().getDatabaseDriver().updatePolicyHolder(policyHolder.getId(), nameTxtField.getText(), phoneTxtField.getText(), emailTxtField.getText(), addressTxtField.getText());
            PolicyOwnerModel.getInstance().updatePolicyHolders(policyHolder.getId(), nameTxtField.getText(), phoneTxtField.getText(), emailTxtField.getText(), addressTxtField.getText());
            PolicyOwnerModel.getInstance().getPolicyOwnerViewFactory().closeCurrentSubStage();
            PolicyOwnerModel.getInstance().getDatabaseDriver().recordActivityHistory("UPDATE POLICY HOLDER INFORMATION OF " + policyHolder.getId(), PolicyOwnerModel.getInstance().getPolicyOwner().getId());
        });
    }
}
