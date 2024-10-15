package insuranceapp.groupproject_cosc2440_insuranceapp.Views.InsuranceSurveyor;

import insuranceapp.groupproject_cosc2440_insuranceapp.Controllers.InsuranceSurveyor.DependentCard;
import insuranceapp.groupproject_cosc2440_insuranceapp.Models.Dependent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

import java.io.IOException;

public class InsuranceSurveyorDependentCardView extends ListCell<Dependent> {
    @Override
    protected void updateItem(Dependent dependent, boolean b) {
        super.updateItem(dependent, b);
        if(b) {
            setText(null);
            setGraphic(null);
            setStyle("-fx-padding: 0px; -fx-background-radius: 0px ; -fx-background-color: d9d9d9; -fx-cursor: none;");
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/InsuranceSurveyorDependent/InsuranceSurveyorDependentCardView.fxml"));
            DependentCard dependentCard = new DependentCard(dependent);
            loader.setController(dependentCard);
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
