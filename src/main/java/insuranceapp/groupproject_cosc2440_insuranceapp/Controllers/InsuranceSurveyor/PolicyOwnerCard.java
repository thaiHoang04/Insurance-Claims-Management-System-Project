/**
 * @author Group 14
 */
package insuranceapp.groupproject_cosc2440_insuranceapp.Controllers.InsuranceSurveyor;

import insuranceapp.groupproject_cosc2440_insuranceapp.Models.PolicyOwner;
import insuranceapp.groupproject_cosc2440_insuranceapp.Views.InsuranceSurveyor.InsuranceSurveyorPolicyOwnerDetailView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PolicyOwnerCard implements Initializable {
    @FXML
    private Label id;

    @FXML
    private Label userName;

    @FXML
    private Label setFee;

    @FXML
    private Label view_more;

    private PolicyOwner owner;

    public PolicyOwnerCard(PolicyOwner owner) {
        this.owner = owner;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updatePolicyOwnerCard();
    }

    public void updatePolicyOwnerCard() {
        id.textProperty().bind(owner.idProperty());
        userName.textProperty().bind(owner.nameProperty());
        setFee.textProperty().bind(owner.feeProperty().asString());

        view_more.setOnMouseClicked(mouseEvent -> {
            onButtonViewClick(owner);
        });
    }

    public void onButtonViewClick(PolicyOwner owner) {
        System.out.println("Click" + owner.getId());
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/InsuranceManager/PolicyOwnerDetail.fxml"));
            fxmlLoader.setController(new InsuranceSurveyorPolicyOwnerDetailView(owner));
            Stage stage = new Stage();
            stage.setTitle("Policy Owner Detail of  " + owner.getId());
            stage.setScene(new Scene(fxmlLoader.load()));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("startPolicyOwnerDetail error" + e.getMessage());
        }
    }
}
