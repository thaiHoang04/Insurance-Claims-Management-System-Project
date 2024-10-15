/**
 * @author Group 14
 */
package insuranceapp.groupproject_cosc2440_insuranceapp.Views.InsuranceManager;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import insuranceapp.groupproject_cosc2440_insuranceapp.Controllers.InsuranceManager.PolicyOwnerCellController;
import insuranceapp.groupproject_cosc2440_insuranceapp.Models.InsuranceManager.PolicyOwner;

public class PolicyOwnerCell extends ListCell<PolicyOwner> {
    @Override
    protected void updateItem(PolicyOwner policyOwner, boolean empty) {
        super.updateItem(policyOwner, empty);
        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/InsuranceManager/PolicyOwnerCell.fxml"));
            PolicyOwnerCellController controller = new PolicyOwnerCellController(policyOwner);
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
