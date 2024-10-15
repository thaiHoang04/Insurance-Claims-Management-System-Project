/**
 * @author Group 14
 */
package insuranceapp.groupproject_cosc2440_insuranceapp.Views;

import insuranceapp.groupproject_cosc2440_insuranceapp.Controllers.PolicyHolder.UpdatePolicyHolderInfoViewController;
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

public class PolicyHolderViewFactory {
    //Current Stage
    private Stage currentStage;

    //Current subStage
    private Stage currentSubStage;

    private final StringProperty policyHolderSelectedMenuItem;
    private AnchorPane claimView;
    private AnchorPane dependentView;

    public PolicyHolderViewFactory(){
        this.policyHolderSelectedMenuItem = new SimpleStringProperty("");
    };

    public StringProperty getPolicyHolderSelectedMenuItem() {
        return policyHolderSelectedMenuItem;
    }

    public AnchorPane getClaimView() {
        if (claimView == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/PolicyHolder/PolicyHolderClaimView.fxml"));
                claimView = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return claimView;
    }

    public AnchorPane getDependentView() {
        if (dependentView == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/PolicyHolder/PolicyHolderDependentView.fxml"));
                dependentView = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return dependentView;
    }

    private boolean subStageIsOpened = false;
    public void showPolicyHolderWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/PolicyHolder/PolicyHolder.fxml"));
        createStage(loader);
    }

    public void showUpdatePolicyHolderView() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/PolicyOwner/UpdatePolicyHolderInfoView.fxml"));
        loader.setController(new UpdatePolicyHolderInfoViewController());
        createSubStage(loader, "Update Policy Holder Info");
    }

    public void showUpdatePolicyHolderInfoWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/PolicyOwner/UpdatePolicyHolderInfoView.fxml"));
        loader.setController(new UpdatePolicyHolderInfoViewController());
        createSubStage(loader, "Update Policy Holder Info");
    }

    public void closeCurrentSubStage() {
        if (subStageIsOpened) {
            currentSubStage.close();
            subStageIsOpened = false;
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
        stage.setTitle("Dependent");
        stage.setScene(scene);
        currentStage = stage;
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/claim.png"))));
        stage.show();
    }

    private void createSubStage(FXMLLoader loader, String string) {
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
}
