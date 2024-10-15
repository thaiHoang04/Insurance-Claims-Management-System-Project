package insuranceapp.groupproject_cosc2440_insuranceapp.Views.InsuranceSurveyor;

import insuranceapp.groupproject_cosc2440_insuranceapp.Controllers.InsuranceSurveyor.PolicyHolderCard;
import insuranceapp.groupproject_cosc2440_insuranceapp.Models.PolicyHolder;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

import java.io.IOException;

public class InsuranceSurveyorPolicyHolderCardView extends ListCell<PolicyHolder> {
    @Override
    protected void updateItem(PolicyHolder holder, boolean b) {
        super.updateItem(holder, b);
        if(b) {
            setText(null);
            setGraphic(null);
            setStyle("-fx-padding: 0px; -fx-background-radius: 0px ; -fx-background-color: d9d9d9; -fx-cursor: none;");
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/InsuranceSurveyorPolicyHolder/InsuranceSurveyorPolicyHolderCardView.fxml"));
            PolicyHolderCard holderCard = new PolicyHolderCard(holder);
            loader.setController(holderCard);
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
