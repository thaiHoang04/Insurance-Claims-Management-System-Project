package insuranceapp.groupproject_cosc2440_insuranceapp.Views.InsuranceSurveyor;

import insuranceapp.groupproject_cosc2440_insuranceapp.Models.Database;
import insuranceapp.groupproject_cosc2440_insuranceapp.Models.PolicyHolder;
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

public class InsuranceSurveyorPolicyHolderView implements Initializable {
    @FXML
    private ListView listView;

    @FXML
    private TextField searchField;

    @FXML
    private Label enterSearch;

    private ObservableList<PolicyHolder> policyHolderList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setListViewPolicyHolder();
        setSearchButton();
    }

    public ObservableList<PolicyHolder> getPolicyHolderList() throws SQLException {
        try {
            Database db = new Database();
            Statement stm = db.getCon().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stm.executeQuery("SELECT * FROM policy_holders ");
            while(rs.next()) {
                PolicyHolder policyHolder = new PolicyHolder(rs.getString("pid"), rs.getString("full_name"), rs.getString("insurance_card"), rs.getString("phone_number"), rs.getString("email"), rs.getString("address"), rs.getString("poid"));
                policyHolderList.add(policyHolder);
            }
            return policyHolderList;
        } catch(SQLException e) {
            System.out.println("getPolicyHolderList Failed" + e.getMessage());
            return null;
        }
    }

    public void setListViewPolicyHolder() {
        try {
            listView.setItems(getPolicyHolderList());
            listView.setCellFactory(e -> new InsuranceSurveyorPolicyHolderCardView());
        } catch (SQLException e) {
            System.out.println("ListViewPolicyHolder error: " + e.getMessage());
        }
    }

    public void setSearchButton() {
        enterSearch.setOnMouseClicked(mouseEvent -> {
            searchPolicyHolderByIdOrName(searchField.getText());
        });
    }

    public void searchPolicyHolderByIdOrName(String string) {
        FilteredList<PolicyHolder> filteredList = policyHolderList.filtered(policyHolder -> policyHolder.getId().toLowerCase().contains(string.toLowerCase()) || policyHolder.getFullName().toLowerCase().contains(string.toLowerCase()));
        setListViewPolicyHolderFilter(filteredList);
    }

    public void setListViewPolicyHolderFilter(FilteredList<PolicyHolder> policyHolderList) {
        listView.setItems(policyHolderList);
        listView.setCellFactory(e -> new InsuranceSurveyorPolicyHolderCardView());
    }
}
