/**
 * @author Group 14
 */
package insuranceapp.groupproject_cosc2440_insuranceapp.Models;

import insuranceapp.groupproject_cosc2440_insuranceapp.Views.DependentViewFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DependentModel {
    private static DependentModel dependentModel;
    private final DependentViewFactory dependentViewFactory;
    private DatabaseDriver databaseDriver;
    private Dependent dependent;

    private ObservableList<Claim> claims;

    public DependentModel() {
        databaseDriver = new DatabaseDriver();
        this.dependent = new Dependent(null, null, null, null,null,null,null, null);
        this.dependentViewFactory = new DependentViewFactory();
        this.claims = FXCollections.observableArrayList();
    }

    public void evaluateDependentCred(String id, String name, String insuranceID, String phoneNum, String email, String address,String policyID, String policyOwnerID) {
        this.dependent.setId(id);
        this.dependent.setFullName(name);
        this.dependent.setInsuranceCard(insuranceID);
        this.dependent.setPhoneNumber(phoneNum);
        this.dependent.setEmail(email);
        this.dependent.setAddress(address);
        this.dependent.setPolicyHolderId(policyID);
        this.dependent.setPolicyOwnerId(policyOwnerID);
    }

    public ObservableList<Claim> getClaims() {
        return claims;
    }

    public void setClaims() {
        databaseDriver.getListClaimByInsuredPersonId(this.dependent.getId());
    }

    public void addClaim(Claim claim) {
        this.claims.add(claim);
    }

    public static synchronized DependentModel getInstance() {
        if (dependentModel == null) {
            dependentModel = new DependentModel();
        }
        return dependentModel;
    }

    public DependentViewFactory getDependentViewFactory() {
        return dependentViewFactory;
    }

    public Dependent getDependent() {
        return dependent;
    }
}
