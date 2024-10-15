/**
 * @author Group 14
 */
package insuranceapp.groupproject_cosc2440_insuranceapp.Controllers.InsuranceManager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import insuranceapp.groupproject_cosc2440_insuranceapp.Models.InsuranceManager.Database;
import insuranceapp.groupproject_cosc2440_insuranceapp.Models.InsuranceManager.Claim;
import insuranceapp.groupproject_cosc2440_insuranceapp.Views.InsuranceManager.ClaimCell;

import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class Controller_Claim implements Initializable {
    public Label enterSearch;
    public TextField searchClaim;
    public ListView<Claim> claimListView;
    private ObservableList<Claim> claims = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        claimListView.setItems(getClaim());
        claimListView.setCellFactory(e -> new ClaimCell());
        setSearchButton();
    }

    public ObservableList<Claim> getClaim() {
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
                String bankingInfo = rs.getString("receiver_banking_info");

                Claim claim = new Claim(fid, cid, card_number, claim_amount, status, exam_date, claim_date, null, bankingInfo);
                claims.add(claim);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return claims;
    }

    public void searchClaim(String string) {
        FilteredList<Claim> filteredList = claims.filtered(claim -> claim.claimIDProperty().get().toLowerCase().contains(string.toLowerCase())
                || claim.statusProperty().get().toLowerCase().contains(string.toLowerCase()));
        setListViewClaimFilter(filteredList);
    }

    public void setListViewClaimFilter(FilteredList<Claim> claimList) {
        claimListView.setItems(claimList);
        claimListView.setCellFactory(e -> new ClaimCell());
    }

    public void setSearchButton() {
        enterSearch.setOnMouseClicked(mouseEvent -> {
            searchClaim(searchClaim.getText());
        });
    }
}
