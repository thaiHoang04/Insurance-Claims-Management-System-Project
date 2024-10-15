/**
 * @author Group 14
 */
package insuranceapp.groupproject_cosc2440_insuranceapp.Models.InsuranceManager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class PolicyHolder extends Customer {
    private final ObservableList<Dependent> dependentList;

    public PolicyHolder() {
        super();
        this.claimList = null;
        dependentList = FXCollections.observableArrayList();
    }

    public PolicyHolder(String customerID, String fullName, String email, String address, String phoneNumber, String policyOwner, String insuranceCard) {
        super(customerID, fullName, email, address, phoneNumber, insuranceCard, policyOwner);
        this.dependentList = FXCollections.observableArrayList();
        this.claimList = FXCollections.observableArrayList();
    }

    public List<Dependent> getDependentList() {
        return dependentList;
    }
}
