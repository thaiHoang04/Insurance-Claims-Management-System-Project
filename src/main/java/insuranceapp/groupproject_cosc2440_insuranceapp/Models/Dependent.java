/**
 * @author Group 14
 */
package insuranceapp.groupproject_cosc2440_insuranceapp.Models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Dependent extends Beneficiary {
    private StringProperty policyHolderId;
    public Dependent(String id, String fullName, String insuranceCard, String phoneNumber, String email, String address, String policyHolderId, String policyOwnerId) {
        super(id, fullName, insuranceCard, phoneNumber, email, address, policyOwnerId);
        this.policyHolderId = new SimpleStringProperty(this, "policyHolderId", policyHolderId);
    }

    public String getPolicyHolderId() {
        return policyHolderId.get();
    }

    public StringProperty policyHolderIdProperty() {
        return policyHolderId;
    }

    public void setPolicyHolderId(String policyHolderId) {
        this.policyHolderId.set(policyHolderId);
    }
}
