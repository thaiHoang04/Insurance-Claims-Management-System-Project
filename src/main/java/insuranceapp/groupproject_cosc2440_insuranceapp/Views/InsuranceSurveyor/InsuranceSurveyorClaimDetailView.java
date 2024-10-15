package insuranceapp.groupproject_cosc2440_insuranceapp.Views.InsuranceSurveyor;

import insuranceapp.groupproject_cosc2440_insuranceapp.Models.Claim;
import insuranceapp.groupproject_cosc2440_insuranceapp.Models.Database;
import insuranceapp.groupproject_cosc2440_insuranceapp.Models.Document;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class InsuranceSurveyorClaimDetailView implements Initializable {
    @FXML
    private TextField id;

    @FXML
    private TextField insured_person;

    @FXML
    private TextField claim_date;

    @FXML
    private TextField exam_date;

    @FXML
    private TextField claim_amount;

    @FXML
    private TextField insurance_card;

    @FXML
    private TextField status;

    @FXML
    private TextField bank_info;
    @FXML
    private FontAwesomeIconView icon;

    @FXML
    private ListView listView;

    private Claim claim;

    public InsuranceSurveyorClaimDetailView(Claim claim) {
        this.claim = claim;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setClaimDetail(this.claim);
        setListViewDocument();
    }

    public void setListViewDocument() {
        try {
            listView.setItems(getDocumentList());
            listView.setCellFactory(e -> new InsuranceSurveyorDocumentCardView());
        } catch (SQLException e) {
            System.out.println("ListViewDocument error: " + e.getMessage());
        }
    }

    public ObservableList<Document> getDocumentList() throws SQLException {
        try {
            Database db = new Database();
            Statement stm = db.getCon().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stm.executeQuery(String.format("SELECT * FROM uploaded_file WHERE fid='%s'", this.claim.getId()));
            ObservableList<Document> documentList = FXCollections.observableArrayList();
            while(rs.next()) {
                System.out.println(rs.getRow());
                Document document = new Document(rs.getString("fid"), rs.getString("name"),rs.getString("file_id"));
                documentList.add(document);
            }
            return documentList;
        } catch(SQLException e) {
            System.out.println("getDocumentList Failed" + e.getMessage());
            return null;
        }
    }

    public void setClaimDetail(Claim claim) {
        id.textProperty().bind(claim.idProperty());
        insured_person.textProperty().bind(claim.insuredPersonProperty());
        claim_date.textProperty().bind(claim.claimDateProperty().asString());
        exam_date.textProperty().bind(claim.examDateProperty().asString());
        bank_info.textProperty().bind(claim.receiverBankingInfoProperty());
        insurance_card.textProperty().bind(claim.insuranceCardNumberProperty());
        status.textProperty().bind(claim.statusProperty());
        claim_amount.textProperty().bind(claim.claimAmountProperty().asString());
    }
}
