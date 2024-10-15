/**
 * @author Group 14
 */
package insuranceapp.groupproject_cosc2440_insuranceapp.Controllers.PolicyOwner;

import insuranceapp.groupproject_cosc2440_insuranceapp.Models.PolicyOwnerModel;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class PolicyOwnerMenuController implements Initializable {

    public Button ClaimBtn;
    public Button BeneficiariesBtn;
    public Button LogOutBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListeners();
    }

    private void addListeners() {
        ClaimBtn.setOnAction(event -> onClaimView());
        BeneficiariesBtn.setOnAction(event -> onBeneficiariesView());
        LogOutBtn.setOnAction(actionEvent -> {
            Platform.exit();
        });
    }

    private void onClaimView() {
        PolicyOwnerModel.getInstance().getPolicyOwnerViewFactory().getPolicyOwnerSelectedMenuItem().set("Claims");
    }

    private void onBeneficiariesView() {
        PolicyOwnerModel.getInstance().getPolicyOwnerViewFactory().getPolicyOwnerSelectedMenuItem().set("Beneficiaries");
    }
}
