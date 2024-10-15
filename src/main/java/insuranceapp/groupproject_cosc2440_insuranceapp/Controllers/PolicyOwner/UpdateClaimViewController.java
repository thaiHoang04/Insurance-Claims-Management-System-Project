/**
 * @author Group 14
 */
package insuranceapp.groupproject_cosc2440_insuranceapp.Controllers.PolicyOwner;

import insuranceapp.groupproject_cosc2440_insuranceapp.Models.Claim;
import insuranceapp.groupproject_cosc2440_insuranceapp.Models.DocumentOrg;
import insuranceapp.groupproject_cosc2440_insuranceapp.Models.PolicyHolderModel;
import insuranceapp.groupproject_cosc2440_insuranceapp.Models.PolicyOwnerModel;
import javafx.concurrent.Task;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.stage.FileChooser;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class UpdateClaimViewController implements Initializable {
    public TextField insuredPersonTxtField;
    public TextField insuranceCardNumTxtField;
    public TextField statusTxtField;
    public DatePicker claimDatePicker;
    public DatePicker examDatePicker;
    public Spinner<Double> claimAmountSpinner;
    public TextField bankNameTxtField;
    public TextField bankNumTxtField;
    public Button importBtn;
    public Button updateBtn;
    public VBox fileListContainer;
    public List<java.io.File> uploadedFileList;
    private Executor executor;

    private Claim claim;

    public UpdateClaimViewController(Claim claim) {
        this.claim = claim;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        insuredPersonTxtField.setText(claim.getInsuredPerson());
        insuranceCardNumTxtField.setText(claim.getInsuranceCardNumber());
        statusTxtField.setText(claim.getStatus());
        claimDatePicker.setValue(claim.getClaimDate());
        examDatePicker.setValue(claim.getExamDate());
        // Set a ValueFactory for the Spinner
        SpinnerValueFactory<Double> valueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(0, Double.MAX_VALUE, 0);
        claimAmountSpinner.setValueFactory(valueFactory);
        claimAmountSpinner.getValueFactory().setValue(claim.getClaimAmount());
        claimAmountSpinner.setEditable(true);
        bankNameTxtField.setText(claim.getReceiverBankingInfo().substring(0, claim.getReceiverBankingInfo().indexOf("-")));
        bankNumTxtField.setText(claim.getReceiverBankingInfo().substring(claim.getReceiverBankingInfo().indexOf("-")));
        for (DocumentOrg i : PolicyOwnerModel.getInstance().getDatabaseDriver().getListOfFile(claim.getId())) {
            fileListContainer.getChildren().add(new Label(i.getName()));
            Line line = new Line(0, 4, 0, 32);
            fileListContainer.getChildren().add(line);

        }
        importBtn.setOnAction(actionEvent -> {
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("PDF file (*.pdf)", "*.pdf");
            fileChooser.getExtensionFilters().add(extensionFilter);
            List<java.io.File> fileList = fileChooser.showOpenMultipleDialog(null);
            if (!fileList.isEmpty()) {
                uploadedFileList = fileList;
                for (java.io.File file: fileList) {
                    fileListContainer.getChildren().add(new Label(file.getName()));
                    Line line = new Line(0, 4, 0, 32);
                    fileListContainer.getChildren().add(line);
                }
            }
        });

        updateBtn.setOnAction(actionEvent -> {
            claim.setInsuredPerson(insuredPersonTxtField.getText());
            claim.setInsuranceCardNumber(insuranceCardNumTxtField.getText());
            claim.setStatus(statusTxtField.getText());
            claim.setClaimDate(claimDatePicker.getValue());
            claim.setExamDate(examDatePicker.getValue());
            claim.setClaimAmount(claimAmountSpinner.getValue());
            claim.setReceiverBankingInfo(bankNameTxtField.getText() + "-" + bankNumTxtField.getText());
            PolicyOwnerModel.getInstance().getDatabaseDriver().updateClaim(claim);
            if (uploadedFileList != null) {
                uploadFile(uploadedFileList, claim.getId(), claim.getInsuranceCardNumber());
            }
            if (!PolicyHolderModel.getInstance().getClaims().isEmpty()) {
                PolicyHolderModel.getInstance().updateClaims(claim);
                PolicyHolderModel.getInstance().getDatabaseDriver().recordActivityHistory("UPDATE CLAIM " + claim.getId(), PolicyHolderModel.getInstance().getPolicyHolder().getId());
            } else {
                PolicyOwnerModel.getInstance().updateClaims(claim);
                PolicyOwnerModel.getInstance().getDatabaseDriver().recordActivityHistory("UPDATE CLAIM " + claim.getId(), PolicyOwnerModel.getInstance().getPolicyOwner().getId());
            }
            PolicyOwnerModel.getInstance().getPolicyOwnerViewFactory().closeCurrentSubStage();
        });

        executor = Executors.newCachedThreadPool(r -> {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t;
        });
    }

    public void uploadFile(List<java.io.File> fileList, String claimId, String insuranceCardNumber) {
        Task<Void> claimSearchTask = new Task<Void>() {
            @Override
            public Void call() throws Exception {
                for (java.io.File file : fileList) {
                    PolicyOwnerModel.getInstance().getDatabaseDriver().uploadFile(fileList, claimId, insuranceCardNumber);
                }
                return null;
            }
        };

        executor.execute(claimSearchTask);
    }
}
