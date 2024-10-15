/**
 * @author Group 14
 */
package insuranceapp.groupproject_cosc2440_insuranceapp.Views;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.util.Objects;

public class DependentViewFactory {
    //Current Stage
    private Stage currentStage;

    //Current subStage
    private Stage currentSubStage;

    private boolean subStageIsOpened = false;
    public void showDependentWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Dependent/DependentClaimView.fxml"));
        createStage(loader);
    }

    public void showViewDependentInfoWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/Dependent/ViewDependentInfoView.fxml"));
        createSubStage(loader, "Dependent Information");
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

