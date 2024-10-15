/**
 * @author Group 14
 */
package insuranceapp.groupproject_cosc2440_insuranceapp.Controllers.InsuranceManager;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import insuranceapp.groupproject_cosc2440_insuranceapp.Models.InsuranceManager.Dependent;

import java.net.URL;
import java.util.ResourceBundle;

public class DetailDependentCellController_PH implements Initializable {
    public Label dID;
    public Label name;
    public Label cardID;
    public Label phone;
    public Label address;
    private final Dependent dependent;
    public DetailDependentCellController_PH(Dependent dependent) {
        this.dependent = dependent;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setValue();
    }

    private void setValue() {
        dID.textProperty().bind(dependent.customerIDProperty());
        name.textProperty().bind(dependent.fullNameProperty());
        cardID.textProperty().bind(dependent.insuranceCardProperty());
        phone.textProperty().bind(dependent.phoneNumberProperty());
        address.textProperty().bind(dependent.addressProperty());
    }
}
