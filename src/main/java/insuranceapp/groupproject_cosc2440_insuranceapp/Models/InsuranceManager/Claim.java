/**
 * @author Group 14
 */
package insuranceapp.groupproject_cosc2440_insuranceapp.Models.InsuranceManager;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.Date;

public class Claim {
    private final StringProperty claimID;
    private final StringProperty insuredPerson;
    private final StringProperty cardID;
    private final StringProperty amount;
    private final StringProperty status;
    private final ObjectProperty<Date> examDate;
    private final ObjectProperty<Date> claimDate;
    private final StringProperty document;
    private final StringProperty bankingInfo;

    public Claim() {
        this.claimID = null;
        this.insuredPerson = null;
        this.cardID = null;
        this.amount = null;
        this.status = null;
        this.examDate = null;
        this.claimDate = null;
        this.document = null;
        this.bankingInfo = null;
    }

    public Claim(String claimID, String insuredPerson, String cardID, String amount, String status,
                 Date examDate, Date claimDate, String document, String bankingInfo) {
        this.claimID = new SimpleStringProperty(this,"Claim ID", claimID);
        this.insuredPerson = new SimpleStringProperty(this,"Insured Person", insuredPerson);
        this.cardID = new SimpleStringProperty(this,"Card ID", cardID);
        this.amount = new SimpleStringProperty(this, "Amount", amount);
        this.status = new SimpleStringProperty(this,"Status", status);
        this.examDate = new SimpleObjectProperty<Date>(this, "Exam Date", examDate);
        this.claimDate = new SimpleObjectProperty<Date>(this,"Claim Date", claimDate);
        this.document = new SimpleStringProperty(this,"Document", document);
        this.bankingInfo = new SimpleStringProperty(this,"Banking Info", bankingInfo);
    }

    public StringProperty claimIDProperty() {
        return claimID;
    }

    public StringProperty insuredPersonProperty() {
        return insuredPerson;
    }

    public StringProperty cardIDProperty() {
        return cardID;
    }

    public StringProperty amountProperty() {
        return amount;
    }

    public StringProperty statusProperty() {
        return status;
    }

    public ObjectProperty<Date> examDateProperty() {
        return examDate;
    }

    public ObjectProperty<Date> claimDateProperty() {
        return claimDate;
    }

    public StringProperty documentProperty() {
        return document;
    }

    public StringProperty bankingInfoProperty() {
        return bankingInfo;
    }

    public void setDocument(String documentName) {
        this.document.set(documentName);
    }

    public String getClaimID() {
        return claimID.get();
    }
}
