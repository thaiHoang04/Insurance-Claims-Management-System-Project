/**
 * @author Group 14
 */
package insuranceapp.groupproject_cosc2440_insuranceapp.Controllers.Admin;

import insuranceapp.groupproject_cosc2440_insuranceapp.Models.AdminModel;
import insuranceapp.groupproject_cosc2440_insuranceapp.Models.Employee;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class InsuranceManagerCellController implements Initializable {
    public Label insuranceManagerIdLbl;
    public Label insuranceManagerNameLbl;
    public Label insuranceManagerEmailLbl;
    public Label insuranceManagerPhoneNumLbl;
    public Button updateInfoBtn;
    public Button deleteBtn;

    private Employee insuranceManager;

    public InsuranceManagerCellController(Employee insuranceManager) {
        this.insuranceManager = insuranceManager;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        insuranceManagerIdLbl.setText(insuranceManager.getId());
        insuranceManagerNameLbl.setText(insuranceManager.getFullName());
        insuranceManagerEmailLbl.setText(insuranceManager.getEmail());
        insuranceManagerPhoneNumLbl.setText(insuranceManager.getPhone());
        updateInfoBtn.setOnAction(event -> {
            // Update insurance manager info
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Admin/UpdateEmployeeInfoView.fxml"));
            loader.setController(new UpdateInsuranceEmployeeController(insuranceManager));
            AdminModel.getInstance().getAdminViewFactory().createSubStage(loader, "Update Insurance Employee Information");
        });
        deleteBtn.setOnAction(event -> {
            // Delete insurance manager
            AdminModel.getInstance().getDatabaseDriver().deleteEmployee(insuranceManager.getId());
            AdminModel.getInstance().getInsuranceManagers().remove(insuranceManager);
            AdminModel.getInstance().getDatabaseDriver().deleteAccountData(insuranceManager.getId());
            AdminModel.getInstance().getDatabaseDriver().recordActivityHistory("DELETE INSURANCE MANAGER " + insuranceManager.getId(), "admin");
            AdminModel.getInstance().getAdminViewFactory().updateInsuranceManagersView();
        });
    }
}
