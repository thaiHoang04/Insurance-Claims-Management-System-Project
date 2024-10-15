/**
 * @author Group 14
 */
package insuranceapp.groupproject_cosc2440_insuranceapp.Views;

import insuranceapp.groupproject_cosc2440_insuranceapp.Controllers.PolicyOwner.PolicyOwnerClaimViewController;
import insuranceapp.groupproject_cosc2440_insuranceapp.Controllers.PolicyOwner.PolicyOwnerPolicyHolderViewController;
import insuranceapp.groupproject_cosc2440_insuranceapp.Controllers.PolicyOwner.UpdateDependentInfoViewController;
import insuranceapp.groupproject_cosc2440_insuranceapp.Controllers.PolicyOwner.UpdatePolicyHolderInfoViewForPolicyOwnerController;
import insuranceapp.groupproject_cosc2440_insuranceapp.Models.Dependent;
import insuranceapp.groupproject_cosc2440_insuranceapp.Models.PolicyOwnerModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.util.Objects;

public class PolicyOwnerViewFactory {
    //Policy Owner views
    private final StringProperty policyOwnerSelectedMenuItem;
    private AnchorPane ClaimView;
    private AnchorPane BeneficiariesView;

    //Controller
    private PolicyOwnerClaimViewController policyOwnerClaimViewController;
    private PolicyOwnerPolicyHolderViewController policyOwnerPolicyHolderViewController;

    //Current Stage
    private Stage currentStage;

    //Current subStage
    private Stage currentSubStage;
    private Stage current2ndSubStage;

    private boolean subStageIsOpened = false;

    private boolean sub2ndStageIsOpened = false;

    public PolicyOwnerViewFactory(){
        this.policyOwnerSelectedMenuItem = new SimpleStringProperty("");
    };

    public StringProperty getPolicyOwnerSelectedMenuItem() {
        return policyOwnerSelectedMenuItem;
    }

    public Stage getCurrentStage() {
        return currentStage;
    }

    public void setPolicyOwnerClaimViewController(PolicyOwnerClaimViewController policyOwnerClaimViewController) {
        this.policyOwnerClaimViewController = policyOwnerClaimViewController;
    }

    public void setPolicyOwnerBeneficiariesViewController(PolicyOwnerPolicyHolderViewController policyOwnerPolicyHolderViewController) {
        this.policyOwnerPolicyHolderViewController = policyOwnerPolicyHolderViewController;
    }
    public AnchorPane getClaimView() {
        if (ClaimView == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/PolicyOwner/PolicyOwnerClaimView.fxml"));
                policyOwnerClaimViewController = loader.getController();
                ClaimView = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ClaimView;
    }

    public AnchorPane getBeneficiariesView() {
        if (BeneficiariesView == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/PolicyOwner/PolicyOwnerPolicyHolderView.fxml"));
                BeneficiariesView = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return BeneficiariesView;
    }

    public void showPolicyHolderView() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/PolicyOwner/PolicyOwnerPolicyHolderView.fxml"));
        createStage(loader);
    }

    public void showPolicyOwnerWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/PolicyOwner/PolicyOwner.fxml"));
        createStage(loader);
    }

    public void showAddNewClaimWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/PolicyOwner/AddNewClaimView.fxml"));
        createSubStage(loader, "Add new claim.");
    }

    public void showPolicyHolderWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/PolicyOwner/PolicyOwnerPolicyHolderView.fxml"));
        createSubStage(loader, "Policy Holder");
    }

    public void showAddNewPolicyHolderWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/PolicyOwner/AddNewPolicyHolderView.fxml"));
        createSubStage(loader, "Add new policy holder.");
    }

    public void showAddNewDependentWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/PolicyOwner/AddNewDependentView.fxml"));
        create2ndSubStage(loader, "Add new dependent policy.");
    }



    public void showUpdateInfoWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/PolicyOwner/UpdatePolicyOwnerInfoView.fxml"));
        createSubStage(loader, "Update Info");
    }

    public void showDependentListView() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/PolicyOwner/PolicyOwnerDependentView.fxml"));
        createSubStage(loader, "Dependent List");
    }

    public void showUpdatePolicyHolderView() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/PolicyOwner/UpdatePolicyHolderInfoView.fxml"));
        loader.setController(new UpdatePolicyHolderInfoViewForPolicyOwnerController(PolicyOwnerModel.getInstance().getCurrentSelectedPolicyHolder()));
        create2ndSubStage(loader, "Update Policy Holder Info");
    }

    public void showUpdateDependentView(Dependent dependent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/PolicyOwner/UpdateDependentInfoView.fxml"));
        loader.setController(new UpdateDependentInfoViewController(dependent));
        create2ndSubStage(loader, "Update Dependent Info");
    }

    public void showUpdateClaimView() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/PolicyOwner/UpdateClaimInfoView.fxml"));
        createSubStage(loader, "Update Claim Info");
    }

    public void createSubStage(FXMLLoader loader, String string) {
        if (!subStageIsOpened) {
            Scene scene = null;
            try {
                scene = new Scene(loader.load());
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage stage = new Stage();
            stage.setTitle(string);
            stage.resizableProperty().setValue(Boolean.FALSE);
            stage.setScene(scene);
            currentSubStage = stage;
            subStageIsOpened = true;
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    subStageIsOpened = false;
                }
            });
            stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/claim.png"))));
            stage.show();
        }
    }

    private void create2ndSubStage(FXMLLoader loader, String string) {
        if (!sub2ndStageIsOpened) {
            Scene scene = null;
            try {
                scene = new Scene(loader.load());
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage stage = new Stage();
            stage.setTitle(string);
            stage.resizableProperty().setValue(Boolean.FALSE);
            stage.setScene(scene);
            current2ndSubStage = stage;
            sub2ndStageIsOpened = true;
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    sub2ndStageIsOpened = false;
                }
            });
            stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/claim.png"))));
            stage.show();
        }
    }


    private void createStage(FXMLLoader loader) {
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setTitle("Policy Owner");
        stage.setScene(scene);
        currentStage = stage;
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/claim.png"))));
        stage.show();
    }

    //Close stage
    public void closeStage(Stage stage) {
        stage.close();
    }

    public void closeCurrentSubStage() {
        currentSubStage.close();
        subStageIsOpened = false;
    }

    public void closeCurrent2ndSubStage() {
        current2ndSubStage.close();
        sub2ndStageIsOpened = false;
    }

    public void updateClaimView() {
        if (policyOwnerClaimViewController != null) {
            policyOwnerClaimViewController.updateTotalClaim(Integer.toString(PolicyOwnerModel.getInstance().getNumberOfClaimById(PolicyOwnerModel.getInstance().getPolicyOwner().getId())));
            policyOwnerClaimViewController.updateTotalPendingClaim(Integer.toString(PolicyOwnerModel.getInstance().getNumberOfPendingClaim(PolicyOwnerModel.getInstance().getPolicyOwner().getId())));
        }
    }

    public void updateBeneficiariesView() {
        policyOwnerPolicyHolderViewController.updateTotalPolicyHolder(Integer.toString(PolicyOwnerModel.getInstance().getNumberOfPolicyHolder(PolicyOwnerModel.getInstance().getPolicyOwner().getId())));
        policyOwnerPolicyHolderViewController.updateAnnualFee(PolicyOwnerModel.getInstance().getTotalAnnualFee(PolicyOwnerModel.getInstance().getPolicyOwner().getId()));
    }

    public void resetViewFactory() {
        sub2ndStageIsOpened = false;
    }
}
