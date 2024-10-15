package insuranceapp.groupproject_cosc2440_insuranceapp.Views.InsuranceSurveyor;

import insuranceapp.groupproject_cosc2440_insuranceapp.Models.Database;
import insuranceapp.groupproject_cosc2440_insuranceapp.Models.PolicyOwner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class InsuranceSurveyorPolicyOwnerListView implements Initializable {
    @FXML
    private ListView listView;

    @FXML
    private TextField searchField;

    @FXML
    private Label enterSearch;

    private ObservableList<PolicyOwner> policyOwnersList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setListViewPolicyOwner();
        setSearchButton();
    }

    public ObservableList<PolicyOwner> getPolicyOwnerList() throws SQLException {
        try {
            Database db = new Database();
            Statement stm = db.getCon().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stm.executeQuery("SELECT * FROM policy_owners");
            while(rs.next()) {
                PolicyOwner policyOwner = new PolicyOwner(rs.getString("poid"), rs.getString("name"), Double.parseDouble(rs.getString("fee").replaceAll("[^0-9+-]", "")));
                policyOwnersList.add(policyOwner);
            }
            return policyOwnersList;
        } catch(SQLException e) {
            System.out.println("getPolicyOwnerList Failed" + e.getMessage());
            return null;
        }
    }

    public void setListViewPolicyOwner() {
        try {
            listView.setItems(getPolicyOwnerList());
            listView.setCellFactory(e -> new InsuranceSurveyorPolicyOwnerCardView());
        } catch (SQLException e) {
            System.out.println("ListViewPolicyOwner error: " + e.getMessage());
        }
    }

    public void setSearchButton() {
        enterSearch.setOnMouseClicked(mouseEvent -> {
            searchPolicyOwnerByIdOrName(searchField.getText());
        });
    }

    public void searchPolicyOwnerByIdOrName(String string) {
        FilteredList<PolicyOwner> filteredList = policyOwnersList.filtered(policyOwner -> policyOwner.getId().toLowerCase().contains(string.toLowerCase()) || policyOwner.getName().toLowerCase().contains(string.toLowerCase()));
        setListViewPolicyOwnerFilter(filteredList);
    }

    public void setListViewPolicyOwnerFilter(FilteredList<PolicyOwner> policyOwnersList) {
        listView.setItems(policyOwnersList);
        listView.setCellFactory(e -> new InsuranceSurveyorPolicyOwnerCardView());
    }
}
