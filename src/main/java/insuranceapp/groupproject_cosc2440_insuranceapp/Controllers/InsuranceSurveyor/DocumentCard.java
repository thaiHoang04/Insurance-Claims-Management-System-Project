/**
 * @author Group 14
 */
package insuranceapp.groupproject_cosc2440_insuranceapp.Controllers.InsuranceSurveyor;

import insuranceapp.groupproject_cosc2440_insuranceapp.Models.Document;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class DocumentCard implements Initializable {
    @FXML
    private Label id;
    @FXML
    private Label insuranceCard;
    @FXML
    private Label documentName;

    public Document document;

    public DocumentCard (Document document) {
        this.document = document;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id.textProperty().bind(document.idProperty());
        insuranceCard.setText(document.getDocumentName().substring(13,23));
        documentName.textProperty().bind(document.documentNameProperty());
    }
}
