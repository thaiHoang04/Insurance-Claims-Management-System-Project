/**
 * @author Group 14
 */
package insuranceapp.groupproject_cosc2440_insuranceapp.Models.InsuranceManager;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;

public class Dependent extends Customer{
    private final StringProperty policyHolder;

    public Dependent() {
        super();
        this.policyHolder = null;
        this.claimList = null;
    }

    public Dependent(String customerID, String fullName, String email, String address, String phoneNumber, String insuranceCard, String policyOwner, String policyHolder) {
        super(customerID, fullName, email, address, phoneNumber, insuranceCard, policyOwner);
        this.policyHolder = new SimpleStringProperty(this, "Policy Holder", policyHolder);
        this.claimList = FXCollections.observableArrayList();
    }

    public StringProperty policyHolderProperty() {
        return policyHolder;
    }
}
