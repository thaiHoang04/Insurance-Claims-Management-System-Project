/**
 * @author Group 14
 */
package insuranceapp.groupproject_cosc2440_insuranceapp.Controllers.InsuranceManager;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import insuranceapp.groupproject_cosc2440_insuranceapp.Models.InsuranceManager.PolicyHolder;
import insuranceapp.groupproject_cosc2440_insuranceapp.Views.InsuranceManager.View;

import java.net.URL;
import java.util.ResourceBundle;

import static insuranceapp.groupproject_cosc2440_insuranceapp.Views.InsuranceManager.View.createStage;

public class PolicyHolderCellController implements Initializable {
    public Label policyHolderID;
    public Label Name;
    public Label policyOwner;
    public Label cardID;
    public Label phoneNumber;
    public Button viewPolicyHolder;
    private final PolicyHolder policyHolder;
    public PolicyHolderCellController(PolicyHolder policyHolder) {
        this.policyHolder = policyHolder;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        viewPolicyHolder.setOnAction(event -> displayPolicyHolder(policyHolder));
        setValue();
    }

    private void displayPolicyHolder(PolicyHolder policyHolder) {
        PolicyHolderDetailController policyHolderDetailController = new PolicyHolderDetailController(policyHolder);
        FXMLLoader loader = new FXMLLoader(View.class.getResource("/FXML/InsuranceManager/PolicyHolderDetail.fxml"));
        loader.setController(policyHolderDetailController);
        createStage(loader);
    }

    public void setValue() {
        policyHolderID.textProperty().bind(policyHolder.customerIDProperty());
        Name.textProperty().bind(policyHolder.fullNameProperty());
        policyOwner.textProperty().bind(policyHolder.policyOwnerProperty());
        cardID.textProperty().bind(policyHolder.insuranceCardProperty());
        phoneNumber.textProperty().bind(policyHolder.phoneNumberProperty());
    }
}
