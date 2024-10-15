/**
 * @author Group 14
 */
package insuranceapp.groupproject_cosc2440_insuranceapp.Views;

import insuranceapp.groupproject_cosc2440_insuranceapp.Controllers.PolicyOwner.DependentCellController;
import insuranceapp.groupproject_cosc2440_insuranceapp.Models.Dependent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

import java.io.IOException;

public class DependentCellFactoryForPolicyOwner extends ListCell<Dependent> {
    @Override
    protected void updateItem(Dependent dependent, boolean empty) {
        super.updateItem(dependent, empty);
        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/PolicyOwner/DependentCellView.fxml"));
            DependentCellController controller = new DependentCellController(dependent);
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
