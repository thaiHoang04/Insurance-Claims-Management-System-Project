package insuranceapp.groupproject_cosc2440_insuranceapp.Views.InsuranceSurveyor;

import insuranceapp.groupproject_cosc2440_insuranceapp.Models.EmployeeModel;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class InsuranceSurveyorMainView implements Initializable {
    public Label userName;
    @FXML
    private Button changePolicyHolderView;

    @FXML
    private Button changeDependentView;

    @FXML
    private Button changePolicyOwnerView;

    @FXML
    private Button changeClaimView;

    @FXML
    private Label directPage;

    @FXML
    private BorderPane mainPane;

    private Stage stageIn;

    private Scene sceneIn;

    public InsuranceSurveyorMainView() {}

    public void startMainViewInsuranceSurveyor() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXML/InsuranceSurveyorMainComponent.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("Claim!");
            stage.setScene(scene);
            stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/claim.png"))));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("startMainViewInsuranceSurveyor error" + e.getMessage());
        }

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        directPage.setText("All Claims");
        addPaneClaimListView();

        userName.setText(EmployeeModel.getInstance().getUsername());
        changePolicyHolderView.setOnMouseClicked(this::switchScenePolicyHolderListView);
        changeDependentView.setOnMouseClicked(this::switchSceneDependentListView);
        changePolicyOwnerView.setOnMouseClicked(this::switchScenePolicyOwnerListView);
        changeClaimView.setOnMouseClicked(this::switchSceneClaimListView);

    }

    public void addPaneClaimListView(){
        try {
            VBox vBox = FXMLLoader.load(getClass().getResource("/FXML/InsuranceSurveyorClaim/InsuranceSurveyorClaimListView.fxml"));
            mainPane.setCenter(vBox);
        } catch (IOException e) {
            System.out.println("Could not add pane of claim list view");
        }
    }

    public void addPanePolicyHolderListView() {
        try {
            VBox vBox = FXMLLoader.load(getClass().getResource("/FXML/InsuranceSurveyorPolicyHolder/InsuranceSurveyorPolicyHolderListView.fxml"));
            mainPane.setCenter(vBox);
        } catch (IOException e) {
            System.out.println("Could not add pane of policy holder list view: " + e.getMessage());
        }
    }

    public void addPanePolicyOwnerListView() {
        try {
            VBox vBox = FXMLLoader.load(getClass().getResource("/FXML/InsuranceSurveyorPolicyOwner/InsuranceSurveyorPolicyOwnerListView.fxml"));
            mainPane.setCenter(vBox);
        } catch (IOException e) {
            System.out.println("Could not add pane of policy owner list view: " + e.getMessage());
        }
    }

    public void addPaneDependentListView(){
        try {
            VBox vBox = FXMLLoader.load(getClass().getResource("/FXML/InsuranceSurveyorDependent/InsuranceSurveyorDependentListView.fxml"));
            mainPane.setCenter(vBox);
        } catch (IOException e) {
            System.out.println("Could not add pane of dependent list view");
        }
    }

    public void switchScenePolicyHolderListView(Event mouseEvent) {
        directPage.setText("All Policy Holders");
        addPanePolicyHolderListView();
    }

    public void switchScenePolicyOwnerListView(Event mouseEvent) {
        directPage.setText("All Policy Owners");
        addPanePolicyOwnerListView();
    }

    public void switchSceneClaimListView(Event mouseEvent) {
        directPage.setText("All Claims");
        addPaneClaimListView();
    }

    public void switchSceneDependentListView(Event mouseEvent) {
        directPage.setText("All Dependents");
        addPaneDependentListView();
    }
}
