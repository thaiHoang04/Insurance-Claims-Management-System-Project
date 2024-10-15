/**
 * @author Group 14
 */
package insuranceapp.groupproject_cosc2440_insuranceapp.Controllers.Dependent;

import insuranceapp.groupproject_cosc2440_insuranceapp.Models.DependentModel;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewDependentInfoController implements Initializable {
    public TextField nameTxtField;
    public TextField phoneTxtField;
    public TextField emailTxtField;
    public TextField addressTxtField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameTxtField.setEditable(false);
        phoneTxtField.setEditable(false);
        emailTxtField.setEditable(false);
        addressTxtField.setEditable(false);
        nameTxtField.textProperty().bind(DependentModel.getInstance().getDependent().fullNameProperty());
        phoneTxtField.textProperty().bind(DependentModel.getInstance().getDependent().phoneNumberProperty());
        emailTxtField.textProperty().bind(DependentModel.getInstance().getDependent().emailProperty());
        addressTxtField.textProperty().bind(DependentModel.getInstance().getDependent().addressProperty());
    }
}
