/**
 * @author Group 14
 */
package insuranceapp.groupproject_cosc2440_insuranceapp.Controllers.InsuranceManager;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import insuranceapp.groupproject_cosc2440_insuranceapp.Models.InsuranceManager.Model;

import java.net.URL;
import java.util.ResourceBundle;

public class Menu_Controller implements Initializable {
    public Button logout_button;
    public Button claim_button;
    public Button policyholder_button;
    public Button dependent_button;
    public Button surveyor_button;
    public Button policyowner_button;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListeners();
    }

    private void addListeners() {
        claim_button.setOnAction(event -> onClaim());
        policyholder_button.setOnAction(event -> onPH());
        dependent_button.setOnAction(event -> onDP());
        surveyor_button.setOnAction(event -> onIS());
        policyowner_button.setOnAction(event -> onPO());
    }

    private void onPO() {
        Model.getInstance().getView().getManagerSelectedMenu().set(ManagerMenuOption.POLICYOWNER);
    }

    private void onClaim() {
        Model.getInstance().getView().getManagerSelectedMenu().set(ManagerMenuOption.CLAIM);
    }

    private void onPH() {
        Model.getInstance().getView().getManagerSelectedMenu().set(ManagerMenuOption.POLICYHOLDER);
    }

    private void onDP() {
        Model.getInstance().getView().getManagerSelectedMenu().set(ManagerMenuOption.DEPENDENT);
    }

    private void onIS() {
        Model.getInstance().getView().getManagerSelectedMenu().set(ManagerMenuOption.INSURANCE_SURVEYOR);
    }
}
