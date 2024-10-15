/**
 * @author Group 14
 */
package insuranceapp.groupproject_cosc2440_insuranceapp.Models.InsuranceManager;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Customer {
    private final StringProperty customerID;
    private final StringProperty fullName;
    private final StringProperty email;
    private final StringProperty address;
    private final StringProperty phoneNumber;
    private final StringProperty insuranceCard;
    private final StringProperty policyOwner;
    protected ObservableList<Claim> claimList;

    public Customer() {
        this.customerID = null;
        this.fullName = null;
        this.email = null;
        this.address = null;
        this.phoneNumber = null;
        this.insuranceCard = null;
        this.policyOwner = null;
        this.claimList = null;
    }

    public Customer(String customerID, String fullName, String email, String address, String phoneNumber, String insuranceCard, String policyOwner) {
        this.customerID = new SimpleStringProperty(this, "Customer ID", customerID);
        this.fullName = new SimpleStringProperty(this, "Full Name", fullName);
        this.email = new SimpleStringProperty(this, "Email", email);
        this.address = new SimpleStringProperty(this, "Address", address);
        this.phoneNumber = new SimpleStringProperty(this, "Phone Number", phoneNumber);
        this.insuranceCard = new SimpleStringProperty(this, "Insurance Card", insuranceCard);
        this.policyOwner = new SimpleStringProperty(this, "Policy Owner", policyOwner);
        claimList = FXCollections.observableArrayList();
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public String getAddress() {
        return address.get();
    }

    public StringProperty addressProperty() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber.get();
    }

    public StringProperty phoneNumberProperty() {
        return phoneNumber;
    }

    public StringProperty customerIDProperty() {
        return customerID;
    }

    public StringProperty fullNameProperty() {
        return fullName;
    }

    public StringProperty insuranceCardProperty() {
        return insuranceCard;
    }
    public StringProperty policyOwnerProperty() {
        return policyOwner;
    }

    public ObservableList<Claim> getClaimList() {
        return claimList;
    }

    public void setClaimList(ObservableList<Claim> claimList) {
        this.claimList = claimList;
    }
}
