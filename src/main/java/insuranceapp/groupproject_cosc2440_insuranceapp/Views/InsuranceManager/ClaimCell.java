/**
 * @author Group 14
 */
package insuranceapp.groupproject_cosc2440_insuranceapp.Views.InsuranceManager;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import insuranceapp.groupproject_cosc2440_insuranceapp.Controllers.InsuranceManager.ClaimCellController;
import insuranceapp.groupproject_cosc2440_insuranceapp.Models.InsuranceManager.Claim;

public class ClaimCell extends ListCell<Claim> {
    @Override
    protected void updateItem(Claim claim, boolean empty) {
        super.updateItem(claim, empty);
        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/InsuranceManager/ClaimCell.fxml"));
            ClaimCellController controller = new ClaimCellController(claim);
            loader.setController(controller);
            setText(null);
            try {
                setGraphic(loader.load());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
