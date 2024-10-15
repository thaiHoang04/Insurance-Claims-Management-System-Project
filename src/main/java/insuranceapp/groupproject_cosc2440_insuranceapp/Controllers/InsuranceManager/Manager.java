/**
 * @author Group 14
 */
package insuranceapp.groupproject_cosc2440_insuranceapp.Controllers.InsuranceManager;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import insuranceapp.groupproject_cosc2440_insuranceapp.Models.InsuranceManager.Model;

import java.net.URL;
import java.util.ResourceBundle;

public class Manager implements Initializable {
    @FXML
    public BorderPane manager_parent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Model.getInstance().getView().getManagerSelectedMenu().addListener((observable, oldValue, newValue) -> {
            switch (newValue) {
                case POLICYHOLDER -> manager_parent.setCenter(Model.getInstance().getView().getPH_dashBoard());
                case DEPENDENT -> manager_parent.setCenter(Model.getInstance().getView().getDP_dashBoard());
                case INSURANCE_SURVEYOR -> manager_parent.setCenter(Model.getInstance().getView().getIS_dashBoard());
                case POLICYOWNER -> manager_parent.setCenter(Model.getInstance().getView().getPO_dashBoard());
                default -> manager_parent.setCenter(Model.getInstance().getView().getClaim_dashBoard());
            }
        });
    }
}
