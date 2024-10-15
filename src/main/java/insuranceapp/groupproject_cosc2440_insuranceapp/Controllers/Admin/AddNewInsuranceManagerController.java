/**
 * @author Group 14
 */

package insuranceapp.groupproject_cosc2440_insuranceapp.Controllers.Admin;

import insuranceapp.groupproject_cosc2440_insuranceapp.Models.*;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AddNewInsuranceManagerController implements Initializable {
    public TextField fullNameTxtField;
    public TextField usernameTxtField;
    public TextField pwdTxtField;
    public TextField phoneNumTxtField;
    public TextField emailTxtField;
    public TextField addressTxtField;
    public Button saveButton;
    public Label errorLbl;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        saveButton.setOnAction(actionEvent -> {
            if (checkEnoughInfo()) {
                errorLbl.setVisible(false);
                String id = AdminModel.getInstance().generateIdForInsuranceManager();
                Employee employee = new Employee(id,  phoneNumTxtField.getText(), addressTxtField.getText() ,fullNameTxtField.getText() ,emailTxtField.getText());
                if (AdminModel.getInstance().getDatabaseDriver().insertNewEmployee(employee) && AdminModel.getInstance().getDatabaseDriver().insertAccountData(usernameTxtField.getText(), pwdTxtField.getText(), id, "IM")) {
                    AdminModel.getInstance().addInsuranceManagers(employee);
                    AdminModel.getInstance().getAdminViewFactory().updateInsuranceManagersView();
                    AdminModel.getInstance().getDatabaseDriver().recordActivityHistory("ADD NEW INSURANCE MANAGER " + employee.getId(), "admin");
                    AdminModel.getInstance().getAdminViewFactory().closeCurrentSubStage();
                }
            } else {
                errorLbl.setVisible(true);
            }
        });
    }

    public boolean checkEnoughInfo() {
        return !(fullNameTxtField.getText().isEmpty()) && !(pwdTxtField.getText().isEmpty()) && !(usernameTxtField.getText().isEmpty()) &&!(phoneNumTxtField.getText().isEmpty()) && !(emailTxtField.getText().isEmpty()) && !(addressTxtField.getText().isEmpty());
    }
}
