/**
 * @author Group 14
 */
package insuranceapp.groupproject_cosc2440_insuranceapp.Controllers.InsuranceManager;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import insuranceapp.groupproject_cosc2440_insuranceapp.Models.InsuranceManager.InsuranceSurveyor;
import insuranceapp.groupproject_cosc2440_insuranceapp.Views.InsuranceManager.View;

import java.net.URL;
import java.util.ResourceBundle;

import static insuranceapp.groupproject_cosc2440_insuranceapp.Views.InsuranceManager.View.createStage;

public class SurveyorCellController implements Initializable {
    public Label employeeID;
    public Label Name;
    public Label phoneNumber;
    public Label email;
    public Label address;
    public Button viewSurveyor;

    private final InsuranceSurveyor surveyor;

    public SurveyorCellController(InsuranceSurveyor surveyor) {
        this.surveyor = surveyor;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        viewSurveyor.setOnAction(event -> displaySurveyor(surveyor));
        setValue();
    }

    private void displaySurveyor(InsuranceSurveyor surveyor) {
        SurveyorDetailController surveyorDetailController = new SurveyorDetailController(surveyor);
        FXMLLoader loader = new FXMLLoader(View.class.getResource("/FXML/InsuranceManager/SurveyorDetail.fxml"));
        loader.setController(surveyorDetailController);
        createStage(loader);
    }

    public void setValue() {
        employeeID.textProperty().bind(surveyor.eIDProperty());
        Name.textProperty().bind(surveyor.fullNameProperty());
        phoneNumber.textProperty().bind(surveyor.phoneProperty());
        address.textProperty().bind(surveyor.addressProperty());
        email.textProperty().bind(surveyor.emailProperty());
    }
}
