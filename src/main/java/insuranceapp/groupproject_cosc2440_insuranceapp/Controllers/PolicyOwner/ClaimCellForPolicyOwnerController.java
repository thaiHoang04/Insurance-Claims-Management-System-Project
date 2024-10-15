/**
 * @author Group 14
 */
package insuranceapp.groupproject_cosc2440_insuranceapp.Controllers.PolicyOwner;

import insuranceapp.groupproject_cosc2440_insuranceapp.Models.Claim;
import insuranceapp.groupproject_cosc2440_insuranceapp.Models.PolicyHolder;
import insuranceapp.groupproject_cosc2440_insuranceapp.Models.PolicyHolderModel;
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

public class ClaimCellForPolicyOwnerController implements Initializable {
    public Label claimIdLbl;
    public Label claimDateLBl;
    public Label insuredPersonLbl;
    public Label statusLbl;
    public Button detailClaimBtn;
    public Button edit_Btn;
    public Button deleteClaim_Btn;

    private final Claim claim;

    public ClaimCellForPolicyOwnerController(Claim claim) {
        this.claim = claim;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        claimIdLbl.textProperty().bind(claim.idProperty());
        claimDateLBl.textProperty().bind(claim.claimDateProperty().asString());
        insuredPersonLbl.textProperty().bind(claim.insuredPersonProperty());
        statusLbl.textProperty().bind(claim.statusProperty());
        if (!PolicyHolderModel.getInstance().getClaims().isEmpty()) {
            deleteClaim_Btn.setDisable(true);
            deleteClaim_Btn.setVisible(false);
        }

        //Set the tool tip for detail claim button
        Tooltip tooltip = new Tooltip("View all claim information");
        tooltip.setShowDelay(Duration.seconds(0.005));
        detailClaimBtn.setTooltip(tooltip);

        //Set the tool tip for edit claim button
        Tooltip tooltip1 = new Tooltip("Update claim information");
        tooltip.setShowDelay(Duration.seconds(0.005));
        edit_Btn.setTooltip(tooltip1);

        //Set the tool tip for delete claim button
        Tooltip tooltip2 = new Tooltip("Delete claim");
        tooltip.setShowDelay(Duration.seconds(0.005));
        deleteClaim_Btn.setTooltip(tooltip2);

        deleteClaim_Btn.setOnAction(event -> {
            PolicyOwnerModel.getInstance().getDatabaseDriver().deleteClaim(claim.getId());
            PolicyOwnerModel.getInstance().deleteClaims(claim);
            PolicyOwnerModel.getInstance().getPolicyOwnerViewFactory().updateClaimView();
            PolicyOwnerModel.getInstance().getDatabaseDriver().recordActivityHistory("DELETE CLAIM " + claim.getId(), PolicyOwnerModel.getInstance().getPolicyOwner().getId());
        });
        detailClaimBtn.setOnAction(event -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/InsuranceSurveyorClaim/InsuranceSurveyorClaimDetailView.fxml"));
            InsuranceSurveyorClaimDetailView claimInfoViewController = new InsuranceSurveyorClaimDetailView(claim);
            loader.setController(claimInfoViewController);
            PolicyOwnerModel.getInstance().getPolicyOwnerViewFactory().createSubStage(loader, "Claim view");
        });
        edit_Btn.setOnAction(event -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/PolicyOwner/UpdateClaimInfoView.fxml"));
            UpdateClaimViewController updateClaimViewController = new UpdateClaimViewController(claim);
            loader.setController(updateClaimViewController);
            PolicyOwnerModel.getInstance().getPolicyOwnerViewFactory().createSubStage(loader, "Update claim view");
        });
    }
}
