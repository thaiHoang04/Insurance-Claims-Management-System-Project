/**
 * @author Group 14
 */
package insuranceapp.groupproject_cosc2440_insuranceapp.Controllers.PolicyOwner;

import insuranceapp.groupproject_cosc2440_insuranceapp.Models.PolicyOwnerModel;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class PolicyOwnerController implements Initializable {
    public BorderPane policyOwner_parent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PolicyOwnerModel.getInstance().getPolicyOwnerViewFactory().getPolicyOwnerSelectedMenuItem().addListener((observableValue, oldVal, newVal) -> {
            switch(newVal) {
                case "Beneficiaries" -> {
                    policyOwner_parent.setCenter(PolicyOwnerModel.getInstance().getPolicyOwnerViewFactory().getBeneficiariesView());
                }
                default -> policyOwner_parent.setCenter(PolicyOwnerModel.getInstance().getPolicyOwnerViewFactory().getClaimView());
            }
        });
    }
}
