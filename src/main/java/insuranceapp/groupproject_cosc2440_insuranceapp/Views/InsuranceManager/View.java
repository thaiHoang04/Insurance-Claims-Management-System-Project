/**
 * @author Group 14
 */
package insuranceapp.groupproject_cosc2440_insuranceapp.Views.InsuranceManager;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import insuranceapp.groupproject_cosc2440_insuranceapp.Controllers.InsuranceManager.ManagerMenuOption;

public class View {
    //create the view for insurance manager
    private final ObjectProperty<ManagerMenuOption> managerSelectedMenu;
    private AnchorPane policyHolderDB;
    private AnchorPane policyOwnerDB;
    private AnchorPane dependentDB;
    private AnchorPane surveyorDB;
    private AnchorPane claimDB;
    private AnchorPane acceptClaim;
    private AnchorPane rejectClaim;
    private AnchorPane acceptNotification;
    private AnchorPane rejectNotification;

    public View(){
        this.managerSelectedMenu = new SimpleObjectProperty<>();
    }

    public ObjectProperty<ManagerMenuOption> getManagerSelectedMenu() {
        return managerSelectedMenu;
    }

    public void showSurveyorDetail() {
        FXMLLoader loader = new FXMLLoader(View.class.getResource("/FXML/InsuranceManager/SurveyorDetail.fxml"));
        createStage(loader);
    }

    public AnchorPane getPH_dashBoard() {
        if(policyHolderDB == null) {
            try {
                policyHolderDB = new FXMLLoader(View.class.getResource("/FXML/InsuranceManager/PH_Menu.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return policyHolderDB;
    }

    public AnchorPane getPO_dashBoard() {
        if(policyOwnerDB == null) {
            try {
                policyOwnerDB = new FXMLLoader(View.class.getResource("/FXML/InsuranceManager/PO_Menu.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return policyOwnerDB;
    }

    public AnchorPane getDP_dashBoard() {
        if(dependentDB == null) {
            try {
                dependentDB = new FXMLLoader(getClass().getResource("/FXML/InsuranceManager/DP_Menu.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dependentDB;
    }

    public AnchorPane getIS_dashBoard() {
        if(surveyorDB == null) {
            try {
                surveyorDB = new FXMLLoader(getClass().getResource("/FXML/InsuranceManager/IS_Menu.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return surveyorDB;
    }

    public AnchorPane getClaim_dashBoard() {
        if(claimDB == null) {
            try {
                claimDB = new FXMLLoader(getClass().getResource("/FXML/InsuranceManager/Claim_Menu.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return claimDB;
    }

    public AnchorPane getAcceptClaim() {
        if(acceptClaim == null) {
            try {
                acceptClaim = new FXMLLoader(getClass().getResource("/FXML/InsuranceManager/AcceptClaim.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return acceptClaim;
    }

    public AnchorPane getRejectClaim() {
        if(rejectClaim == null) {
            try {
                rejectClaim = new FXMLLoader(getClass().getResource("/FXML/InsuranceManager/RejectClaim.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return rejectClaim;
    }

    public AnchorPane getAcceptNotification() {
        if(acceptNotification == null) {
            try {
                acceptNotification = new FXMLLoader(getClass().getResource("/FXML/InsuranceManager/AcceptNotification.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return acceptNotification;
    }

    public AnchorPane getRejectNotification() {
        if(rejectNotification == null) {
            try {
                rejectNotification = new FXMLLoader(getClass().getResource("/FXML/InsuranceManager/RejectNotification.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return rejectNotification;
    }

    public void showClaimWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/InsuranceManager/Manager.fxml"));
        createStage(loader);
    }

    public static void createStage(FXMLLoader loader) {
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Insurance Application");
        stage.show();
    }

    public void closeStage(Stage stage) {
        stage.close();
    }
}
