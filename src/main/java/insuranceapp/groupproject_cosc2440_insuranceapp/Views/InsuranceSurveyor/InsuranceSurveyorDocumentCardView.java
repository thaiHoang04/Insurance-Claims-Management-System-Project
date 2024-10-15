package insuranceapp.groupproject_cosc2440_insuranceapp.Views.InsuranceSurveyor;

import insuranceapp.groupproject_cosc2440_insuranceapp.Controllers.InsuranceSurveyor.DocumentCard;
import insuranceapp.groupproject_cosc2440_insuranceapp.Models.Document;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

import java.io.IOException;

public class InsuranceSurveyorDocumentCardView extends ListCell<Document> {
    @Override
    protected void updateItem(Document document, boolean b) {
        super.updateItem(document, b);
        if(b) {
            setText(null);
            setStyle("-fx-padding: 0px; -fx-background-radius: 0px ; -fx-background-color: e4e4e4; -fx-cursor: none;");
            setGraphic(null);
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/InsuranceSurveyorClaim/InsuranceSurveyorDocumentCardView.fxml"));
            DocumentCard documentCard = new DocumentCard(document);
            loader.setController(documentCard);
            setText(null);
            try {
                setStyle("-fx-padding: 0px; -fx-background-radius: 0px ; -fx-background-color: e4e4e4; -fx-cursor: none;");
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
