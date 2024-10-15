/**
 * @author Group 14
 */
package insuranceapp.groupproject_cosc2440_insuranceapp.Controllers.Admin;

import insuranceapp.groupproject_cosc2440_insuranceapp.Models.AdminModel;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminMenuController implements Initializable {
    public Button ClaimBtn;
    public Button BeneficiariesBtn;
    public Button LogOutBtn;
    public Button InsuranceManagerBtn;
    public Button InsuranceSurveyorBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListeners();
    }

    private void addListeners() {
        ClaimBtn.setOnAction(event -> onClaimView());
        BeneficiariesBtn.setOnAction(event -> onPolicyOwnerView());
        InsuranceManagerBtn.setOnAction(event -> onInsuranceManagerView());
        InsuranceSurveyorBtn.setOnAction(event -> onInsuranceSurveyorView());
        LogOutBtn.setOnAction(actionEvent -> {
            Platform.exit();
        });
    }

    private void onClaimView() {
        AdminModel.getInstance().getAdminViewFactory().getAdminSelectedMenuItem().set("Claims");
    }

    private void onPolicyOwnerView() {
        AdminModel.getInstance().getAdminViewFactory().getAdminSelectedMenuItem().set("Beneficiaries");
    }

    private void onInsuranceManagerView() {
        AdminModel.getInstance().getAdminViewFactory().getAdminSelectedMenuItem().set("Insurance_Manager");
    }

    private void onInsuranceSurveyorView() {
        AdminModel.getInstance().getAdminViewFactory().getAdminSelectedMenuItem().set("Insurance_Surveyor");
    }


}
