/**
 * @author Group 14
 */
package insuranceapp.groupproject_cosc2440_insuranceapp.Models;

import insuranceapp.groupproject_cosc2440_insuranceapp.Views.AdminViewFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AdminModel {
    private static AdminModel adminModel;

    private DatabaseDriver databaseDriver;
    private final AdminViewFactory adminViewFactory;

    private ObservableList<Claim> claims;
    private ObservableList<PolicyOwner> policyOwners;
    private ObservableList<Employee> insuranceManagers;
    private ObservableList<Employee> insuranceSurveyors;

    public AdminModel() {
        this.databaseDriver = new DatabaseDriver();
        this.adminViewFactory = new AdminViewFactory();
        this.claims = FXCollections.observableArrayList();
        this.policyOwners = FXCollections.observableArrayList();
        this.insuranceManagers = FXCollections.observableArrayList();
        this.insuranceSurveyors = FXCollections.observableArrayList();
    }

    public static synchronized AdminModel getInstance() {
        if (adminModel == null) {
            adminModel = new AdminModel();
        }
        return adminModel;
    }

    public AdminViewFactory getAdminViewFactory() {
        return adminViewFactory;
    }

    public DatabaseDriver getDatabaseDriver() {
        return databaseDriver;
    }

    public ObservableList<Claim> getClaims() {
        return claims;
    }

    public void setClaims() {
        databaseDriver.getListClaimForAdmin();
    }

    public void addClaim(Claim claim) {
        this.claims.add(claim);
    }

    //Policy Owner
    public ObservableList<PolicyOwner> getPolicyOwners() {
        return policyOwners;
    }

    public void setPolicyOwners() {
        databaseDriver.getListPolicyOwnerForAdmin();
    }

    public void addPolicyOwners(PolicyOwner policyOwner) {
        this.policyOwners.add(policyOwner);
    }

    //Insurance Manager
    public ObservableList<Employee> getInsuranceManagers() {
        return insuranceManagers;
    }

    public void setInsuranceManagers() {
        databaseDriver.getListInsuranceManagerForAdmin();
    }

    public void addInsuranceManagers(Employee insuranceManager) {
        this.insuranceManagers.add(insuranceManager);
    }

    //Insurance Surveyor
    public ObservableList<Employee> getInsuranceSurveyors() {
        return insuranceSurveyors;
    }

    public void setInsuranceSurveyors() {
        databaseDriver.getListInsuranceSurveyorForAdmin();
    }

    public void addInsuranceSurveyors(Employee insuranceSurveyor) {
        this.insuranceSurveyors.add(insuranceSurveyor);
    }

    public void updateCellInfo(PolicyOwner policyOwner) {
        for (PolicyOwner policyOwnerTmp : AdminModel.getInstance().getPolicyOwners()) {
            if (policyOwnerTmp.getId().equals(policyOwner.getId())) {
                policyOwnerTmp.setName(policyOwner.getName());
                policyOwnerTmp.setFee(policyOwner.getFee());
            }
        }
    }

    public String generateIdForInsuranceManager() {
        String id = "m-";
        String lastNumOfEmployees = String.valueOf(databaseDriver.getTheLastNumberOfEmployee());
        for (int i = 0; i < (7 - lastNumOfEmployees.length()); i++) {
            id += 0;
        }
        return id + lastNumOfEmployees;
    }

    public String generateIdForInsuranceSurveyor() {
        String id = "s-";
        int theLastNumberOfEmployee = databaseDriver.getTheLastNumberOfEmployee() + 1;
        String lastNumOfEmployees = String.valueOf(theLastNumberOfEmployee);
        for (int i = 0; i < (7 - lastNumOfEmployees.length()); i++) {
            id += 0;
        }
        return id + lastNumOfEmployees;
    }

    public String generateIdForPolicyOwner() {
        // Generate a unique id for the new policy owner
        String id = "po-";
        String numOfPolicyOwner = String.valueOf(AdminModel.getInstance().getDatabaseDriver().getTheLastNumberOfPolicyOwner() + 1);
        for (int i = 0; i < (7 - numOfPolicyOwner.length()); i++) {
            id += "0";
        }
        return id + numOfPolicyOwner;
    }

    public void updateInsuranceManager(String id, String fullName, String phoneNumber, String email, String address) {
        for (Employee insuranceManagerTmp : insuranceManagers) {
            if (insuranceManagerTmp.getId().equals(id)) {
                insuranceManagerTmp.setFullName(fullName);
                insuranceManagerTmp.setPhone(phoneNumber);
                insuranceManagerTmp.setEmail(email);
                insuranceManagerTmp.setAddress(address);
            }
        }
    }

    public void updateInsuranceSurveyor(String id, String fullName, String phoneNumber, String email, String address) {
        for (Employee insuranceSurveyorTmp : insuranceSurveyors) {
            if (insuranceSurveyorTmp.getId().equals(id)) {
                insuranceSurveyorTmp.setFullName(fullName);
                insuranceSurveyorTmp.setPhone(phoneNumber);
                insuranceSurveyorTmp.setEmail(email);
                insuranceSurveyorTmp.setAddress(address);
            }
        }
    }
}
