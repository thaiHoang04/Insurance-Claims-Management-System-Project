/**
 * @author Group 14
 */
package insuranceapp.groupproject_cosc2440_insuranceapp.Views;

import insuranceapp.groupproject_cosc2440_insuranceapp.Controllers.Admin.AdminPolicyOwnerCellController;
import insuranceapp.groupproject_cosc2440_insuranceapp.Models.PolicyOwner;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

import java.io.IOException;

public class PolicyOwnerCellFactory extends ListCell<PolicyOwner> {
    @Override
    protected void updateItem(PolicyOwner policyOwner, boolean empty) {
        super.updateItem(policyOwner, empty);
        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Admin/PolicyOwnerCellView.fxml"));
            AdminPolicyOwnerCellController controller = new AdminPolicyOwnerCellController(policyOwner);
            loader.setController(controller);
            setText(null);
            try {
                setGraphic(loader.load());
                prefWidthProperty().bind(getListView().widthProperty());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void updateSelected(boolean b) {
    }
}
