/**
 * @author Group 14
 */
package insuranceapp.groupproject_cosc2440_insuranceapp.Controllers.Admin;

import insuranceapp.groupproject_cosc2440_insuranceapp.Models.AdminModel;
import insuranceapp.groupproject_cosc2440_insuranceapp.Models.Employee;
import insuranceapp.groupproject_cosc2440_insuranceapp.Views.InsuranceSurveyorCellFactory;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AdminInsuranceSurveyorViewController implements Initializable {
    public Label noInsuranceSurveyorLbl;
    public Button addNewInsuranceSurveyor;
    public TextField searchBar;
    public ListView<Employee> insuranceSurveyor_listview;
    public Label numberOfInsuranceSurveyor;

    private Executor executor;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initData();
        AdminModel.getInstance().getAdminViewFactory().setAdminInsuranceSurveyorViewController(this);
        numberOfInsuranceSurveyor.setText(String.valueOf(AdminModel.getInstance().getInsuranceSurveyors().size()));
        insuranceSurveyor_listview.setItems(AdminModel.getInstance().getInsuranceSurveyors());
        insuranceSurveyor_listview.setCellFactory(c -> new InsuranceSurveyorCellFactory());
        addNewInsuranceSurveyor.setOnAction(actionEvent -> AdminModel.getInstance().getAdminViewFactory().showAddNewInsuranceSurveyor());
        searchBar.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String string, String t1) {
                if (!t1.isEmpty()) {
                    searchInsuranceSurveyor(t1);
                } else {
                    initData();
                    noInsuranceSurveyorLbl.setVisible(false);
                }
            }
        });
        executor = Executors.newCachedThreadPool(r -> {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t;
        });
    }

    public void initData() {
        if (AdminModel.getInstance().getInsuranceSurveyors().isEmpty()) {
            AdminModel.getInstance().setInsuranceSurveyors();
        }
    }

    public void searchInsuranceSurveyor(String searchString) {
        Task<List<Employee>> insuranceSurveyorSearchTask = new Task<List<Employee>>() {
            @Override
            public List<Employee> call() throws Exception {
                return AdminModel.getInstance().getDatabaseDriver().searchListInsuranceSurveyorWithId(searchString);
            }
        };

        insuranceSurveyorSearchTask.setOnFailed(e -> {
            insuranceSurveyorSearchTask.getException().printStackTrace();
        });

        insuranceSurveyorSearchTask.setOnSucceeded(e -> {
            if (insuranceSurveyorSearchTask.getValue().isEmpty()) {
                noInsuranceSurveyorLbl.setVisible(true);
            } else {
                noInsuranceSurveyorLbl.setVisible(false);
            }
            insuranceSurveyor_listview.getItems().setAll(insuranceSurveyorSearchTask.getValue());
        });

        executor.execute(insuranceSurveyorSearchTask);
    }
}
