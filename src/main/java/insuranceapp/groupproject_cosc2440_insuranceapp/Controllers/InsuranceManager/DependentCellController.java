/**
 * @author Group 14
 */
package insuranceapp.groupproject_cosc2440_insuranceapp.Controllers.InsuranceManager;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import insuranceapp.groupproject_cosc2440_insuranceapp.Models.InsuranceManager.Dependent;
import insuranceapp.groupproject_cosc2440_insuranceapp.Views.InsuranceManager.View;

import java.net.URL;
import java.util.ResourceBundle;

import static insuranceapp.groupproject_cosc2440_insuranceapp.Views.InsuranceManager.View.createStage;

public class DependentCellController implements Initializable {
    public Label dependentID;
    public Label Name;
    public Label policyHolder;
    public Label cardID;
    public Label phoneNumber;
    public Button viewDependent;

    private final Dependent dependent;

    public DependentCellController(Dependent dependent) {
        this.dependent = dependent;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        viewDependent.setOnAction(event -> displayDependent(dependent));
        setValue();
    }

    private void displayDependent(Dependent dependent) {
        DependentDetailController dependentDetailController = new DependentDetailController();
        dependentDetailController.setDependent(dependent);
        FXMLLoader loader = new FXMLLoader(View.class.getResource("/FXML/InsuranceManager/DependentDetail.fxml"));
        loader.setController(dependentDetailController);
        createStage(loader);
    }

    public void setValue() {
        dependentID.textProperty().bind(dependent.customerIDProperty());
        Name.textProperty().bind(dependent.fullNameProperty());
        policyHolder.textProperty().bind(dependent.policyHolderProperty());
        cardID.textProperty().bind(dependent.insuranceCardProperty());
        phoneNumber.textProperty().bind(dependent.phoneNumberProperty());
    }
}
