package insuranceapp.groupproject_cosc2440_insuranceapp.Views.InsuranceSurveyor;

import insuranceapp.groupproject_cosc2440_insuranceapp.Models.Claim;
import insuranceapp.groupproject_cosc2440_insuranceapp.Models.Database;
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
import java.util.Comparator;
import java.util.ResourceBundle;


public class InsuranceSurveyorClaimView implements Initializable {
    @FXML
    private ListView listViewClaim;

    @FXML
    private MenuItem statusNew;

    @FXML
    private MenuItem statusRequire;

    @FXML
    private MenuItem statusUpdate;

    @FXML
    private MenuItem statusPending;

    @FXML
    private MenuItem statusApprove;

    @FXML
    private MenuItem statusDecline;

    @FXML
    private MenuItem claimDateNewest;

    @FXML
    private MenuItem claimDateOldest;

    @FXML
    private MenuItem examDateNewest;

    @FXML
    private MenuItem examDateOldest;

    @FXML
    private Label filter;

    @FXML
    private TextField searchField;

    @FXML
    private Label enterSearch;


    private ObservableList<Claim> claimList = FXCollections.observableArrayList();

    public InsuranceSurveyorClaimView() {}


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setListViewClaim();
        setStatusFilterButton();
        setDateFilterButton();
        setSearchButton();
        filter.setOnMouseClicked(mouseEvent -> {
            setListViewClaim();
        });
    }


    public void setListViewClaim() {
        try {
            listViewClaim.setItems(getClaimList());
            listViewClaim.setCellFactory(e -> new InsuranceSurveyorClaimCardView());
        } catch (SQLException e) {
            System.out.println("ListViewClaim error: " + e.getMessage());
        }
    }

    public void setStatusFilterButton() {
        statusNew.setOnAction(mouseEvent -> {
            filterClaimStatus("new");
        });
        statusRequire.setOnAction(mouseEvent -> {
            filterClaimStatus("require");
        });
        statusUpdate.setOnAction(mouseEvent -> {
            filterClaimStatus("update");
        });
        statusPending.setOnAction(mouseEvent -> {
            filterClaimStatus("pending");
        });
        statusApprove.setOnAction(mouseEvent -> {
            filterClaimStatus("approve");
        });
        statusDecline.setOnAction(mouseEvent -> {
            filterClaimStatus("decline");
        });
    }

    public void setDateFilterButton() {
        claimDateNewest.setOnAction(mouseEvent -> {
            filterClaimDate("newest");
        });
        claimDateOldest.setOnAction(mouseEvent -> {
            filterClaimDate("oldest");
        });
        examDateNewest.setOnAction(mouseEvent -> {
            filterExamDate("newest");
        });
        examDateOldest.setOnAction(mouseEvent -> {
            filterExamDate("oldest");
        });
    }

    public void setSearchButton() {
        enterSearch.setOnMouseClicked(mouseEvent -> {
            searchClaimByIdOrName(searchField.getText());
        });
    }

    public ObservableList<Claim> getClaimList() throws SQLException {
        try {
            Database db = new Database();
            Statement stm = db.getCon().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stm.executeQuery("SELECT * FROM claims ");
            while(rs.next()) {
                Claim claim = new Claim(rs.getString("fid"), rs.getDate("claim_date").toLocalDate(), rs.getString("insured_person"), rs.getString("card_number"), rs.getDate("exam_date").toLocalDate(), Double.parseDouble(rs.getString("claim_amount").replaceAll("[^0-9+-]", "")), rs.getString("status"), rs.getString("receiver_banking_info"));
                this.claimList.add(claim);
            }
            return this.claimList;
        } catch(SQLException e) {
            System.out.println("getClaimList Failed" + e.getMessage());
            return null;
        }
    }

    public void filterClaimStatus(String status) {
        FilteredList<Claim> filteredList = claimList.filtered(claim -> claim.getStatus().equalsIgnoreCase(status));
        setListViewClaimFilter(filteredList);
    }

    public void filterClaimDate(String state) {
        if(state.equalsIgnoreCase("newest")) {
            setListViewClaimFilter(sortByNewestDate(claimList, "claimDate"));
        }
        if(state.equalsIgnoreCase("oldest")) {
            setListViewClaimFilter(sortByOldestDate(claimList, "claimDate"));
        }

    }

    public void filterExamDate(String state) {
        if(state.equalsIgnoreCase("newest")) {
            setListViewClaimFilter(sortByNewestDate(claimList, "examDate"));
        }
        if(state.equalsIgnoreCase("oldest")) {
            setListViewClaimFilter(sortByOldestDate(claimList, "examDate"));
        }

    }

    public FilteredList<Claim> sortByNewestDate(ObservableList<Claim> claimList, String date) {
        Comparator<Claim> dateComparator = (claim1, claim2) -> {
            if (date.equalsIgnoreCase("claimDate")) {
                return claim2.getClaimDate().compareTo(claim1.getClaimDate());
            } else {
                return claim2.getExamDate().compareTo(claim1.getExamDate());
            }
        };
        ObservableList<Claim> sortedList = FXCollections.observableArrayList(claimList);
        sortedList.sort(dateComparator);
        return new FilteredList<>(sortedList, claim -> true);
    }

    public FilteredList<Claim> sortByOldestDate(ObservableList<Claim> claimList, String date) {
        Comparator<Claim> dateComparator = (claim1, claim2) -> {
            if (date.equalsIgnoreCase("claimDate")) {
                return claim1.getClaimDate().compareTo(claim2.getClaimDate());
            } else {
                return claim1.getExamDate().compareTo(claim2.getExamDate());
            }
        };
        ObservableList<Claim> sortedList = FXCollections.observableArrayList(claimList);
        sortedList.sort(dateComparator);
        return new FilteredList<>(sortedList, claim -> true);
    }

    public void searchClaimByIdOrName(String string) {
        FilteredList<Claim> filteredList = claimList.filtered(claim -> claim.getId().toLowerCase().contains(string.toLowerCase()) || claim.getInsuredPerson().toLowerCase().contains(string.toLowerCase()));
        setListViewClaimFilter(filteredList);
    }

    public void setListViewClaimFilter(FilteredList<Claim> claimList) {
        listViewClaim.setItems(claimList);
        listViewClaim.setCellFactory(e -> new InsuranceSurveyorClaimCardView());
    }
}
