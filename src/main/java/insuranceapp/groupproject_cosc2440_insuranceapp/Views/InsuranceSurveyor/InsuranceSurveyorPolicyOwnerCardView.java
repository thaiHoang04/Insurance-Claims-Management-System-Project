package insuranceapp.groupproject_cosc2440_insuranceapp.Views.InsuranceSurveyor;

import insuranceapp.groupproject_cosc2440_insuranceapp.Controllers.InsuranceSurveyor.PolicyOwnerCard;
import insuranceapp.groupproject_cosc2440_insuranceapp.Models.PolicyOwner;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

import java.io.IOException;

public class InsuranceSurveyorPolicyOwnerCardView extends ListCell<PolicyOwner> {
    @Override
    protected void updateItem(PolicyOwner owner, boolean b) {
        super.updateItem(owner, b);
        if(b) {
            setText(null);
            setGraphic(null);
            setStyle("-fx-padding: 0px; -fx-background-radius: 0px ; -fx-background-color: d9d9d9; -fx-cursor: none;");
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/InsuranceSurveyorPolicyOwner/InsuranceSurveyorPolicyOwnerCardView.fxml"));
            PolicyOwnerCard policyOwnerCard = new PolicyOwnerCard(owner);
            loader.setController(policyOwnerCard);
            setText(null);
            try {
                setStyle("-fx-padding: 0px; -fx-background-radius: 0px ; -fx-background-color: d9d9d9; -fx-cursor: none;");
                setGraphic(loader.load());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void updateSelected(boolean b) {
    }
}
