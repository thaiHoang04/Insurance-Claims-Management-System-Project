/**
 * @author Group 14
 */
package insuranceapp.groupproject_cosc2440_insuranceapp.Views.InsuranceManager;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import insuranceapp.groupproject_cosc2440_insuranceapp.Controllers.InsuranceManager.DetailPolicyHolderCellController;
import insuranceapp.groupproject_cosc2440_insuranceapp.Models.InsuranceManager.PolicyHolder;

public class DetailPolicyHolderCell extends ListCell<PolicyHolder> {
    @Override
    protected void updateItem(PolicyHolder policyHolder, boolean empty) {
        super.updateItem(policyHolder, empty);
        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/InsuranceManager/DetailPolicyHolderCell.fxml"));
            DetailPolicyHolderCellController controller = new DetailPolicyHolderCellController(policyHolder);
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