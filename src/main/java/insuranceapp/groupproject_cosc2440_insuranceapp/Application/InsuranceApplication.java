package insuranceapp.groupproject_cosc2440_insuranceapp.Application;

import insuranceapp.groupproject_cosc2440_insuranceapp.Models.PolicyOwnerModel;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class InsuranceApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        PolicyOwnerModel.getInstance().getViewFactory().showLoginWindow();
    }

    public static void main(String[] args) {
        launch();
    }
}