/**
 * @author Group 14
 */
package insuranceapp.groupproject_cosc2440_insuranceapp.Controllers.InsuranceManager;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import insuranceapp.groupproject_cosc2440_insuranceapp.Models.InsuranceManager.InsuranceSurveyor;

import java.net.URL;
import java.util.ResourceBundle;

public class SurveyorDetailController implements Initializable {
    public Label eID;
    public Label name;
    public Label address;
    public Label phone;
    public Label email;

    private final InsuranceSurveyor insuranceSurveyor;

    public SurveyorDetailController(InsuranceSurveyor insuranceSurveyor) {
        this.insuranceSurveyor = insuranceSurveyor;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setValue();
    }

    public void setValue() {
        eID.textProperty().bind(insuranceSurveyor.eIDProperty());
        phone.textProperty().bind(insuranceSurveyor.phoneProperty());
        email.textProperty().bind(insuranceSurveyor.emailProperty());
        address.textProperty().bind(insuranceSurveyor.addressProperty());
        name.textProperty().bind(insuranceSurveyor.fullNameProperty());
    }
}
