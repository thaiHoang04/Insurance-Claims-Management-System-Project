/**
 * @author Group 14
 */
package insuranceapp.groupproject_cosc2440_insuranceapp.Models;

import insuranceapp.groupproject_cosc2440_insuranceapp.Controllers.PolicyOwner.PolicyOwnerPolicyHolderViewController;
import insuranceapp.groupproject_cosc2440_insuranceapp.Views.PolicyOwnerViewFactory;
import insuranceapp.groupproject_cosc2440_insuranceapp.Views.ViewFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;

public class PolicyOwnerModel {
    private static PolicyOwnerModel policyOwnerModel;
    private final ViewFactory viewFactory;
    private PolicyOwnerViewFactory policyOwnerViewFactory;
    private PolicyOwner policyOwner;
    private boolean policyOwnerLoginSuccessFlag;
    private DatabaseDriver databaseDriver;
    private ObservableList<Claim> claims;
    private ObservableList<PolicyHolder> policyHolders;
    private ObservableList<Dependent> dependents;
    private ObservableList<Dependent> dependentsOfCurrentPolicyHolder;
    private PolicyHolder currentSelectedPolicyHolder;

    private PolicyOwnerPolicyHolderViewController policyOwnerPolicyHolderViewController;

    public PolicyOwnerModel() {
        this.viewFactory = new ViewFactory();
        this.policyOwnerViewFactory = new PolicyOwnerViewFactory();
        this.databaseDriver = new DatabaseDriver();

        //Policy Owner Data Section
        this.policyOwner = new PolicyOwner(null, null, 0.0);
        this.policyOwnerLoginSuccessFlag = false;
        this.policyHolders = FXCollections.observableArrayList();
        this.dependents = FXCollections.observableArrayList();
        this.dependentsOfCurrentPolicyHolder = FXCollections.observableArrayList();
        this.claims = FXCollections.observableArrayList();
    }

    public static synchronized PolicyOwnerModel getInstance() {
        if (policyOwnerModel == null) {
            policyOwnerModel = new PolicyOwnerModel();
        }
        return policyOwnerModel;
    }

    public void setPolicyOwnerPolicyHolderViewController(PolicyOwnerPolicyHolderViewController policyOwnerPolicyHolderViewController) {
        this.policyOwnerPolicyHolderViewController = policyOwnerPolicyHolderViewController;
    }

    public void updatePolicyHolderView() {
        this.policyOwnerPolicyHolderViewController.numberOfPolicyHolder.setText(Integer.toString(policyHolders.size()));
    }

    public ViewFactory getViewFactory() {
        return viewFactory;
    }

    public PolicyOwnerViewFactory getPolicyOwnerViewFactory() {
        return policyOwnerViewFactory;
    }

    public PolicyOwner getPolicyOwner() {
        return policyOwner;
    }

    public Integer getNumberOfPolicyHolder(String id) {
        return databaseDriver.countNumberOfPolicyHolders(id);
    }
    public Integer getNumberOfDependent(String id) {
        return databaseDriver.countNumberOfDependents(id);
    }
    public Integer getNumberOfClaimById(String id) {
        return databaseDriver.countNumberOfClaimById(id);
    }

    public Integer getNumberOfClaim() {
        return databaseDriver.getTheLastNumberOfClaim();
    }

    public Integer getNumberOfPendingClaim(String id) {
        return databaseDriver.countNumberOfPendingClaim(id);
    }

    public Integer getTheLastNumberOfPolicyHolders() {return databaseDriver.getTheLastOfPolicyHolders();}

    public Integer getTheLastNumberOfDependents() {return databaseDriver.getTheLastOfDependents();}

    public Integer getNumberOfInsuranceCard() {return databaseDriver.countNumberOfInsuranceCard();}

    public ObservableList<Claim> getClaims() {
        return claims;
    }
    public ObservableList<PolicyHolder> getPolicyHolders() {
        return policyHolders;
    }

    public ObservableList<Dependent> getDependents() {
        return dependents;
    }

    public ObservableList<Dependent> getDependentsOfCurrentPolicyHolder() {
        return dependentsOfCurrentPolicyHolder;
    }

    public void addClaims(Claim claim) {
        this.claims.add(claim);
    }

    public void deleteClaims(Claim claim) {this.claims.remove(claim);}

    public void addPolicyHolders(PolicyHolder policyHolder) {
        this.policyHolders.add(policyHolder);
    }

    public void addDependents(Dependent dependent) {
        this.dependents.add(dependent);
    }

    public void addDependentsOfCurrentPolicyHolder(Dependent dependent) {
        this.dependentsOfCurrentPolicyHolder.add(dependent);
    }

    public void setClaims() {
        databaseDriver.getListClaimByPolicyOwnerId(this.policyOwner.getId());
    }

    public void setPolicyHolders() {
        databaseDriver.getListPolicyHolderByPolicyOwnerId(this.policyOwner.getId());
    }

    public void setDependents() {
        databaseDriver.getListDependentByPolicyOwnerId(this.policyOwner.getId());
    }

    public void setDependentsOfCurrentPolicyHolder() {
        this.dependentsOfCurrentPolicyHolder = FXCollections.observableArrayList();
        databaseDriver.getListDependentByPolicyHolderId(this.currentSelectedPolicyHolder.getId(), "PO");
    }

    public void setCurrentSelectedPolicyHolder(PolicyHolder policyHolder) {
        this.currentSelectedPolicyHolder = policyHolder;
    }

    public PolicyHolder getCurrentSelectedPolicyHolder() {
        return this.currentSelectedPolicyHolder;
    }

    public DatabaseDriver getDatabaseDriver() {
        return databaseDriver;
    }

    public void evaluatePolicyOwnerCred(String id, String name, String fee) {
        this.policyOwner.setId(id);
        this.policyOwner.setName(name);
        this.policyOwner.setFee(Double.parseDouble(fee));
    }

    public double getTotalAnnualFee(String id) {
        int numberOfPolicyHolder = databaseDriver.countNumberOfPolicyHolders(id);
        int numberOfDependent = databaseDriver.countNumberOfDependents(id);
        double fixedAnnualFee = this.policyOwner.getFee();
        return fixedAnnualFee * numberOfPolicyHolder + (numberOfDependent * (fixedAnnualFee * 0.6));
    }

    public void updatePolicyHolders(String id, String fullName, String phoneNumber, String email, String address) {
        for (PolicyHolder policyHolderTmp : policyHolders) {
            if (policyHolderTmp.getId().equals(id)) {
                policyHolderTmp.setFullName(fullName);
                policyHolderTmp.setPhoneNumber(phoneNumber);
                policyHolderTmp.setEmail(email);
                policyHolderTmp.setAddress(address);
            }
        }
    }

    public void updateDependents(String id, String fullName, String phoneNumber, String email, String address) {
        for (Dependent dependentTmp : dependents) {
            if (dependentTmp.getId().equals(id)) {
                dependentTmp.setFullName(fullName);
                dependentTmp.setPhoneNumber(phoneNumber);
                dependentTmp.setEmail(email);
                dependentTmp.setAddress(address);
            }
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

    public void updateDependentsOfCurrentPolicyHolder(String id, String fullName, String phoneNumber, String email, String address) {
        for (Dependent dependentTmp : dependentsOfCurrentPolicyHolder) {
            if (dependentTmp.getId().equals(id)) {
                dependentTmp.setFullName(fullName);
                dependentTmp.setPhoneNumber(phoneNumber);
                dependentTmp.setEmail(email);
                dependentTmp.setAddress(address);
            }
        }
    }

    public void deleteClaim(String id) {
        databaseDriver.deleteClaim(id);
    }

    public boolean deletePolicyHolder(PolicyHolder policyHolder) {
        return databaseDriver.deletePolicyHolder(policyHolder.getId()) && databaseDriver.deleteInsuranceCard(policyHolder.getInsuranceCard()) && databaseDriver.deleteAccountData(policyHolder.getId()) && databaseDriver.deleteDependentByPolicyHolderId(policyHolder.getId()) && databaseDriver.deleteClaimByInsuranceCard(policyHolder.getInsuranceCard());
    }

    public void resetPolicyHolders() {
        this.policyHolders = FXCollections.observableArrayList();
    }

    public void resetDependents() {
        this.dependents = FXCollections.observableArrayList();
    }

    public void resetPolicyOwnerModel() {
        this.policyOwnerViewFactory = new PolicyOwnerViewFactory();
    }

    //Generate id
    public String generateClaimId() {
        int numberOfClaim = PolicyOwnerModel.getInstance().getNumberOfClaim() + 1;
        String claimId = "f-";
        String numberOfClaimStr = Integer.toString(numberOfClaim);
        for (int i = 0; i < (10 - numberOfClaimStr.length()); i++) {
            claimId += "0";
        }
        return claimId += numberOfClaimStr;
    }

    public String generateIdForDependent() {
        int numberOfBeneficiaries = PolicyOwnerModel.getInstance().getTheLastNumberOfDependents() + 1001;
        String customerId = "c-";
        String numberOfBeneficiariesStr = Integer.toString(numberOfBeneficiaries);
        for (int i = 0; i < (7 - numberOfBeneficiariesStr.length()); i++) {
            customerId += "0";
        }
        return customerId += numberOfBeneficiariesStr;
    }

    public String generateIdForPolicyHolder() {
        int numberOfBeneficiaries = PolicyOwnerModel.getInstance().getTheLastNumberOfPolicyHolders() + 1;
        String customerId = "c-";
        String numberOfBeneficiariesStr = Integer.toString(numberOfBeneficiaries);
        for (int i = 0; i < (7 - numberOfBeneficiariesStr.length()); i++) {
            customerId += "0";
        }
        return customerId += numberOfBeneficiariesStr;
    }

    public String generateIdForInsuranceCard() {
        int numberOfInsuranceCards = PolicyOwnerModel.getInstance().getNumberOfInsuranceCard() + 1;
        String insuranceCardId = "";
        for (int i = 0; i < (10 - Integer.toString(numberOfInsuranceCards).length()); i++) {
            // Generate insurance card id
            insuranceCardId += "0";
        }
        return insuranceCardId += Integer.toString(numberOfInsuranceCards);
    }

    public InsuranceCard generateInsuranceCard(String cardHolder) {
        // Generate insurance card
        return new InsuranceCard(generateIdForInsuranceCard(), cardHolder, PolicyOwnerModel.getInstance().getPolicyOwner().getId(), LocalDate.now().plusYears(2));
    }
}
