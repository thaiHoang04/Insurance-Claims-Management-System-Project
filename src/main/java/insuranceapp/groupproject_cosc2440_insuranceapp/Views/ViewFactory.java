/**
 * @author Group 14
 */
package insuranceapp.groupproject_cosc2440_insuranceapp.Views;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class ViewFactory {
    private final PolicyOwnerViewFactory policyOwnerViewFactory;
    private final StringProperty notificationMsg;

    public PolicyOwnerViewFactory getPolicyOwnerViewFactory() {
        return policyOwnerViewFactory;
    }

    public void showLoginWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/PolicyOwner/LoginView.fxml"));
        createStage(loader);
    }

    public ViewFactory() {
        policyOwnerViewFactory = new PolicyOwnerViewFactory();
        notificationMsg = new SimpleStringProperty("");
    }

    public StringProperty getNotificationMsg() {
        return notificationMsg;
    }

    private void createStage(FXMLLoader loader) {
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setTitle("Insurance App");
        stage.setResizable(false);
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/claim.png"))));
        stage.setScene(scene);
        stage.show();
    }
}
