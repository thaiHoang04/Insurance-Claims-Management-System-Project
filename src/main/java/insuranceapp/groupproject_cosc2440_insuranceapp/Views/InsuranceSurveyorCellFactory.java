/**
 * @author Group 14
 */
package insuranceapp.groupproject_cosc2440_insuranceapp.Views;

import insuranceapp.groupproject_cosc2440_insuranceapp.Controllers.Admin.InsuranceSurveyorCellController;
import insuranceapp.groupproject_cosc2440_insuranceapp.Models.Employee;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

import java.io.IOException;

public class InsuranceSurveyorCellFactory extends ListCell<Employee> {
    @Override
    protected void updateItem(Employee insuranceManager, boolean empty) {
        super.updateItem(insuranceManager, empty);
        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Admin/InsuranceSurveyorCellView.fxml"));
            InsuranceSurveyorCellController controller = new InsuranceSurveyorCellController(insuranceManager);
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
