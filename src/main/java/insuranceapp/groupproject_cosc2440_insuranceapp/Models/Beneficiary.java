/**
 * @author Group 14
 */
package insuranceapp.groupproject_cosc2440_insuranceapp.Models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public abstract class Beneficiary {
    private StringProperty id;
    private StringProperty fullName;
    private StringProperty insuranceCard;
    private StringProperty phoneNumber;
    private StringProperty email;
    private StringProperty address;
    private StringProperty policyOwnerId;

    public Beneficiary(String id, String fullName, String insuranceCard, String phoneNumber, String email, String address, String policyOwnerId) {
        this.id = new SimpleStringProperty(this, "id", id);
        this.fullName = new SimpleStringProperty(this, "fullName", fullName);
        this.insuranceCard = new SimpleStringProperty(this, "insuranceCard", insuranceCard);
        this.phoneNumber = new SimpleStringProperty(this, "phoneNumber", phoneNumber);
        this.email = new SimpleStringProperty(this, "email", email);
        this.address = new SimpleStringProperty(this, "address", address);
        this.policyOwnerId = new SimpleStringProperty(this, "policyOwnerId", policyOwnerId);
    }

    public StringProperty idProperty() {
        return id;
    }

    public StringProperty fullNameProperty() {
        return fullName;
    }

    public StringProperty insuranceCardProperty() {
        return insuranceCard;
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber.get();
    }

    public StringProperty phoneNumberProperty() {
        return phoneNumber;
    }

    public String getAddress() {
        return address.get();
    }

    public StringProperty addressProperty() {
        return address;
    }

    public String getId() {
        return id.get();
    }

    public String getFullName() {
        return fullName.get();
    }

    public String getInsuranceCard() {
        return insuranceCard.get();
    }

    public String getPolicyOwnerId() {
        return policyOwnerId.get();
    }

    public StringProperty policyOwnerIdProperty() {
        return policyOwnerId;
    }

    public void setFullName(String fullName) {
        this.fullName.set(fullName);
    }

    public void setInsuranceCard(String insuranceCard) {
        this.insuranceCard.set(insuranceCard);
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber.set(phoneNumber);
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public void setPolicyOwnerId(String policyOwnerId) {
        this.policyOwnerId.set(policyOwnerId);
    }

    public void setId(String id) {
        this.id.set(id);
    }
}
