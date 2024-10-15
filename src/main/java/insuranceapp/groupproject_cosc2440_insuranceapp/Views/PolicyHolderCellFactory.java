/**
 * @author Group 14
 */
package insuranceapp.groupproject_cosc2440_insuranceapp.Views;

import insuranceapp.groupproject_cosc2440_insuranceapp.Controllers.PolicyOwner.PolicyHolderCellController;
import insuranceapp.groupproject_cosc2440_insuranceapp.Models.PolicyHolder;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

import java.io.IOException;

public class PolicyHolderCellFactory extends ListCell<PolicyHolder> {
    @Override
    protected void updateItem(PolicyHolder policyHolder, boolean empty) {
        super.updateItem(policyHolder, empty);
        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/PolicyOwner/PolicyHolderCellView.fxml"));
            PolicyHolderCellController controller = new PolicyHolderCellController(policyHolder);
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
