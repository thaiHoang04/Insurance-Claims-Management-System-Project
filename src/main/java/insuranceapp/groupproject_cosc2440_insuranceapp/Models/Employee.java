/**
 * @author Group 14
 */
package insuranceapp.groupproject_cosc2440_insuranceapp.Models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Employee {
    private StringProperty id;
    private StringProperty phone;
    private StringProperty address;
    private StringProperty fullName;
    private StringProperty email;

    public Employee() {
        this.id = new SimpleStringProperty(this, "id", "");
        this.phone = new SimpleStringProperty(this, "phone", "");
        this.address = new SimpleStringProperty(this, "address", "");
        this.fullName = new SimpleStringProperty(this, "fullName", "");
        this.email = new SimpleStringProperty(this, "email", "");
    }

    public Employee(String id, String phone, String address, String fullName, String email) {
        this.id = new SimpleStringProperty(this, "id", id);
        this.phone = new SimpleStringProperty(this, "phone", phone);
        this.address = new SimpleStringProperty(this, "address", address);
        this.fullName = new SimpleStringProperty(this, "fullName", fullName);
        this.email = new SimpleStringProperty(this, "email", email);
    }

    public String getId() {
        return id.get();
    }

    public StringProperty idProperty() {
        return id;
    }

    public String getPhone() {
        return phone.get();
    }

    public StringProperty phoneProperty() {
        return phone;
    }

    public String getAddress() {
        return address.get();
    }

    public StringProperty addressProperty() {
        return address;
    }

    public String getFullName() {
        return fullName.get();
    }

    public StringProperty fullNameProperty() {
        return fullName;
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public void setFullName(String fullName) {
        this.fullName.set(fullName);
    }

    public void setEmail(String email) {
        this.email.set(email);
    }
}
