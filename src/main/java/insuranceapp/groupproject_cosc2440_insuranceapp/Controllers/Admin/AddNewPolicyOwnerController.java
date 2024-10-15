/**
 * @author Group 14
 */
package insuranceapp.groupproject_cosc2440_insuranceapp.Controllers.Admin;

import insuranceapp.groupproject_cosc2440_insuranceapp.Models.AdminModel;
import insuranceapp.groupproject_cosc2440_insuranceapp.Models.PolicyOwner;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class AddNewPolicyOwnerController implements Initializable {
    public TextField policyOwnerNameTxtField;
    public Spinner<Double> feeSpinner;
    public Button saveBtn;
    public Label errorLbl;
    public TextField policyOwnerPwdTxtField;
    public TextField policyOwnerUsernameTxtField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SpinnerValueFactory<Double> valueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 9999999, 0.0);
        feeSpinner.setValueFactory(valueFactory);
        feeSpinner.setEditable(true);
        saveBtn.setOnAction(e -> {
            // Save the new policy owner
            if (checkEnoughInfo()) {
                errorLbl.setVisible(true);
            } else {
                errorLbl.setVisible(false);
                // Save the new policy owner
                String id = AdminModel.getInstance().generateIdForPolicyOwner();
                PolicyOwner policyOwner = new PolicyOwner(id ,policyOwnerNameTxtField.getText(), feeSpinner.getValue());
                if (AdminModel.getInstance().getDatabaseDriver().insertNewPolicyOwner(policyOwner) && AdminModel.getInstance().getDatabaseDriver().insertAccountData(policyOwnerUsernameTxtField.getText(), policyOwnerPwdTxtField.getText(), policyOwner.getId() , "PO")) {
                    AdminModel.getInstance().getDatabaseDriver().recordActivityHistory("ADD NEW POLICY OWNER", "admin");
                    AdminModel.getInstance().getPolicyOwners().add(policyOwner);
                    AdminModel.getInstance().getAdminViewFactory().updatePolicyOwnerView();
                    AdminModel.getInstance().getAdminViewFactory().closeCurrentSubStage();
                }

            }
        });
    }

    public boolean checkEnoughInfo() {
        // Check if the user has entered enough information to save the new policy owner
        return policyOwnerNameTxtField.getText().isEmpty() && feeSpinner.getValue().toString().isEmpty();
    }
}
