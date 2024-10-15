/**
 * @author Group 14
 */
package insuranceapp.groupproject_cosc2440_insuranceapp.Controllers;

import insuranceapp.groupproject_cosc2440_insuranceapp.Models.*;
import insuranceapp.groupproject_cosc2440_insuranceapp.Models.InsuranceManager.Model;
import insuranceapp.groupproject_cosc2440_insuranceapp.Views.InsuranceSurveyor.InsuranceSurveyorMainView;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    public TextField username_fld;
    public TextField pwd_fld;
    public Button login_btn;
    public Label error_lbl;

    private DatabaseDriver databaseDriver = new DatabaseDriver();
    private InsuranceSurveyorMainView insuranceSurveyorMainView = new InsuranceSurveyorMainView();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        login_btn.setOnAction(event -> onLogin());
    }

    private void onLogin() {
        Stage stage = (Stage) error_lbl.getScene().getWindow();
        boolean flag;
            ResultSet resultSet = databaseDriver.getAccountData(username_fld.getText(), pwd_fld.getText());
        try {
            if (resultSet.next()) {
                String role,id = null;
                role = resultSet.getString("role");
                switch (Objects.requireNonNull(role)) {
                    case "PO" -> {
                        id = resultSet.getString("id");
                        databaseDriver.getPolicyOwnerDataById(id);
                        PolicyOwnerModel.getInstance().getPolicyOwnerViewFactory().showPolicyOwnerWindow();
                        PolicyOwnerModel.getInstance().getPolicyOwnerViewFactory().closeStage(stage);
                    }
                    case "PH" -> {
                        id = resultSet.getString("id");
                        databaseDriver.getPolicyHolderDataById(id);
                        PolicyHolderModel.getInstance().getPolicyHolderViewFactory().showPolicyHolderWindow();
                        PolicyOwnerModel.getInstance().getPolicyOwnerViewFactory().closeStage(stage);
                    }
                    case "D" -> {
                        id = resultSet.getString("id");
                        databaseDriver.getDependentDataById(id);
                        DependentModel.getInstance().getDependentViewFactory().showDependentWindow();
                        PolicyOwnerModel.getInstance().getPolicyOwnerViewFactory().closeStage(stage);
                    }
                    case "IS" -> {
                        id = resultSet.getString("id");
                        databaseDriver.getEmployeeDataById(id);
                        insuranceSurveyorMainView.startMainViewInsuranceSurveyor();
                        PolicyOwnerModel.getInstance().getPolicyOwnerViewFactory().closeStage(stage);
                    }
                    case "IM" -> {
                        id = resultSet.getString("id");
                        databaseDriver.getEmployeeDataById(id);
                        Model.getInstance().getView().showClaimWindow();
                        PolicyOwnerModel.getInstance().getPolicyOwnerViewFactory().closeStage(stage);
                    }
                    case "ADMIN" -> {
                        AdminModel.getInstance().getAdminViewFactory().showAdminWindow();
                        PolicyOwnerModel.getInstance().getPolicyOwnerViewFactory().closeStage(stage);
                    }
                }
            } else {
                username_fld.setText("");
                pwd_fld.setText("");
                error_lbl.setText("No such Login Credentials!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
