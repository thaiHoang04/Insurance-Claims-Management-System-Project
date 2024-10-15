/**
 * @author Group 14
 */
package insuranceapp.groupproject_cosc2440_insuranceapp.Controllers.InsuranceManager;

import insuranceapp.groupproject_cosc2440_insuranceapp.Models.DatabaseDriver;
import insuranceapp.groupproject_cosc2440_insuranceapp.Models.EmployeeModel;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import insuranceapp.groupproject_cosc2440_insuranceapp.Models.InsuranceManager.Database;
import insuranceapp.groupproject_cosc2440_insuranceapp.Models.InsuranceManager.Claim;
import insuranceapp.groupproject_cosc2440_insuranceapp.Views.InsuranceManager.View;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import static insuranceapp.groupproject_cosc2440_insuranceapp.Views.InsuranceManager.View.createStage;

public class ClaimCellController implements Initializable {
    public Label claimID;
    public Label insuredPerson;
    public Label cardID;
    public Label status;
    public Label amount;

    public Button acceptClaim;
    public Button rejectClaim;
    public Button viewClaim;
    private final Claim claim;

    public ClaimCellController(Claim claim) {
        this.claim = claim;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (claim.statusProperty().get().equals("PENDING")) {
            acceptClaim.setVisible(true);
            rejectClaim.setVisible(true);
        } else {
            acceptClaim.setVisible(false);
            rejectClaim.setVisible(false);
        }
        acceptClaim.setOnAction(event -> acceptClaim());
        rejectClaim.setOnAction(event -> rejectClaim());
        viewClaim.setOnAction(event -> viewClaim(claim));
        setValue();
    }


    private void viewClaim(Claim claim) {
        ClaimDetailController claimDetailController = new ClaimDetailController(claim);
        FXMLLoader loader = new FXMLLoader(View.class.getResource("/FXML/InsuranceManager/ClaimDetail.fxml"));
        loader.setController(claimDetailController);
        createStage(loader);
    }

    private void acceptClaim() {
        try {
            Database.setConnectionDatabase();
            Statement statement = Database.connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            statement.executeUpdate(String.format("UPDATE claims SET status = 'APPROVED' WHERE fid = '%s' AND status = 'PENDING'", this.claim.claimIDProperty().get()));
            DatabaseDriver dbDriver = new DatabaseDriver();
            dbDriver.recordActivityHistory(String.format("APPROVE CLAIM ID: ‘%s’ BY INSURANCE MANAGER", this.claim.getClaimID(), this.claim.statusProperty().get()), EmployeeModel.getInstance().getId());
            this.claim.statusProperty().set("APPROVED");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void rejectClaim() {
        try {
            Database.setConnectionDatabase();
            Statement statement = Database.connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            statement.executeUpdate(String.format("UPDATE claims SET status = 'REJECTED' WHERE fid = '%s' AND status = 'PENDING'", this.claim.claimIDProperty().get()));
            DatabaseDriver dbDriver = new DatabaseDriver();
            dbDriver.recordActivityHistory(String.format("REJECT CLAIM ID: ‘%s’ BY INSURANCE MANAGER", this.claim.getClaimID(), this.claim.statusProperty().get()), EmployeeModel.getInstance().getId());
            this.claim.statusProperty().set("REJECTED");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setValue() {
        claimID.textProperty().bind(claim.claimIDProperty());
        insuredPerson.textProperty().bind(claim.insuredPersonProperty());
        cardID.textProperty().bind(claim.cardIDProperty());
        status.textProperty().bind(claim.statusProperty());
        amount.textProperty().bind(claim.amountProperty());
    }
}

