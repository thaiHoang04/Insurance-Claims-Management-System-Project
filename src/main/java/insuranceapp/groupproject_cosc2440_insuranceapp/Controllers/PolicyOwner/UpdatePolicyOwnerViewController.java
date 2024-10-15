/**
 * @author Group 14
 */
package insuranceapp.groupproject_cosc2440_insuranceapp.Controllers.PolicyOwner;

import insuranceapp.groupproject_cosc2440_insuranceapp.Models.AdminModel;
import insuranceapp.groupproject_cosc2440_insuranceapp.Models.PolicyOwnerModel;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class UpdatePolicyOwnerViewController implements Initializable {
    public TextField policyOwnerNameTxtField;
    public Spinner<Double> feeSpinner;
    public Button updateBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SpinnerValueFactory<Double> valueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 9999999);
        feeSpinner.setValueFactory(valueFactory);
        feeSpinner.setEditable(true);
        policyOwnerNameTxtField.setText(PolicyOwnerModel.getInstance().getPolicyOwner().getName());
        feeSpinner.getValueFactory().setValue(PolicyOwnerModel.getInstance().getPolicyOwner().feeProperty().getValue());
        updateBtn.setOnAction(event -> {
            PolicyOwnerModel.getInstance().getPolicyOwner().setName(policyOwnerNameTxtField.getText());
            PolicyOwnerModel.getInstance().getPolicyOwner().setFee(feeSpinner.getValue());
            AdminModel.getInstance().updateCellInfo(PolicyOwnerModel.getInstance().getPolicyOwner());
            PolicyOwnerModel.getInstance().getDatabaseDriver().updatePolicyOwnerById(PolicyOwnerModel.getInstance().getPolicyOwner().getId());
            PolicyOwnerModel.getInstance().getPolicyOwnerViewFactory().closeCurrentSubStage();
            PolicyOwnerModel.getInstance().getDatabaseDriver().recordActivityHistory("UPDATE POLICY OWNER INFORMATION", PolicyOwnerModel.getInstance().getPolicyOwner().getId());
        });
    }
}
