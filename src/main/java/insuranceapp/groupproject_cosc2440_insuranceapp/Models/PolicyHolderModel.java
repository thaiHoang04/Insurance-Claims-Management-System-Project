/**
 * @author Group 14
 */
package insuranceapp.groupproject_cosc2440_insuranceapp.Models;

import insuranceapp.groupproject_cosc2440_insuranceapp.Controllers.PolicyHolder.PolicyHolderClaimViewController;
import insuranceapp.groupproject_cosc2440_insuranceapp.Controllers.PolicyHolder.PolicyHolderDependentViewController;
import insuranceapp.groupproject_cosc2440_insuranceapp.Views.PolicyHolderViewFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PolicyHolderModel {
    private static PolicyHolderModel policyHolderModel;
    private final PolicyHolderViewFactory policyHolderViewFactory;
    private DatabaseDriver databaseDriver;
    private ObservableList<Dependent> dependents;
    private ObservableList<Claim> claims;
    private PolicyHolder policyHolder;

    private PolicyHolderClaimViewController policyHolderClaimViewController;
    private PolicyHolderDependentViewController policyHolderDependentViewController;

    public PolicyHolderModel() {
        this.databaseDriver = new DatabaseDriver();
        this.policyHolder = new PolicyHolder(null, null, null, null, null, null, null);
        this.policyHolderViewFactory = new PolicyHolderViewFactory();
        this.dependents = FXCollections.observableArrayList();
        this.claims = FXCollections.observableArrayList();
    }

    public void setPolicyHolderClaimViewController(PolicyHolderClaimViewController policyHolderClaimViewController) {
        this.policyHolderClaimViewController = policyHolderClaimViewController;
    }

    public static synchronized PolicyHolderModel getInstance() {
        if (policyHolderModel == null) {
            policyHolderModel = new PolicyHolderModel();
        }
        return policyHolderModel;
    }

    public void evaluatePolicyHolder(String id, String name, String insuranceCard, String phone, String email, String address, String poid) {
        policyHolder.setId(id);
        policyHolder.setFullName(name);
        policyHolder.setInsuranceCard(insuranceCard);
        policyHolder.setPhoneNumber(phone);
        policyHolder.setEmail(email);
        policyHolder.setAddress(address);
        policyHolder.setPolicyOwnerId(poid);
    }

    public PolicyHolderViewFactory getPolicyHolderViewFactory() {
        return policyHolderViewFactory;
    }

    public PolicyHolder getPolicyHolder() {
        return policyHolder;
    }

    public ObservableList<Claim> getClaims() {
        return claims;
    }

    public ObservableList<Dependent> getDependents() {
        return dependents;
    }

    public void addClaim(Claim claim) {
        claims.add(claim);
    }

    public void addDependent(Dependent dependent) {
        dependents.add(dependent);
    }

    public void setDependents() {
        databaseDriver.getListDependentByPolicyHolderId(policyHolder.getId(), "D");
    }

    public void setClaims() {
        databaseDriver.getListClaimForPolicyHolder(policyHolder.getInsuranceCard());
    }

    public DatabaseDriver getDatabaseDriver() {
        return databaseDriver;
    }

    public void updateClaimView() {
        if (policyHolderClaimViewController != null) {
            policyHolderClaimViewController.totalClaimLbl.setText(String.valueOf(claims.size()));
        }
    }

    public void updateDependentView() {
        if (policyHolderDependentViewController != null) {
            policyHolderDependentViewController.numberOfDependent.setText(String.valueOf(dependents.size()));
        }
    }

    public void updateClaims(Claim claim) {
        for (Claim claimTmp : claims) {
            if (claimTmp.getId().equals(claim.getId())) {
                claimTmp.setClaimDate(claim.getClaimDate());
                claimTmp.setExamDate(claim.getExamDate());
                claimTmp.setClaimAmount(claim.getClaimAmount());
                claimTmp.setReceiverBankingInfo(claim.getReceiverBankingInfo());
            }
        }
    }

    public void updateDependent(String id,String name, String phone, String email, String address) {
        for (Dependent dependent : dependents) {
            if (dependent.getId().equals(id)) {
                dependent.setFullName(name);
                dependent.setPhoneNumber(phone);
                dependent.setEmail(email);
                dependent.setAddress(address);
            }
        }
    }
}
