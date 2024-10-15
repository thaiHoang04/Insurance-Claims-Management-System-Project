/**
 * @author Group 14
 */
package insuranceapp.groupproject_cosc2440_insuranceapp.Controllers.InsuranceSurveyor;

import insuranceapp.groupproject_cosc2440_insuranceapp.Models.*;
import insuranceapp.groupproject_cosc2440_insuranceapp.Models.InsuranceManager.Model;
import insuranceapp.groupproject_cosc2440_insuranceapp.Views.InsuranceSurveyor.InsuranceSurveyorClaimDetailView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class ClaimCard implements Initializable {
    @FXML
    private Label id;

    @FXML
    private Label insuredPerson;

    @FXML
    private Label claimDate;

    @FXML
    private Label examDate;

    @FXML
    private Label bankInfo;

    @FXML
    private Label status;

    private Claim claim;

    @FXML
    private VBox claimCardActionRoot;

    @FXML
    private Button more_info;

    @FXML
    private Button propose_claim;

    @FXML
    private Label view_more;

    public ClaimCard (Claim claim) {
        this.claim = claim;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateClaimCardCell();
    }

    public void updateClaimCardCell() {
        id.textProperty().bind(claim.idProperty());
        insuredPerson.textProperty().bind(claim.insuredPersonProperty());
        claimDate.textProperty().bind(claim.claimDateProperty().asString());
        examDate.textProperty().bind(claim.examDateProperty().asString());
        bankInfo.textProperty().bind(claim.receiverBankingInfoProperty());
        status.textProperty().bind(claim.statusProperty());

        view_more.setOnMouseClicked(mouseEvent -> {
            onButtonViewClick(claim);
        });

        if (claim.getStatus().equalsIgnoreCase("new") || claim.getStatus().equalsIgnoreCase("update") ) {
            more_info.setOnMouseClicked(mouseEvent -> {
                onButtonRequireClick(claim);
            });

            propose_claim.setOnMouseClicked(mouseEvent -> {
                onButtonProposeClick(claim);
            });
        } else {
            claimCardActionRoot.getChildren().removeAll(claimCardActionRoot.getChildren());
        }

    }

    public void onButtonRequireClick(Claim claim) {
        System.out.println("require click " + claim.getId());
        updateClaimFromDatabase(claim, "require");
        claimCardActionRoot.getChildren().removeAll(claimCardActionRoot.getChildren());
    }

    public void onButtonProposeClick(Claim claim) {
        System.out.println("propose click " + claim.getId());
        updateClaimFromDatabase(claim, "pending");
        claimCardActionRoot.getChildren().removeAll(claimCardActionRoot.getChildren());
    }

    public void onButtonViewClick(Claim claim){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/InsuranceSurveyorClaim/InsuranceSurveyorClaimDetailView.fxml"));
            loader.setController(new InsuranceSurveyorClaimDetailView(claim));
            Stage stage = new Stage();
            stage.setTitle("Claim Detail of claim " + claim.getId());
            stage.setScene(new Scene(loader.load()));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            System.out.println("Error button view");
            e.printStackTrace();
        }
    }

    public void updateClaimFromDatabase(Claim claim, String status) {
        try {
            Database db = new Database();
            String oldStatus = claim.getStatus();
            Statement stm = db.getCon().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            int update = stm.executeUpdate(String.format("UPDATE claims set status='%s' WHERE fid='%s'", status.toUpperCase(), claim.getId()));
            claim.statusProperty().set(status.toUpperCase());
            DatabaseDriver dbDriver = new DatabaseDriver();
            dbDriver.recordActivityHistory(String.format("UPDATE CLAIM ID: '%s' WITH STATUS FROM '%s' TO '%s' by INSURANCE SURVEYOR", claim.getId(), oldStatus, claim.getStatus()), EmployeeModel.getInstance().getId());
        } catch(SQLException e) {
            System.out.println("getDocumentList Failed" + e.getMessage());
        }
    }
}
