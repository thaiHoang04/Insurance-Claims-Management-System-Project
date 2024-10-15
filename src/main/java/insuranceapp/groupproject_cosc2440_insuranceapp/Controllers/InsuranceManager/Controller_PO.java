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
import insuranceapp.groupproject_cosc2440_insuranceapp.Models.InsuranceManager.PolicyOwner;
import insuranceapp.groupproject_cosc2440_insuranceapp.Views.InsuranceManager.PolicyOwnerCell;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class Controller_PO implements Initializable {
    public Label enterSearch;
    public ListView<PolicyOwner> pO_listview;
    public TextField searchPO;
    private ObservableList<PolicyOwner> policyOwners = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pO_listview.setItems(getPolicyOwner());
        pO_listview.setCellFactory(e -> new PolicyOwnerCell());
        setSearchButton();
    }

    public ObservableList<PolicyOwner> getPolicyOwner() {
        try {
            Database.setConnectionDatabase();
            Statement statement = Database.connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = statement.executeQuery(String.format("SELECT * FROM policy_owners"));
            while (rs.next()) {
                String poid = rs.getString("poid");
                String name = rs.getString("name");
                String fee = rs.getString("fee");

                PolicyOwner policyOwner = new PolicyOwner(name, fee, poid);
                policyOwners.add(policyOwner);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return policyOwners;
    }

    public void searchPolicyOwner(String string) {
        FilteredList<PolicyOwner> filteredList = policyOwners.filtered(policyOwner -> policyOwner.nameProperty().get().toLowerCase().contains(string.toLowerCase())
                || policyOwner.POIDProperty().get().toLowerCase().contains(string.toLowerCase()));
        setListViewPolicyOwnerFilter(filteredList);
    }

    public void setListViewPolicyOwnerFilter(FilteredList<PolicyOwner> policyOwnerList) {
        pO_listview.setItems(policyOwnerList);
        pO_listview.setCellFactory(e -> new PolicyOwnerCell());
    }

    public void setSearchButton() {
        enterSearch.setOnMouseClicked(mouseEvent -> {
            searchPolicyOwner(searchPO.getText());
        });
    }

}
