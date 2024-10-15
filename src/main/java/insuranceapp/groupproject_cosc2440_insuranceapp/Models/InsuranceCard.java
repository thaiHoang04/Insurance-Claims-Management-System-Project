/**
 * @author Group 14
 */
package insuranceapp.groupproject_cosc2440_insuranceapp.Models;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;

public class InsuranceCard {
    private StringProperty cardNumber;
    private StringProperty cardHolder;
    private StringProperty policyOwner;
    private ObjectProperty<LocalDate> expirationDate;

    public InsuranceCard(String cardNumber, String cardHolder, String policyOwner, LocalDate expirationDate) {
        this.cardNumber = new SimpleStringProperty(this, "cardNumber", cardNumber);
        this.cardHolder = new SimpleStringProperty(this, "cardHolder", cardHolder);
        this.policyOwner = new SimpleStringProperty(this, "policyOwner", policyOwner);
        this.expirationDate = new SimpleObjectProperty<>(this, "expirationDate", expirationDate);
    }

    public String getCardNumber() {
        return cardNumber.get();
    }

    public StringProperty cardNumberProperty() {
        return cardNumber;
    }

    public String getCardHolder() {
        return cardHolder.get();
    }

    public StringProperty cardHolderProperty() {
        return cardHolder;
    }

    public String getPolicyOwner() {
        return policyOwner.get();
    }

    public StringProperty policyOwnerProperty() {
        return policyOwner;
    }

    public LocalDate getExpirationDate() {
        return expirationDate.get();
    }

    public ObjectProperty<LocalDate> expirationDateProperty() {
        return expirationDate;
    }
}
