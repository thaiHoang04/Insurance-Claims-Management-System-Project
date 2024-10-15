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

public class InsuranceSurveyorCellController implements Initializable {
    public Label insuranceSurveyorIdLbl;
    public Label insuranceSurveyorNameLbl;
    public Label insuranceSurveyorEmailLbl;
    public Label insuranceSurveyorPhoneNumLbl;
    public Button updateInfoBtn;
    public Button deleteBtn;

    private Employee insuranceSurveyor;

    public InsuranceSurveyorCellController(Employee employee) {
        this.insuranceSurveyor = employee;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        insuranceSurveyorIdLbl.setText(insuranceSurveyor.getId());
        insuranceSurveyorNameLbl.setText(insuranceSurveyor.getFullName());
        insuranceSurveyorEmailLbl.setText(insuranceSurveyor.getEmail());
        insuranceSurveyorPhoneNumLbl.setText(insuranceSurveyor.getPhone());
        updateInfoBtn.setOnAction(event -> {
            // Update insurance surveyor info
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Admin/UpdateEmployeeInfoView.fxml"));
            loader.setController(new UpdateInsuranceEmployeeController(insuranceSurveyor));
            AdminModel.getInstance().getAdminViewFactory().createSubStage(loader, "Update Insurance Employee Information");
        });
        deleteBtn.setOnAction(event -> {
            // Delete insurance surveyor
            AdminModel.getInstance().getDatabaseDriver().deleteEmployee(insuranceSurveyor.getId());
            AdminModel.getInstance().getInsuranceSurveyors().remove(insuranceSurveyor);
            AdminModel.getInstance().getDatabaseDriver().deleteAccountData(insuranceSurveyor.getId());
            AdminModel.getInstance().getDatabaseDriver().recordActivityHistory("DELETE NEW INSURANCE SURVEYOR " + insuranceSurveyor.getId(), "admin");
            AdminModel.getInstance().getAdminViewFactory().updateInsuranceSurveyorsView();
        });
    }
}
