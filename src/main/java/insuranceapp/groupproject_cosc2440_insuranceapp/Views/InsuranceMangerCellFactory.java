/**
 * @author Group 14
 */
package insuranceapp.groupproject_cosc2440_insuranceapp.Views;

import insuranceapp.groupproject_cosc2440_insuranceapp.Controllers.Admin.InsuranceManagerCellController;
import insuranceapp.groupproject_cosc2440_insuranceapp.Models.Employee;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

import java.io.IOException;

public class InsuranceMangerCellFactory extends ListCell<Employee> {
    @Override
    protected void updateItem(Employee insuranceManager, boolean empty) {
        super.updateItem(insuranceManager, empty);
        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Admin/InsuranceManagerCellView.fxml"));
            InsuranceManagerCellController controller = new InsuranceManagerCellController(insuranceManager);
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
