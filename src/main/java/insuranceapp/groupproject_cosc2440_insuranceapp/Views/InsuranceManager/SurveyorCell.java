/**
 * @author Group 14
 */
package insuranceapp.groupproject_cosc2440_insuranceapp.Views.InsuranceManager;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import insuranceapp.groupproject_cosc2440_insuranceapp.Controllers.InsuranceManager.SurveyorCellController;
import insuranceapp.groupproject_cosc2440_insuranceapp.Models.InsuranceManager.InsuranceSurveyor;

public class SurveyorCell extends ListCell<InsuranceSurveyor> {
    @Override
    protected void updateItem(InsuranceSurveyor surveyor, boolean empty) {
        super.updateItem(surveyor, empty);
        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/InsuranceManager/SurveyorCell.fxml"));
            SurveyorCellController controller = new SurveyorCellController(surveyor);
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
