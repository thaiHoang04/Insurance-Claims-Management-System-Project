/**
 * @author Group 14
 */
package insuranceapp.groupproject_cosc2440_insuranceapp.Controllers.InsuranceManager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import insuranceapp.groupproject_cosc2440_insuranceapp.Models.InsuranceManager.Database;
import insuranceapp.groupproject_cosc2440_insuranceapp.Models.InsuranceManager.Dependent;
import insuranceapp.groupproject_cosc2440_insuranceapp.Models.InsuranceManager.PolicyHolder;
import insuranceapp.groupproject_cosc2440_insuranceapp.Models.InsuranceManager.PolicyOwner;
import insuranceapp.groupproject_cosc2440_insuranceapp.Views.InsuranceManager.DetailDependentCell;
import insuranceapp.groupproject_cosc2440_insuranceapp.Views.InsuranceManager.DetailPolicyHolderCell;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class PolicyOwnerDetailController implements Initializable {
    public Label poid;
    public Label name;
    public Label fee;
    public static PolicyOwner policyOwner;
    public ListView<PolicyHolder> policyHolderList;
    public ListView<Dependent> DependentList;

    public PolicyOwnerDetailController(PolicyOwner policyOwner) {
        PolicyOwnerDetailController.policyOwner = policyOwner;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setValue();
        policyHolderList.setItems(getPolicyHolder());
        policyHolderList.setCellFactory(e -> new DetailPolicyHolderCell());
        DependentList.setItems(getDependent());
        DependentList.setCellFactory(e -> new DetailDependentCell());
    }

    public void setValue() {
        poid.textProperty().bind(policyOwner.POIDProperty());
        name.textProperty().bind(policyOwner.nameProperty());
        fee.textProperty().bind(policyOwner.feeProperty());
    }

    public static ObservableList<PolicyHolder> getPolicyHolder() {
        ObservableList<PolicyHolder> policyHolders = FXCollections.observableArrayList();
        try {
            Database.setConnectionDatabase();
            Statement statement = Database.connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = statement.executeQuery(String.format("SELECT * FROM policy_holders WHERE poid = '%s'", policyOwner.POIDProperty().get()));
            while (rs.next()) {
                String full_name = rs.getString("full_name");
                String insurance_card = rs.getString("insurance_card");
                String email = rs.getString("email");
                String address = rs.getString("address");
                String phone_number = rs.getString("phone_number");
                String pid = rs.getString("pid");
                String poid = rs.getString("poid");

                PolicyHolder policyHolder = new PolicyHolder(pid, full_name, email, address, phone_number, poid, insurance_card);
                policyHolders.add(policyHolder);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return policyHolders;
    }

    public static ObservableList<Dependent> getDependent() {
        ObservableList<Dependent> dependents = FXCollections.observableArrayList();
        try {
            Database.setConnectionDatabase();
            Statement statement = Database.connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = statement.executeQuery(String.format("SELECT * FROM dependents WHERE poid = '%s'", policyOwner.POIDProperty().get()));
            while (rs.next()) {
                String full_name = rs.getString("full_name");
                String did = rs.getString("did");
                String insurance_card = rs.getString("insurance_card");
                String email = rs.getString("email");
                String address = rs.getString("address");
                String phone_number = rs.getString("phone_number");
                String policy_holder = rs.getString("pid");
                String policy_owner = rs.getString("poid");

                Dependent dependent = new Dependent(did, full_name, email, address, phone_number, insurance_card, policy_holder, policy_owner);
                dependents.add(dependent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dependents;
    }
}
