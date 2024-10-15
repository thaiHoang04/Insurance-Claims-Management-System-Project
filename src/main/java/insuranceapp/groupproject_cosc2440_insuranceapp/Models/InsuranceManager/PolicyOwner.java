/**
 * @author Group 14
 */
package insuranceapp.groupproject_cosc2440_insuranceapp.Models.InsuranceManager;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PolicyOwner {
    private final StringProperty Name;
    private final StringProperty Fee;
    private final StringProperty POID;

    public PolicyOwner() {
        this.Name = null;
        this.Fee= null;
        this.POID = null;
    }

    public PolicyOwner(String name, String fee, String POID) {
        this.POID = new SimpleStringProperty(this, "POID", POID);
        this.Name = new SimpleStringProperty(this, "Name", name);
        this.Fee = new SimpleStringProperty(this, "Fee", fee);
    }
    public StringProperty nameProperty() {
        return Name;
    }

    public StringProperty feeProperty() {
        return Fee;
    }

    public StringProperty POIDProperty() {
        return POID;
    }
}
