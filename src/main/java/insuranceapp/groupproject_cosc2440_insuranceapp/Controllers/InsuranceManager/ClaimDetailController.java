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
import insuranceapp.groupproject_cosc2440_insuranceapp.Views.InsuranceManager.DocumentCell;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class ClaimDetailController implements Initializable {
    public Label claimID;
    public Label insuredPerson;
    public Label cardID;
    public Label status;
    public Label amount;
    public Label claimDate;
    public Label examDate;
    public Label bankingInfo;

    public ListView<Claim> documentList;
    private static Claim claim;

    public ClaimDetailController(Claim claim) {
        ClaimDetailController.claim = claim;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setValue();
        documentList.setItems(getDocument());
        documentList.setCellFactory(e -> new DocumentCell());
    }

    public static ObservableList<Claim> getDocument() {
        ObservableList<Claim> claims = FXCollections.observableArrayList();
        try {
            Database.setConnectionDatabase();
            Statement statement = Database.connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = statement.executeQuery(String.format("SELECT * FROM uploaded_file WHERE fid = '%s'", claim.claimIDProperty().get()));
            while (rs.next()) {
                String document_name = rs.getString("name");
                claim.documentProperty().set(document_name);
                claims.add(claim);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return claims;
    }

    private void setValue() {
        claimID.textProperty().bind(claim.claimIDProperty());
        insuredPerson.textProperty().bind(claim.insuredPersonProperty());
        cardID.textProperty().bind(claim.cardIDProperty());
        status.textProperty().bind(claim.statusProperty());
        amount.textProperty().bind(claim.amountProperty());
        claimDate.textProperty().bind(claim.claimDateProperty().asString());
        examDate.textProperty().bind(claim.examDateProperty().asString());
        bankingInfo.textProperty().bind(claim.bankingInfoProperty());
    }
}
