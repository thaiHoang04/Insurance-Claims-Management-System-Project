/**
 * @author Group 14
 */
package insuranceapp.groupproject_cosc2440_insuranceapp.Controllers.PolicyHolder;

import insuranceapp.groupproject_cosc2440_insuranceapp.Models.PolicyHolderModel;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class PolicyHolderController implements Initializable {
    public BorderPane policyHolder_parent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PolicyHolderModel.getInstance().getPolicyHolderViewFactory().getPolicyHolderSelectedMenuItem().addListener((observableValue, oldVal, newVal) -> {
            if (newVal.equals("Dependent")) {
                policyHolder_parent.setCenter(PolicyHolderModel.getInstance().getPolicyHolderViewFactory().getDependentView());
            } else {
                policyHolder_parent.setCenter(PolicyHolderModel.getInstance().getPolicyHolderViewFactory().getClaimView());
            }
        });
    }
}
