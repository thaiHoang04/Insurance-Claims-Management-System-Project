/**
 * @author Group 14
 */
package insuranceapp.groupproject_cosc2440_insuranceapp.Models.InsuranceManager;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.Date;

public class InsuranceCard {
    private final StringProperty insuranceCardID;
    private final PolicyHolder cardHolder;
    private final ObjectProperty<Date> expirationDate;

    public InsuranceCard() {
        this.insuranceCardID = null;
        this.cardHolder = null;
        this.expirationDate = null;
    }

    public InsuranceCard(String insuranceCardID, PolicyOwner policyOwner, Date expirationDate, PolicyHolder cardHolder) {
        this.insuranceCardID = new SimpleStringProperty(this, "Card ID", insuranceCardID);
        this.expirationDate = new SimpleObjectProperty<Date>(this, "Expiration Date", expirationDate);
        this.cardHolder = new PolicyHolder();
    }

    public StringProperty insuranceCardIDProperty() {
        return insuranceCardID;
    }

    public PolicyHolder getCardHolder() {
        return cardHolder;
    }

    public ObjectProperty<Date> expirationDateProperty() {
        return expirationDate;
    }
}
