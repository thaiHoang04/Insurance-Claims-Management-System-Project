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
import insuranceapp.groupproject_cosc2440_insuranceapp.Models.InsuranceManager.Dependent;
import insuranceapp.groupproject_cosc2440_insuranceapp.Views.InsuranceManager.DependentCell;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class Controller_DP implements Initializable {
    public Label enterSearch;
    public TextField searchDP;
    public ListView<Dependent> dP_listview;
    private ObservableList<Dependent> dependents = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dP_listview.setItems(getDependent());
        dP_listview.setCellFactory(e -> new DependentCell());
        setSearchButton();
    }

    public ObservableList<Dependent> getDependent() {
        try {
            Database.setConnectionDatabase();
            Statement statement = Database.connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = statement.executeQuery(String.format("SELECT * FROM dependents"));
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


    public void searchDependent(String string) {
        FilteredList<Dependent> filteredList = dependents.filtered(dependent -> dependent.customerIDProperty().get().toLowerCase().contains(string.toLowerCase())
                || dependent.fullNameProperty().get().toLowerCase().contains(string.toLowerCase()));
        setListViewDependentFilter(filteredList);
    }

    public void setListViewDependentFilter(FilteredList<Dependent> dependentList) {
        dP_listview.setItems(dependentList);
        dP_listview.setCellFactory(e -> new DependentCell());
    }

    public void setSearchButton() {
        enterSearch.setOnMouseClicked(mouseEvent -> {
            searchDependent(searchDP.getText());
            System.out.println("!");
        });
    }
}
