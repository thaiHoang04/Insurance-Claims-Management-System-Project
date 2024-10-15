/**
 * @author Group 14
 */
package insuranceapp.groupproject_cosc2440_insuranceapp.Views;

import insuranceapp.groupproject_cosc2440_insuranceapp.Controllers.PolicyOwner.ClaimCellForPolicyOwnerController;
import insuranceapp.groupproject_cosc2440_insuranceapp.Models.Claim;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

import java.io.IOException;

public class ClaimCellForPolicyOwner extends ListCell<Claim> {
    @Override
    protected void updateItem(Claim claim, boolean empty) {
        super.updateItem(claim, empty);
        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/PolicyOwner/ClaimCell.fxml"));
            ClaimCellForPolicyOwnerController controller = new ClaimCellForPolicyOwnerController(claim);
            loader.setController(controller);
            setText(null);
            try {
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
