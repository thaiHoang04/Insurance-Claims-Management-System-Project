/**
 * @author Group 14
 */
package insuranceapp.groupproject_cosc2440_insuranceapp.Controllers.PolicyHolder;

import insuranceapp.groupproject_cosc2440_insuranceapp.Models.PolicyHolder;
import insuranceapp.groupproject_cosc2440_insuranceapp.Models.PolicyHolderModel;
import insuranceapp.groupproject_cosc2440_insuranceapp.Models.PolicyOwnerModel;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class UpdatePolicyHolderInfoViewController implements Initializable {
    public TextField nameTxtField;
    public TextField pwdTxtField;
    public TextField phoneTxtField;
    public TextField emailTxtField;
    public TextField addressTxtField;
    public Button saveBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ResultSet rs = PolicyOwnerModel.getInstance().getDatabaseDriver().getAccountDataById(PolicyHolderModel.getInstance().getPolicyHolder().getId());
        String username = null, password = null;
        try {
            if (rs.next()) {
                username = rs.getString("username");
                password = rs.getString("password");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        nameTxtField.setText(PolicyHolderModel.getInstance().getPolicyHolder().getFullName());
        pwdTxtField.setText(password);
        phoneTxtField.setText(PolicyHolderModel.getInstance().getPolicyHolder().getPhoneNumber());
        emailTxtField.setText(PolicyHolderModel.getInstance().getPolicyHolder().getEmail());
        addressTxtField.setText(PolicyHolderModel.getInstance().getPolicyHolder().getAddress());
        String finalUsername = username;
        saveBtn.setOnAction(actionEvent -> {
            PolicyHolderModel.getInstance().getDatabaseDriver().updateAccountData(finalUsername, pwdTxtField.getText(), PolicyHolderModel.getInstance().getPolicyHolder().getId());
            PolicyHolderModel.getInstance().getDatabaseDriver().updatePolicyHolder(PolicyHolderModel.getInstance().getPolicyHolder().getId(), nameTxtField.getText(), phoneTxtField.getText(), emailTxtField.getText(), addressTxtField.getText());
            PolicyHolderModel.getInstance().getPolicyHolder().setFullName(nameTxtField.getText());
            PolicyHolderModel.getInstance().getPolicyHolder().setPhoneNumber(phoneTxtField.getText());
            PolicyHolderModel.getInstance().getPolicyHolder().setEmail(emailTxtField.getText());
            PolicyHolderModel.getInstance().getPolicyHolder().setAddress(addressTxtField.getText());
            PolicyHolderModel.getInstance().getPolicyHolderViewFactory().closeCurrentSubStage();
            PolicyHolderModel.getInstance().getDatabaseDriver().recordActivityHistory("UPDATE POLICY HOLDER INFORMATION " , PolicyHolderModel.getInstance().getPolicyHolder().getId());
        });
    }
}
