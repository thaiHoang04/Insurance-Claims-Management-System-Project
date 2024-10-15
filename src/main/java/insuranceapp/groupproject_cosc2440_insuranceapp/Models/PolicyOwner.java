/**
 * @author Group 14
 */
package insuranceapp.groupproject_cosc2440_insuranceapp.Models;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PolicyOwner {
    private StringProperty id;
    private StringProperty name;
    private DoubleProperty fee;

    public PolicyOwner(String id, String name, double fee) {
        this.id = new SimpleStringProperty(this, "id", id);
        this.name = new SimpleStringProperty(this, "name", name);
        this.fee = new SimpleDoubleProperty(this, "fee", fee);
    }

    public StringProperty idProperty() {
        return id;
    }

    public StringProperty nameProperty() {
        return name;
    }

    public String getId() {
        return id.get();
    }

    public String getName() {
        return name.get();
    }

    public double getFee() {
        return fee.get();
    }

    public DoubleProperty feeProperty() {
        return fee;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public void setFee(double fee) {
        this.fee.set(fee);
    }
}
