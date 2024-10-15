/**
 * @author Group 14
 */
package insuranceapp.groupproject_cosc2440_insuranceapp.Models;

public class PolicyHolder extends Beneficiary {
    public PolicyHolder(String id, String fullName, String insuranceCard, String phoneNumber, String email, String address, String policyOwnerId) {
        super(id, fullName, insuranceCard, phoneNumber, email, address, policyOwnerId);
    }
}
