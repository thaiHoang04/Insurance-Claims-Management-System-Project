/**
 * @author Group 14
 */
package insuranceapp.groupproject_cosc2440_insuranceapp.Controllers.PolicyOwner;

import insuranceapp.groupproject_cosc2440_insuranceapp.Models.PolicyHolder;
import insuranceapp.groupproject_cosc2440_insuranceapp.Models.PolicyOwnerModel;
import insuranceapp.groupproject_cosc2440_insuranceapp.Views.DependentCellFactoryForPolicyOwner;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class PolicyOwnerDependentViewController implements Initializable {
    public ListView dependent_listview;
    public Button addNewDependentBtn;
    public Label noDependentLbl;

    private PolicyHolder policyHolder;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PolicyOwnerModel.getInstance().setDependentsOfCurrentPolicyHolder();
        this.policyHolder = PolicyOwnerModel.getInstance().getCurrentSelectedPolicyHolder();
        dependent_listview.setItems(PolicyOwnerModel.getInstance().getDependentsOfCurrentPolicyHolder());
        dependent_listview.setCellFactory(e -> new DependentCellFactoryForPolicyOwner());
        int numOfDependents = PolicyOwnerModel.getInstance().getDependentsOfCurrentPolicyHolder().size();
        if (numOfDependents == 0) {
            noDependentLbl.setVisible(true);
        } else {
            noDependentLbl.setVisible(false);
        }
        addNewDependentBtn.setOnAction(event -> {
            PolicyOwnerModel.getInstance().getPolicyOwnerViewFactory().showAddNewDependentWindow();
        });
    }
}
