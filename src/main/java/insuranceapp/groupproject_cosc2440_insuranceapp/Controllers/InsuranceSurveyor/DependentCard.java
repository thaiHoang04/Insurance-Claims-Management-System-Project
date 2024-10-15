/**
 * @author Group 14
 */
package insuranceapp.groupproject_cosc2440_insuranceapp.Controllers.InsuranceSurveyor;

import insuranceapp.groupproject_cosc2440_insuranceapp.Models.Dependent;
import insuranceapp.groupproject_cosc2440_insuranceapp.Views.InsuranceSurveyor.InsuranceSurveyorDependentDetailView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DependentCard implements Initializable {
    @FXML
    private Label id;

    @FXML
    private Label userName;

    @FXML
    private Label insuranceCard;

    @FXML
    private Label view_more;

    private Dependent dependent;

    public DependentCard(Dependent dependent) {
        this.dependent = dependent;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updatePolicyHolderCard();
    }

    public void updatePolicyHolderCard() {
        id.setText(dependent.getId());
        userName.setText(dependent.getFullName());
        insuranceCard.setText(dependent.getInsuranceCard());

        view_more.setOnMouseClicked(mouseEvent -> {
            onButtonViewClick(dependent);
        });
    }

    public void onButtonViewClick(Dependent dependent) {
        System.out.println("Click" + dependent.getId());
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/InsuranceManager/DependentDetail.fxml"));
            fxmlLoader.setController(new InsuranceSurveyorDependentDetailView(dependent));
            Stage stage = new Stage();
            stage.setTitle("Dependent Detail of  " + dependent.getId());
            stage.setScene(new Scene(fxmlLoader.load()));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("startDependentDetail error" + e.getMessage());
        }
    }
}
