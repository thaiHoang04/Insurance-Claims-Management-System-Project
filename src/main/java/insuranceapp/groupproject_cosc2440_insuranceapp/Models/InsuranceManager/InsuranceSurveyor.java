/**
 * @author Group 14
 */
package insuranceapp.groupproject_cosc2440_insuranceapp.Models.InsuranceManager;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class InsuranceSurveyor {
    private final StringProperty eID;
    private final StringProperty fullName;
    private final StringProperty phone;
    private final StringProperty address;
    private final StringProperty email;

    public InsuranceSurveyor() {
        this.eID = null;
        this.fullName = null;
        this.phone = null;
        this.address = null;
        this.email = null;
    }

    public InsuranceSurveyor(String eID, String fullName, String phone, String address, String email) {
        this.eID = new SimpleStringProperty(this, "Employee ID", eID);
        this.fullName = new SimpleStringProperty(this, "Full Name", fullName);
        this.phone = new SimpleStringProperty(this, "Phone Number", phone);
        this.address = new SimpleStringProperty(this, "Address", address);
        this.email = new SimpleStringProperty(this, "Email", email);
    }

    public StringProperty eIDProperty() {
        return eID;
    }

    public StringProperty fullNameProperty() {
        return fullName;
    }

    public StringProperty phoneProperty() {
        return phone;
    }

    public StringProperty addressProperty() {
        return address;
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setID(String eID) {
        this.eID.set(eID);
    }

    public void setFullName(String fullName) {
        this.fullName.set(fullName);
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

}
