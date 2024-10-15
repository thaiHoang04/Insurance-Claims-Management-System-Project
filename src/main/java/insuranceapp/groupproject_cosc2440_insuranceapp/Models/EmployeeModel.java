/**
 * @author Group 14
 */
package insuranceapp.groupproject_cosc2440_insuranceapp.Models;

public class EmployeeModel {

    private static EmployeeModel employeeModel;

    private String id;

    private String username;

    private String role;
    public EmployeeModel() {
        this.id = null;
        this.username = null;
        this.role = null;
    }

    public static EmployeeModel getInstance() {
        if (employeeModel == null) {
            employeeModel = new EmployeeModel();
        }
        return employeeModel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmployeeModel(String id, String username, String role) {
        this.id = id;
        this.username = username;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }
}
