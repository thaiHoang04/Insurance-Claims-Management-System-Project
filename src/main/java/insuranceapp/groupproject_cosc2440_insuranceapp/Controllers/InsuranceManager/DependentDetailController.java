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
import insuranceapp.groupproject_cosc2440_insuranceapp.Views.InsuranceManager.ClaimCellDetail;

import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class DependentDetailController implements Initializable {
    public ListView<Claim> dependentClaimList;
    public Label dID;
    public Label name;
    public Label policyHolder;
    public Label cardID;
    public Label address;
    public Label phone;
    public Label email;
    public Label policyOwner;
    private Dependent dependent;

    public DependentDetailController() {
    }

    public void setDependent(Dependent dependent) {
        this.dependent = dependent;
    }

    public DependentDetailController(Dependent dependent) {
        this.dependent = dependent;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setValue();
        ObservableList<Claim> claims = getDependentClaim(dependent);
        dependentClaimList.setItems(claims);
        dependentClaimList.setCellFactory(e -> new ClaimCellDetail());
    }

    private void setValue() {
        dID.textProperty().bind(dependent.customerIDProperty());
        name.textProperty().bind(dependent.fullNameProperty());
        policyHolder.textProperty().bind(dependent.policyHolderProperty());
        cardID.textProperty().bind(dependent.insuranceCardProperty());
        address.textProperty().bind(dependent.addressProperty());
        phone.textProperty().bind(dependent.phoneNumberProperty());
        email.textProperty().bind(dependent.emailProperty());
        policyOwner.textProperty().bind(dependent.policyOwnerProperty());
    }

    public static ObservableList<Claim> getClaim() {
        ObservableList<Claim> claims = FXCollections.observableArrayList();
        try {
            Database.setConnectionDatabase();
            Statement statement = Database.connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = statement.executeQuery(String.format("SELECT * FROM claims"));
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

    public static ObservableList<Claim> getDependentClaim(Dependent dependent) {
        ObservableList<Claim> allClaims = FXCollections.observableArrayList();
        allClaims = getClaim();
        ObservableList<Claim> dependentClaims = FXCollections.observableArrayList();
        for (Claim claim:allClaims) {
            if(claim.insuredPersonProperty() != null && claim.insuredPersonProperty().get().equals(dependent.customerIDProperty().get())) {
                dependentClaims.add(claim);
            }
        }

        return dependentClaims;
    }
}
