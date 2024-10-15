/**
 * @author Group 14
 */
package insuranceapp.groupproject_cosc2440_insuranceapp.Controllers.Dependent;

import insuranceapp.groupproject_cosc2440_insuranceapp.Models.Claim;
import insuranceapp.groupproject_cosc2440_insuranceapp.Models.PolicyOwnerModel;
import insuranceapp.groupproject_cosc2440_insuranceapp.Views.InsuranceSurveyor.InsuranceSurveyorClaimDetailView;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class ClaimCellForDependentController implements Initializable {
    public Label claimIdLbl;
    public Label claimDateLBl;
    public Label insuredPersonLbl;
    public Label statusLbl;
    public Button detailClaimBtn;

    private final Claim claim;

    public ClaimCellForDependentController(Claim claim) {
        this.claim = claim;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        claimIdLbl.textProperty().bind(claim.idProperty());
        claimDateLBl.textProperty().bind(claim.claimDateProperty().asString());
        insuredPersonLbl.textProperty().bind(claim.insuredPersonProperty());
        statusLbl.textProperty().bind(claim.statusProperty());

        //Set the tool tip for detail claim button
        Tooltip tooltip = new Tooltip("View all claim information");
        tooltip.setShowDelay(Duration.seconds(0.005));
        detailClaimBtn.setTooltip(tooltip);
        detailClaimBtn.setOnAction(event -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/InsuranceSurveyorClaim/InsuranceSurveyorClaimDetailView.fxml"));
            InsuranceSurveyorClaimDetailView claimInfoViewController = new InsuranceSurveyorClaimDetailView(claim);
            loader.setController(claimInfoViewController);
            PolicyOwnerModel.getInstance().getPolicyOwnerViewFactory().createSubStage(loader, "Claim view");
        });
    }
}
