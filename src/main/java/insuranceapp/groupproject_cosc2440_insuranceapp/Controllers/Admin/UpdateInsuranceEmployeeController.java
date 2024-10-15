/**
 * @author Group 14
 */
package insuranceapp.groupproject_cosc2440_insuranceapp.Controllers.Admin;

import insuranceapp.groupproject_cosc2440_insuranceapp.Models.AdminModel;
import insuranceapp.groupproject_cosc2440_insuranceapp.Models.Employee;
import insuranceapp.groupproject_cosc2440_insuranceapp.Models.PolicyOwnerModel;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class UpdateInsuranceEmployeeController implements Initializable {
    public TextField nameTxtField;
    public TextField pwdTxtField;
    public TextField phoneTxtField;
    public TextField emailTxtField;
    public TextField addressTxtField;
    public Button saveBtn;

    private Employee employee;

    public UpdateInsuranceEmployeeController(Employee employee) {
        this.employee = employee;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ResultSet rs = PolicyOwnerModel.getInstance().getDatabaseDriver().getAccountDataById(employee.getId());
        String username = null, password = null;
        try {
            if (rs.next()) {
                username = rs.getString("username");
                password = rs.getString("password");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        nameTxtField.setText(employee.getFullName());
        pwdTxtField.setText(password);
        phoneTxtField.setText(employee.getPhone());
        emailTxtField.setText(employee.getEmail());
        addressTxtField.setText(employee.getAddress());
        String finalUsername = username;
        saveBtn.setOnAction(actionEvent -> {
            AdminModel.getInstance().getDatabaseDriver().updateAccountData(finalUsername, pwdTxtField.getText(), employee.getId());
            AdminModel.getInstance().getDatabaseDriver().updateEmployee(employee.getId(), nameTxtField.getText(), phoneTxtField.getText(), emailTxtField.getText(), addressTxtField.getText());
            System.out.println(employee.getId());
            if (employee.getId().contains("s")) {
                AdminModel.getInstance().updateInsuranceSurveyor(employee.getId(), nameTxtField.getText(), phoneTxtField.getText(), emailTxtField.getText(), addressTxtField.getText());
            } else {
                AdminModel.getInstance().updateInsuranceManager(employee.getId(), nameTxtField.getText(), phoneTxtField.getText(), emailTxtField.getText(), addressTxtField.getText());
            }
            AdminModel.getInstance().getAdminViewFactory().closeCurrentSubStage();
            AdminModel.getInstance().getDatabaseDriver().recordActivityHistory("UPDATE EMPLOYEE INFORMATION OF " + employee.getId(), "Admin");
        });
    }
}
