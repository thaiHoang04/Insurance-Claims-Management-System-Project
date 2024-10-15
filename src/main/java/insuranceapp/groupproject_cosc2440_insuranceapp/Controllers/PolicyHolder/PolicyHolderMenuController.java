/**
 * @author Group 14
 */
package insuranceapp.groupproject_cosc2440_insuranceapp.Controllers.PolicyHolder;

import insuranceapp.groupproject_cosc2440_insuranceapp.Models.PolicyHolderModel;
import insuranceapp.groupproject_cosc2440_insuranceapp.Models.PolicyOwnerModel;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class PolicyHolderMenuController implements Initializable {
    public Button ClaimBtn;
    public Button dependentBtn;
    public Button LogOutBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListeners();
    }

    private void addListeners() {
        ClaimBtn.setOnAction(event -> onClaimView());
        dependentBtn.setOnAction(event -> onDependentView());
        LogOutBtn.setOnAction(actionEvent -> {
            Platform.exit();
        });
    }

    private void onClaimView() {
        PolicyHolderModel.getInstance().getPolicyHolderViewFactory().getPolicyHolderSelectedMenuItem().set("Claims");
    }

    private void onDependentView() {
        PolicyHolderModel.getInstance().getPolicyHolderViewFactory().getPolicyHolderSelectedMenuItem().set("Dependent");
    }
}
