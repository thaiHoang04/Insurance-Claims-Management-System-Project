/**
 * @author Group 14
 */
package insuranceapp.groupproject_cosc2440_insuranceapp.Controllers.Admin;

import insuranceapp.groupproject_cosc2440_insuranceapp.Models.AdminModel;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
    public BorderPane admin_parent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AdminModel.getInstance().getAdminViewFactory().getAdminSelectedMenuItem().addListener((observableValue, oldVal, newVal) -> {
            switch(newVal) {
                case "Insurance_Surveyor" -> {
                    admin_parent.setCenter(AdminModel.getInstance().getAdminViewFactory().getInsuranceSurveyorView());
                }
                case "Beneficiaries" -> {
                    admin_parent.setCenter(AdminModel.getInstance().getAdminViewFactory().getBeneficiariesView());
                }
                case "Insurance_Manager" -> {
                    admin_parent.setCenter(AdminModel.getInstance().getAdminViewFactory().getInsuranceManagerView());
                }
                default -> admin_parent.setCenter(AdminModel.getInstance().getAdminViewFactory().getClaimView());
            }
        });
    }
}
