/**
 * @author Group 14
 */
package insuranceapp.groupproject_cosc2440_insuranceapp.Views;

import insuranceapp.groupproject_cosc2440_insuranceapp.Controllers.Admin.*;
import insuranceapp.groupproject_cosc2440_insuranceapp.Models.AdminModel;
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

public class AdminViewFactory {
    private final StringProperty adminSelectedMenuItem;
    private AnchorPane ClaimView;
    private AnchorPane BeneficiariesView;
    private AnchorPane InsuranceManagerView;
    private AnchorPane InsuranceSurveyorView;

    private boolean subStageIsOpened = false;

    private Stage currentStage;
    private Stage currentSubStage;

    private AdminPolicyOwnerViewController adminPolicyOwnerViewController;
    private AdminInsuranceSurveyorViewController adminInsuranceSurveyorViewController;
    private AdminInsuranceManagerViewController adminInsuranceManagerViewController;


    public AdminViewFactory() {
        this.adminSelectedMenuItem = new SimpleStringProperty("");
    }

    public void setAdminPolicyOwnerViewController(AdminPolicyOwnerViewController adminPolicyOwnerViewController) {
        this.adminPolicyOwnerViewController = adminPolicyOwnerViewController;
    }

    public void setAdminInsuranceSurveyorViewController(AdminInsuranceSurveyorViewController adminInsuranceSurveyorViewController) {
        this.adminInsuranceSurveyorViewController = adminInsuranceSurveyorViewController;
    }

    public void setAdminInsuranceManagerViewController(AdminInsuranceManagerViewController adminInsuranceManagerViewController) {
        this.adminInsuranceManagerViewController = adminInsuranceManagerViewController;
    }

    public AnchorPane getClaimView() {
        if (ClaimView == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Admin/AdminClaimView.fxml"));
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
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Admin/AdminPolicyOwnerView.fxml"));
                BeneficiariesView = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return BeneficiariesView;
    }

    public AnchorPane getInsuranceManagerView() {
        if (InsuranceManagerView == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Admin/AdminInsuranceManagerView.fxml"));
                InsuranceManagerView = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return InsuranceManagerView;
    }

    public AnchorPane getInsuranceSurveyorView() {
        if (InsuranceSurveyorView == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Admin/AdminInsuranceSurveyorView.fxml"));
                InsuranceSurveyorView = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return InsuranceSurveyorView;
    }

    public void showAdminWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Admin/Admin.fxml"));
        createStage(loader);
    }

    public void showAddNewPolicyOwner() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Admin/AddNewPolicyOwner.fxml"));
        createSubStage(loader, "Add New Policy Owner");
    }

    public void showAddNewInsuranceManager() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Admin/AddNewInsuranceManagerView.fxml"));
        createSubStage(loader, "Add New Insurance Manager");
    }

    public void showAddNewInsuranceSurveyor() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Admin/AddNewInsuranceSurveyorView.fxml"));
        createSubStage(loader, "Add New Insurance Surveyor");
    }

    private void createStage(FXMLLoader loader) {
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setTitle("Admin");
        stage.setScene(scene);
        currentStage = stage;
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/claim.png"))));
        stage.show();
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

    public StringProperty getAdminSelectedMenuItem() {
        return adminSelectedMenuItem;
    }

    public void closeCurrentSubStage() {
        currentSubStage.close();
        subStageIsOpened = false;
    }

    public void updatePolicyOwnerView() {
        adminPolicyOwnerViewController.numberOfPolicyOwner.setText(String.valueOf(AdminModel.getInstance().getPolicyOwners().size()));
    }

    public void updateInsuranceManagersView() {
        adminInsuranceManagerViewController.numberOfInsuranceManager.setText(String.valueOf(AdminModel.getInstance().getInsuranceManagers().size()));
    }

    public void updateInsuranceSurveyorsView() {
        adminInsuranceSurveyorViewController.numberOfInsuranceSurveyor.setText(String.valueOf(AdminModel.getInstance().getInsuranceSurveyors().size()));
    }
}
