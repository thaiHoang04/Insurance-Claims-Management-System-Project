/**
 * @author Group 14
 */
package insuranceapp.groupproject_cosc2440_insuranceapp.Controllers.InsuranceManager;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import insuranceapp.groupproject_cosc2440_insuranceapp.Models.InsuranceManager.PolicyOwner;
import insuranceapp.groupproject_cosc2440_insuranceapp.Views.InsuranceManager.View;

import java.net.URL;
import java.util.ResourceBundle;

import static insuranceapp.groupproject_cosc2440_insuranceapp.Views.InsuranceManager.View.createStage;

public class PolicyOwnerCellController implements Initializable {
    public Label poid;
    public Label name;
    public Label fee;
    public Button viewPO;

    private final PolicyOwner policyOwner;
    public PolicyOwnerCellController(PolicyOwner policyOwner) {
        this.policyOwner = policyOwner;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        viewPO.setOnAction(event -> displayPolicyOwner(policyOwner));
        setValue();
    }

    private void displayPolicyOwner(PolicyOwner policyOwner) {
        PolicyOwnerDetailController policyOwnerDetailController = new PolicyOwnerDetailController(policyOwner);
        FXMLLoader loader = new FXMLLoader(View.class.getResource("/FXML/InsuranceManager/PolicyOwnerDetail.fxml"));
        loader.setController(policyOwnerDetailController);
        createStage(loader);
    }

    public void setValue() {
        poid.textProperty().bind(policyOwner.POIDProperty());
        name.textProperty().bind(policyOwner.nameProperty());
        fee.textProperty().bind(policyOwner.feeProperty());
    }
}
