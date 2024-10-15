/**
 * @author Group 14
 */
package insuranceapp.groupproject_cosc2440_insuranceapp.Controllers.InsuranceManager;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import insuranceapp.groupproject_cosc2440_insuranceapp.Models.InsuranceManager.Claim;

import java.net.URL;
import java.util.ResourceBundle;

public class DocumentCellController implements Initializable {
    public Label claimID;
    public Label cardID;
    public Label documentName;
    private final Claim claim;

    public DocumentCellController(Claim claim) {
        this.claim = claim;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setValue();
    }

    public void setValue() {
        claimID.textProperty().bind(claim.claimIDProperty());
        cardID.textProperty().bind(claim.cardIDProperty());
        documentName.textProperty().bind(claim.documentProperty());
    }
}
