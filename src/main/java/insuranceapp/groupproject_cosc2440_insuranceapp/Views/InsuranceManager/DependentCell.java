/**
 * @author Group 14
 */
package insuranceapp.groupproject_cosc2440_insuranceapp.Views.InsuranceManager;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import insuranceapp.groupproject_cosc2440_insuranceapp.Controllers.InsuranceManager.DependentCellController;
import insuranceapp.groupproject_cosc2440_insuranceapp.Models.InsuranceManager.Dependent;

public class DependentCell extends ListCell<Dependent> {
    @Override
    protected void updateItem(Dependent dependent, boolean empty) {
        super.updateItem(dependent, empty);
        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/InsuranceManager/DependentCell.fxml"));
            DependentCellController controller = new DependentCellController(dependent);
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