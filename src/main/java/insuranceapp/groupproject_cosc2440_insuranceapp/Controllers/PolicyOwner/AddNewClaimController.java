/**
 * @author Group 14
 */
package insuranceapp.groupproject_cosc2440_insuranceapp.Controllers.PolicyOwner;

import insuranceapp.groupproject_cosc2440_insuranceapp.Models.*;
import insuranceapp.groupproject_cosc2440_insuranceapp.Models.Claim;
import insuranceapp.groupproject_cosc2440_insuranceapp.Models.PolicyHolder;
import javafx.concurrent.Task;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Line;
import javafx.stage.FileChooser;
import javafx.util.Callback;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AddNewClaimController implements Initializable {
    public HBox fileListContainer;
    public ComboBox<String> insuredPersonComboBox;
    public DatePicker claimDatePicker;
    public TextField cardNumTextField;
    public DatePicker examDatePicker;
    public TextField claimAmountTextField;
    public TextField bankNameTextField;
    public TextField bankNumTextField;
    public Button importFileBtn;
    public Button saveBtn;
    public Label errorLbl;

    public List<File> uploadedFileList;
    public Label saveBtnErrorLbl;

    private Executor executor;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initData();
        cardNumTextField.setDisable(true);
        if ((PolicyOwnerModel.getInstance().getPolicyOwner().getId() == null)) {
            for (Dependent dependent : PolicyHolderModel.getInstance().getDependents()) {
                insuredPersonComboBox.getItems().add(dependent.getFullName() + "\t|\t" + dependent.getId() + "\t|\t" + dependent.getInsuranceCard());
            }
            insuredPersonComboBox.getItems().add(PolicyHolderModel.getInstance().getPolicyHolder().getFullName() + "\t|\t" + PolicyHolderModel.getInstance().getPolicyHolder().getId() + "\t|\t" + PolicyHolderModel.getInstance().getPolicyHolder().getInsuranceCard());
        } else {
            for (PolicyHolder policyHolder : PolicyOwnerModel.getInstance().getPolicyHolders()) {
                insuredPersonComboBox.getItems().add(policyHolder.getFullName() + "\t|\t" + policyHolder.getId() + "\t|\t" + policyHolder.getInsuranceCard());
            }
            for (Dependent dependent : PolicyOwnerModel.getInstance().getDependents()) {
                insuredPersonComboBox.getItems().add(dependent.getFullName() + "\t|\t" + dependent.getId() + "\t|\t" + dependent.getInsuranceCard());
            }
        }
        insuredPersonComboBox.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> param) {
                return new ListCell<String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null || empty) {
                            setText(null);
                        } else {
                            setText(item);
                        }
                    }
                };
            }
        });
        insuredPersonComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                String cardNumber = newValue;
                cardNumber = cardNumber.substring(cardNumber.lastIndexOf("\t|\t") + 3);
                cardNumTextField.setText(cardNumber);
            }
        });
        saveBtn.setOnAction(event -> {
            if (checkEnoughInfo()) {
                String insuredPersonId = insuredPersonComboBox.getValue().substring(insuredPersonComboBox.getValue().indexOf("\t|\t") + 3, insuredPersonComboBox.getValue().lastIndexOf("\t|\t"));
                String claimId = PolicyOwnerModel.getInstance().generateClaimId();
                Claim newClaim = new Claim(claimId, claimDatePicker.getValue(), insuredPersonId, cardNumTextField.getText(), examDatePicker.getValue(), Double.valueOf(claimAmountTextField.getText()), "NEW", bankNameTextField.getText() + "_" + insuredPersonId + "_" + bankNumTextField.getText());
                if (PolicyOwnerModel.getInstance().getDatabaseDriver().insertNewClaim(newClaim)) {
                    if (uploadedFileList != null) {
                        uploadFile(uploadedFileList, newClaim.getId(), newClaim.getInsuranceCardNumber());
                    }
                    if ((PolicyOwnerModel.getInstance().getPolicyOwner().getId() == null)) {
                        PolicyHolderModel.getInstance().getClaims().add(newClaim);
                        PolicyHolderModel.getInstance().getDatabaseDriver().recordActivityHistory("INSERT NEW CLAIM FOR " + insuredPersonId, PolicyHolderModel.getInstance().getPolicyHolder().getId());
                        PolicyHolderModel.getInstance().updateClaimView();
                    } else {
                        PolicyOwnerModel.getInstance().getDatabaseDriver().recordActivityHistory("INSERT NEW CLAIM FOR " + insuredPersonId, PolicyOwnerModel.getInstance().getPolicyOwner().getId());
                        PolicyOwnerModel.getInstance().getPolicyOwnerViewFactory().updateClaimView();
                        PolicyOwnerModel.getInstance().getClaims().add(newClaim);
                    }
                    PolicyOwnerModel.getInstance().getPolicyOwnerViewFactory().closeCurrentSubStage();

                } else {
                    saveBtnErrorLbl.setText("Error: Cannot save the claim");
                }
                saveBtnErrorLbl.setText("");
            } else {
                saveBtnErrorLbl.setText("Error: Please fill in all the information");
            }
        });
        importFileBtn.setOnAction(actionEvent -> {
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("PDF file (*.pdf)", "*.pdf");
            fileChooser.getExtensionFilters().add(extensionFilter);
            List<File> fileList = fileChooser.showOpenMultipleDialog(null);
            if (!fileList.isEmpty()) {
                uploadedFileList = fileList;
                for (File file: fileList) {
                    fileListContainer.getChildren().add(new Label(file.getName()));
                    Line line = new Line(0, 4, 0, 32);
                    fileListContainer.getChildren().add(line);
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
        if (PolicyOwnerModel.getInstance().getPolicyHolders().isEmpty()) {
            PolicyOwnerModel.getInstance().setPolicyHolders();
        }
        if (PolicyOwnerModel.getInstance().getDependents().isEmpty()) {
            PolicyOwnerModel.getInstance().setDependents();
        }
    }

    public boolean checkEnoughInfo() {
        return !(insuredPersonComboBox.getValue() == null) && !(claimDatePicker.getValue() == null) && (cardNumTextField.getText().length() == 10) && cardNumTextField.getText().matches("\\d+") && !(examDatePicker.getValue() == null) && !(claimAmountTextField.getText().isEmpty()) && !(bankNameTextField.getText().isEmpty()) && !(bankNumTextField.getText().isEmpty());
    }

    public void uploadFile(List<File> fileList, String claimId, String insuranceCardNumber) {
        Task<Void> claimSearchTask = new Task<Void>() {
            @Override
            public Void call() throws Exception {
                for (File file : fileList) {
                    PolicyOwnerModel.getInstance().getDatabaseDriver().uploadFile(fileList, claimId, insuranceCardNumber);
                }
                return null;
            }
        };

        executor.execute(claimSearchTask);
    }
}
