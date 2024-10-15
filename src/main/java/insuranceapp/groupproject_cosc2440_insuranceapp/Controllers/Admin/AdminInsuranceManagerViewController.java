/**
 * @author Group 14
 */
package insuranceapp.groupproject_cosc2440_insuranceapp.Controllers.Admin;

import insuranceapp.groupproject_cosc2440_insuranceapp.Models.AdminModel;
import insuranceapp.groupproject_cosc2440_insuranceapp.Models.Employee;
import insuranceapp.groupproject_cosc2440_insuranceapp.Views.InsuranceMangerCellFactory;
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

public class AdminInsuranceManagerViewController implements Initializable {
    public Label numberOfInsuranceManager;
    public ListView<Employee> insuranceManager_listview;
    public TextField searchBar;
    public Button addNewInsuranceManager;
    public Label noInsuranceManagerLbl;

    private Executor executor;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initData();
        AdminModel.getInstance().getAdminViewFactory().setAdminInsuranceManagerViewController(this);
        numberOfInsuranceManager.setText(String.valueOf(AdminModel.getInstance().getInsuranceManagers().size()));
        insuranceManager_listview.setItems(AdminModel.getInstance().getInsuranceManagers());
        insuranceManager_listview.setCellFactory(insuranceManagerListView -> new InsuranceMangerCellFactory());
        addNewInsuranceManager.setOnAction(actionEvent -> AdminModel.getInstance().getAdminViewFactory().showAddNewInsuranceManager());
        searchBar.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String string, String t1) {
                if (!t1.isEmpty()) {
                    searchInsuranceManager(t1);
                } else {
                    initData();
                    numberOfInsuranceManager.setVisible(false);
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
        if (AdminModel.getInstance().getInsuranceManagers().isEmpty()) {
            AdminModel.getInstance().setInsuranceManagers();
        }
    }

    public void searchInsuranceManager(String searchString) {
        Task<List<Employee>> insuranceSurveyorSearchTask = new Task<List<Employee>>() {
            @Override
            public List<Employee> call() throws Exception {
                return AdminModel.getInstance().getDatabaseDriver().searchListInsuranceManagerWithId(searchString);
            }
        };

        insuranceSurveyorSearchTask.setOnFailed(e -> {
            insuranceSurveyorSearchTask.getException().printStackTrace();
        });

        insuranceSurveyorSearchTask.setOnSucceeded(e -> {
            if (insuranceSurveyorSearchTask.getValue().isEmpty()) {
                numberOfInsuranceManager.setVisible(true);
            } else {
                numberOfInsuranceManager.setVisible(false);
            }
            insuranceManager_listview.getItems().setAll(insuranceSurveyorSearchTask.getValue());
        });

        executor.execute(insuranceSurveyorSearchTask);
    }
}
