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
import insuranceapp.groupproject_cosc2440_insuranceapp.Models.InsuranceManager.PolicyHolder;
import insuranceapp.groupproject_cosc2440_insuranceapp.Views.InsuranceManager.PolicyHolderCell;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class Controller_PH implements Initializable {
    public Label enterSearch;
    public TextField searchPH;
    public ListView<PolicyHolder> pH_listview;
    private ObservableList<PolicyHolder> policyHolders = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pH_listview.setItems(getPolicyHolder());
        pH_listview.setCellFactory(e -> new PolicyHolderCell());
        setSearchButton();
    }

    public ObservableList<PolicyHolder> getPolicyHolder() {
        try {
            Database.setConnectionDatabase();
            Statement statement = Database.connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = statement.executeQuery(String.format("SELECT * FROM policy_holders"));
            while (rs.next()) {
                String full_name = rs.getString("full_name");
                String pid = rs.getString("pid");
                String insurance_card = rs.getString("insurance_card");
                String email = rs.getString("email");
                String address = rs.getString("address");
                String phone_number = rs.getString("phone_number");
                String policy_owner = rs.getString("poid");

                PolicyHolder policyHolder = new PolicyHolder(pid, full_name, email, address, phone_number, policy_owner, insurance_card);
                policyHolders.add(policyHolder);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return policyHolders;
    }


    public void searchPolicyHolder(String string) {
        FilteredList<PolicyHolder> filteredList = policyHolders.filtered(policyHolder -> policyHolder.fullNameProperty().get().toLowerCase().contains(string.toLowerCase())
                || policyHolder.customerIDProperty().get().toLowerCase().contains(string.toLowerCase()));
        setListViewPolicyHolderFilter(filteredList);
    }

    public void setListViewPolicyHolderFilter(FilteredList<PolicyHolder> policyHolderList) {
        pH_listview.setItems(policyHolderList);
        pH_listview.setCellFactory(e -> new PolicyHolderCell());
    }

    public void setSearchButton() {
        enterSearch.setOnMouseClicked(mouseEvent -> {
            searchPolicyHolder(searchPH.getText());
        });
    }
}
