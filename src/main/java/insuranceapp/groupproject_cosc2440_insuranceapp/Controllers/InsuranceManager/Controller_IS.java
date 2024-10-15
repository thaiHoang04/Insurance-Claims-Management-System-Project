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
import insuranceapp.groupproject_cosc2440_insuranceapp.Models.InsuranceManager.InsuranceSurveyor;
import insuranceapp.groupproject_cosc2440_insuranceapp.Views.InsuranceManager.SurveyorCell;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class Controller_IS implements Initializable {
    public Label enterSearch;
    public TextField searchIS;
    public ListView<InsuranceSurveyor> iS_listview;
    private ObservableList<InsuranceSurveyor> insuranceSurveyors = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        iS_listview.setItems(getSurveyor());
        iS_listview.setCellFactory(e -> new SurveyorCell());
        setSearchButton();
    }

    public ObservableList<InsuranceSurveyor> getSurveyor() {
        try {
            Database.setConnectionDatabase();
            Statement statement = Database.connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = statement.executeQuery(String.format("SELECT * FROM insurance_employees"));
            while (rs.next()) {
                String full_name = rs.getString("full_name");
                String phone = rs.getString("phone");
                String address = rs.getString("address");
                String eid = rs.getString("id");
                String email = rs.getString("email");

                InsuranceSurveyor insuranceSurveyor = new InsuranceSurveyor(eid, full_name, phone, address, email);
                insuranceSurveyors.add(insuranceSurveyor);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return insuranceSurveyors;
    }

    public void searchSurveyor(String string) {
        FilteredList<InsuranceSurveyor> filteredList = insuranceSurveyors.filtered(surveyor -> surveyor.eIDProperty().get().toLowerCase().contains(string.toLowerCase())
                || surveyor.fullNameProperty().get().toLowerCase().contains(string.toLowerCase()));
        setListViewSurveyorFilter(filteredList);
    }

    public void setListViewSurveyorFilter(FilteredList<InsuranceSurveyor> surveyorList) {
        iS_listview.setItems(surveyorList);
        iS_listview.setCellFactory(e -> new SurveyorCell());
    }

    public void setSearchButton() {
        enterSearch.setOnMouseClicked(mouseEvent -> {
            searchSurveyor(searchIS.getText());
        });
    }
}
