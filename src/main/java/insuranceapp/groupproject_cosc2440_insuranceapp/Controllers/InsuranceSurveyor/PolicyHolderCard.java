/**
 * @author Group 14
 */
package insuranceapp.groupproject_cosc2440_insuranceapp.Controllers.InsuranceSurveyor;

import insuranceapp.groupproject_cosc2440_insuranceapp.Models.PolicyHolder;
import insuranceapp.groupproject_cosc2440_insuranceapp.Views.InsuranceSurveyor.InsuranceSurveyorPolicyHolderDetailView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PolicyHolderCard implements Initializable {
    @FXML
    private Label id;

    @FXML
    private Label userName;

    @FXML
    private Label insuranceCard;

    @FXML
    private Label view_more;

    private PolicyHolder holder;

    public PolicyHolderCard(PolicyHolder holder) {
        this.holder = holder;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updatePolicyHolderCard();
    }

    public void updatePolicyHolderCard() {
        id.setText(holder.getId());
        userName.setText(holder.getFullName());
        insuranceCard.setText(holder.getInsuranceCard());

        view_more.setOnMouseClicked(mouseEvent -> {
            onButtonViewClick(holder);
        });
    }

    public void onButtonViewClick(PolicyHolder holder) {
        System.out.println("Click" + holder.getId());
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/InsuranceManager/PolicyHolderDetail.fxml"));
            fxmlLoader.setController(new InsuranceSurveyorPolicyHolderDetailView(holder));
            Stage stage = new Stage();
            stage.setTitle("Policy Holder Detail of  " + holder.getId());
            stage.setScene(new Scene(fxmlLoader.load()));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("startPolicyHolderDetail error" + e.getMessage());
        }
    }

}
