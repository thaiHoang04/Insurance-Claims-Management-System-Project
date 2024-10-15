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
import insuranceapp.groupproject_cosc2440_insuranceapp.Models.InsuranceManager.Claim;
import insuranceapp.groupproject_cosc2440_insuranceapp.Models.InsuranceManager.Dependent;
import insuranceapp.groupproject_cosc2440_insuranceapp.Models.InsuranceManager.PolicyHolder;
import insuranceapp.groupproject_cosc2440_insuranceapp.Views.InsuranceManager.DetailClaimCell;
import insuranceapp.groupproject_cosc2440_insuranceapp.Views.InsuranceManager.DetailDependentCell;

import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class PolicyHolderDetailController implements Initializable {
    public ListView<Claim> policyHolderClaimList;
    public ListView<Dependent> policyHolderDPList;
    public Label pID;
    public Label name;
    public Label policyOwner;
    public Label cardID;
    public Label address;
    public Label phone;
    public Label email;
    private static PolicyHolder policyHolder;

    public PolicyHolderDetailController(PolicyHolder policyHolder) {
        PolicyHolderDetailController.policyHolder = policyHolder;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setValue();
        policyHolderClaimList.setItems(getClaim());
        policyHolderClaimList.setCellFactory(e -> new DetailClaimCell());

        policyHolderDPList.setItems(getDependent());
        policyHolderDPList.setCellFactory(e -> new DetailDependentCell());
    }

    private void setValue() {
        pID.textProperty().bind(policyHolder.customerIDProperty());
        name.textProperty().bind(policyHolder.fullNameProperty());
        cardID.textProperty().bind(policyHolder.insuranceCardProperty());
        address.textProperty().bind(policyHolder.addressProperty());
        phone.textProperty().bind(policyHolder.phoneNumberProperty());
        email.textProperty().bind(policyHolder.emailProperty());
        policyOwner.textProperty().bind(policyHolder.policyOwnerProperty());
    }

    public static ObservableList<Claim> getClaim() {
        ObservableList<Claim> claims = FXCollections.observableArrayList();
        try {
            Database.setConnectionDatabase();
            Statement statement = Database.connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = statement.executeQuery(String.format("SELECT * FROM claims WHERE insured_person = '%s'", policyHolder.customerIDProperty().get()));
            while (rs.next()) {
                String fid = rs.getString("fid");
                String cid = rs.getString("insured_person");
                String card_number = rs.getString("card_number");
                String claim_amount = rs.getString("claim_amount");
                String status = rs.getString("status");
                Date exam_date = rs.getDate("exam_date");
                Date claim_date = rs.getDate("claim_date");

                Claim claim = new Claim(fid, cid, card_number, claim_amount, status, exam_date, claim_date, null, null);
                claims.add(claim);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return claims;
    }

    public static ObservableList<Dependent> getDependent() {
        ObservableList<Dependent> dependents = FXCollections.observableArrayList();
        try {
            Database.setConnectionDatabase();
            Statement statement = Database.connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = statement.executeQuery(String.format("SELECT * FROM dependents WHERE pid = '%s'", policyHolder.customerIDProperty().get()));
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