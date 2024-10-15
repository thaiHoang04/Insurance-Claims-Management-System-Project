/**
 * @author Group 14
 */
package insuranceapp.groupproject_cosc2440_insuranceapp.Controllers.InsuranceManager;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import insuranceapp.groupproject_cosc2440_insuranceapp.Models.InsuranceManager.Claim;

import java.net.URL;
import java.util.ResourceBundle;

public class DetailClaimCellController_PH implements Initializable {
    public Label claimID;
    public Label insuredPerson;
    public Label cardID;
    public Label status;
    public Label amount;
    private final Claim claim;

    public DetailClaimCellController_PH(Claim claim) {
        this.claim = claim;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setValue();
    }

    public void setValue() {
        claimID.textProperty().bind(claim.cardIDProperty());
        insuredPerson.textProperty().bind(claim.insuredPersonProperty());
        cardID.textProperty().bind(claim.cardIDProperty());
        status.textProperty().bind(claim.statusProperty());
        amount.textProperty().bind(claim.amountProperty());
    }
}
