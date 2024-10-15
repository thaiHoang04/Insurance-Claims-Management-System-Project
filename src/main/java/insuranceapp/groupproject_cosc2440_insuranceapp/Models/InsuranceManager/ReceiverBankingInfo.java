/**
 * @author Group 14
 */
package insuranceapp.groupproject_cosc2440_insuranceapp.Models.InsuranceManager;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ReceiverBankingInfo {
    private final StringProperty bank;
    private final StringProperty name;
    private final StringProperty bankNumber;

    public ReceiverBankingInfo() {
        this.bank = null;
        this.name = null;
        this.bankNumber = null;
    }

    public ReceiverBankingInfo(String bank, String name, String bankNumber) {
        this.bank = new SimpleStringProperty(this, "Bank", bank);
        this.name = new SimpleStringProperty(this, "Name", name);
        this.bankNumber = new SimpleStringProperty(this, "Bank Number", bankNumber);
    }

    public StringProperty getBank() { return bank; }

    public StringProperty getName() {
        return name;
    }


    public StringProperty getBankNumber() {
        return bankNumber;
    }
}
