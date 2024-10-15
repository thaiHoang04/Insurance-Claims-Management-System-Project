/**
 * @author Group 14
 */
package insuranceapp.groupproject_cosc2440_insuranceapp.Controllers.InsuranceManager;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import insuranceapp.groupproject_cosc2440_insuranceapp.Models.InsuranceManager.PolicyHolder;

import java.net.URL;
import java.util.ResourceBundle;

public class DetailPolicyHolderCellController implements Initializable {
    public Label pid;
    public Label name;
    public Label cardID;
    public Label phone;
    public Label address;
    private final PolicyHolder policyHolder;
    public DetailPolicyHolderCellController(PolicyHolder policyHolder) {
        this.policyHolder = policyHolder;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setValue();
    }

    private void setValue() {
        pid.textProperty().bind(policyHolder.customerIDProperty());
        name.textProperty().bind(policyHolder.fullNameProperty());
        cardID.textProperty().bind(policyHolder.insuranceCardProperty());
        phone.textProperty().bind(policyHolder.phoneNumberProperty());
        address.textProperty().bind(policyHolder.addressProperty());
    }
}
