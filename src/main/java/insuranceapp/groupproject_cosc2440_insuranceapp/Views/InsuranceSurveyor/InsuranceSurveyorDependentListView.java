package insuranceapp.groupproject_cosc2440_insuranceapp.Views.InsuranceSurveyor;

import insuranceapp.groupproject_cosc2440_insuranceapp.Models.Database;
import insuranceapp.groupproject_cosc2440_insuranceapp.Models.Dependent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class InsuranceSurveyorDependentListView implements Initializable {
    @FXML
    private ListView listView;

    @FXML
    private TextField searchField;

    @FXML
    private Label enterSearch;

    private ObservableList<Dependent> dependentsList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setListViewDependent();
        setSearchButton();
    }

    public ObservableList<Dependent> getDependentList() throws SQLException {
        try {
            Database db = new Database();
            Statement stm = db.getCon().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stm.executeQuery("SELECT * FROM dependents ");
            while(rs.next()) {
                Dependent dependent = new Dependent(rs.getString("did"), rs.getString("full_name"), rs.getString("insurance_card"), rs.getString("phone_number"), rs.getString("email"), rs.getString("address"), rs.getString("pid"), rs.getString("poid"));
                dependentsList.add(dependent);
            }
            return dependentsList;
        } catch(SQLException e) {
            System.out.println("getDependentList Failed" + e.getMessage());
            return null;
        }
    }

    public void setListViewDependent() {
        try {
            listView.setItems(getDependentList());
            listView.setCellFactory(e -> new InsuranceSurveyorDependentCardView());
        } catch (SQLException e) {
            System.out.println("ListViewDependent error: " + e.getMessage());
        }
    }

    public void setSearchButton() {
        enterSearch.setOnMouseClicked(mouseEvent -> {
            searchDependentByIdOrName(searchField.getText());
        });
    }

    public void searchDependentByIdOrName(String string) {
        FilteredList<Dependent> filteredList = dependentsList.filtered(dependent -> dependent.getId().toLowerCase().contains(string.toLowerCase()) || dependent.getFullName().toLowerCase().contains(string.toLowerCase()));
        setListViewDependentFilter(filteredList);
    }

    public void setListViewDependentFilter(FilteredList<Dependent> dependentsList) {
        listView.setItems(dependentsList);
        listView.setCellFactory(e -> new InsuranceSurveyorDependentCardView());
    }
}
