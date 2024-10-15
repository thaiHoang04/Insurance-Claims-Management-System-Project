/**
 * @author Group 14
 */
package insuranceapp.groupproject_cosc2440_insuranceapp.Models;

import javafx.beans.property.*;

import java.time.LocalDate;

public class Claim {

    enum STATUS {NEW, PROCESSING, COMPLETE};
    private StringProperty id;
    private ObjectProperty<LocalDate> claimDate;
    private StringProperty insuredPerson;
    private StringProperty insuranceCardNumber;
    private ObjectProperty<LocalDate> examDate;
    private DoubleProperty claimAmount;
    private StringProperty status;
    private StringProperty receiverBankingInfo;

    public Claim() {
    }

    public Claim(String id, LocalDate claimDate, String insuredPerson, String insuranceCardNumber, LocalDate examDate, Double claimAmount, String status, String receiverBankingInfo) {
        this.id = new SimpleStringProperty(this, "id", id);
        this.claimDate = new SimpleObjectProperty<LocalDate>(this, "claimDate", claimDate);
        this.insuredPerson = new SimpleStringProperty(this, "insuredPerson", insuredPerson);
        this.insuranceCardNumber = new SimpleStringProperty(this, "insuranceCardNumber", insuranceCardNumber);
        this.examDate = new SimpleObjectProperty<LocalDate>(this, "examDate", examDate);
        this.claimAmount = new SimpleDoubleProperty(this, "claimAmount", claimAmount);
        this.status = new SimpleStringProperty(this, "status", status);
        this.receiverBankingInfo = new SimpleStringProperty(this, "receiverBankingInfo", receiverBankingInfo);
    }

    public String getId() {
        return id.get();
    }

    public StringProperty idProperty() {
        return id;
    }

    public LocalDate getClaimDate() {
        return claimDate.get();
    }

    public ObjectProperty<LocalDate> claimDateProperty() {
        return claimDate;
    }

    public String getInsuredPerson() {
        return insuredPerson.get();
    }

    public StringProperty insuredPersonProperty() {
        return insuredPerson;
    }

    public String getInsuranceCardNumber() {
        return insuranceCardNumber.get();
    }

    public StringProperty insuranceCardNumberProperty() {
        return insuranceCardNumber;
    }

    public LocalDate getExamDate() {
        return examDate.get();
    }

    public ObjectProperty<LocalDate> examDateProperty() {
        return examDate;
    }

    public double getClaimAmount() {
        return claimAmount.get();
    }

    public DoubleProperty claimAmountProperty() {
        return claimAmount;
    }

    public String getStatus() {
        return status.get();
    }

    public StringProperty statusProperty() {
        return status;
    }

    public String getReceiverBankingInfo() {
        return receiverBankingInfo.get();
    }

    public StringProperty receiverBankingInfoProperty() {
        return receiverBankingInfo;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public void setClaimDate(LocalDate claimDate) {
        this.claimDate.set(claimDate);
    }

    public void setInsuredPerson(String insuredPerson) {
        this.insuredPerson.set(insuredPerson);
    }

    public void setInsuranceCardNumber(String insuranceCardNumber) {
        this.insuranceCardNumber.set(insuranceCardNumber);
    }

    public void setExamDate(LocalDate examDate) {
        this.examDate.set(examDate);
    }

    public void setClaimAmount(double claimAmount) {
        this.claimAmount.set(claimAmount);
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    public void setReceiverBankingInfo(String receiverBankingInfo) {
        this.receiverBankingInfo.set(receiverBankingInfo);
    }
}
