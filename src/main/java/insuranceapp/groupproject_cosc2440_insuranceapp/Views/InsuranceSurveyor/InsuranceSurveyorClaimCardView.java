package insuranceapp.groupproject_cosc2440_insuranceapp.Views.InsuranceSurveyor;

import insuranceapp.groupproject_cosc2440_insuranceapp.Controllers.InsuranceSurveyor.ClaimCard;
import insuranceapp.groupproject_cosc2440_insuranceapp.Models.Claim;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

import java.io.IOException;

public class InsuranceSurveyorClaimCardView extends ListCell<Claim> {

    @Override
    protected void updateItem(Claim claim, boolean b) {
        super.updateItem(claim, b);
        if(b) {
            setText(null);
            setGraphic(null);
            setStyle("-fx-padding: 0px; -fx-background-radius: 10px ; -fx-background-color: d9d9d9; -fx-cursor: none;");
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/InsuranceSurveyorClaim/InsuranceSurveyorClaimCardView.fxml"));
            ClaimCard claimCard = new ClaimCard(claim);
            loader.setController(claimCard);
            setText(null);
            try {
                setStyle("-fx-padding: 0px; -fx-background-radius: 10px ; -fx-background-color: d9d9d9; -fx-cursor: none;");
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
