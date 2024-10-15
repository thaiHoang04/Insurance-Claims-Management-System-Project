/**
 * @author Group 14
 */
package insuranceapp.groupproject_cosc2440_insuranceapp.Models;

import org.junit.jupiter.api.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DatabaseDriverTest {
    DatabaseDriver databaseDriver = new DatabaseDriver();
    static String claimId;
    static String policyHolderId;
    static String dependentId;
    static String insuranceManagerId;
    static String insuranceSurveyorId;
    static InsuranceCard insuranceCard;

    @BeforeAll
    static void setUp() {
        PolicyOwnerModel.getInstance().evaluatePolicyOwnerCred("Test", "Test", "1000");
        claimId = PolicyOwnerModel.getInstance().generateClaimId();
        policyHolderId = PolicyOwnerModel.getInstance().generateIdForPolicyHolder();
        dependentId = PolicyOwnerModel.getInstance().generateIdForDependent();
        insuranceCard = PolicyOwnerModel.getInstance().generateInsuranceCard(policyHolderId);
    }

    //Claim
    @Test
    @Order(1)
    void insertNewClaim() {
        Claim claim = new Claim(claimId, LocalDate.now(), "John Doe", "123456789", LocalDate.now(), 100.0, "NEW", "123456789");
        if (databaseDriver.insertNewClaim(claim)) {
            try (PreparedStatement statement = databaseDriver.getConn().prepareStatement("SELECT * FROM claims WHERE fid = ?")) {
                statement.setString(1, claimId);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    assertEquals(claimId, resultSet.getString("fid"));
                    assertEquals("John Doe", resultSet.getString("insured_person"));
                    assertEquals("123456789", resultSet.getString("card_number"));
                    assertEquals(LocalDate.now(), resultSet.getDate("claim_date").toLocalDate());
                    assertEquals(LocalDate.now(), resultSet.getDate("exam_date").toLocalDate());
                    assertEquals("NEW", resultSet.getString("status"));
                    assertEquals("123456789", resultSet.getString("receiver_banking_info"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    @Order(2)
    void deleteClaim() {
        if (databaseDriver.deleteClaim(claimId)) {
            try (PreparedStatement statement = databaseDriver.getConn().prepareStatement("SELECT * FROM claims WHERE fid = ?")) {
                statement.setString(1, claimId);
                ResultSet resultSet = statement.executeQuery();
                assertFalse(resultSet.next());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    @Order(3)
    void deleteFileOfClaim() {
        try (PreparedStatement pst = databaseDriver.getConn().prepareStatement("INSERT INTO uploaded_file (name, file_id, fid)" + "VALUES (?, ?, ?)")) {
            String fileName = "test.pdf";
            String fileId = "123456789";
            pst.setString(1, fileName);
            pst.setString(2, fileId);
            pst.setString(3, claimId);
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertTrue(databaseDriver.deleteFileOfClaim(claimId));
    }


    //Policy Holder
    @Test
    @Order(4)
    void insertNewPolicyHolder() {
        PolicyHolder policyHolder = new PolicyHolder(policyHolderId, "John Doe", "123456789", "123456789", "test@gmail.com", "123 Main St", "123456789");
        if (databaseDriver.insertNewPolicyHolder(policyHolder)) {
            try (PreparedStatement statement = databaseDriver.getConn().prepareStatement("SELECT * FROM policy_holders WHERE pid = ?")) {
                statement.setString(1, policyHolderId);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    assertEquals(policyHolderId, resultSet.getString("pid"));
                    assertEquals("John Doe", resultSet.getString("full_name"));
                    assertEquals("123456789", resultSet.getString("insurance_card"));
                    assertEquals("123456789", resultSet.getString("phone_number"));
                    assertEquals("test@gmail.com", resultSet.getString("email"));
                    assertEquals("123 Main St", resultSet.getString("address"));
                    assertEquals("123456789", resultSet.getString("poid"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    @Order(5)
    void updatePolicyHolder() {
        databaseDriver.updatePolicyHolder(policyHolderId, "John Test", "123456789", "test@gmail.com", "123 Main St");
        try (PreparedStatement statement = databaseDriver.getConn().prepareStatement("SELECT * FROM policy_holders WHERE pid = ?")) {
            statement.setString(1, policyHolderId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                assertEquals(policyHolderId, resultSet.getString("pid"));
                assertEquals("John Test", resultSet.getString("full_name"));
                assertEquals("123456789", resultSet.getString("insurance_card"));
                assertEquals("123456789", resultSet.getString("phone_number"));
                assertEquals("test@gmail.com", resultSet.getString("email"));
                assertEquals("123 Main St", resultSet.getString("address"));
                assertEquals("123456789", resultSet.getString("poid"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(6)
    void deletePolicyHolder() {
        if (databaseDriver.deletePolicyHolder(policyHolderId)) {
            try (PreparedStatement statement = databaseDriver.getConn().prepareStatement("SELECT * FROM policy_holders WHERE pid = ?")) {
                statement.setString(1, policyHolderId);
                ResultSet resultSet = statement.executeQuery();
                assertFalse(resultSet.next());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //Dependent
    @Test
    @Order(7)
    void insertNewDependent() {
        Dependent dependent = new Dependent(dependentId, "John Doe", "123456789", "123456789", "test@gmail", "123 Main St", "123456789", "123456789");
        if (databaseDriver.insertNewDependent(dependent)) {
            try (PreparedStatement statement = databaseDriver.getConn().prepareStatement("SELECT * FROM dependents WHERE did = ?")) {
                statement.setString(1, dependentId);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    assertEquals(dependentId, resultSet.getString("did"));
                    assertEquals("John Doe", resultSet.getString("full_name"));
                    assertEquals("123456789", resultSet.getString("insurance_card"));
                    assertEquals("123456789", resultSet.getString("phone_number"));
                    assertEquals("test@gmail", resultSet.getString("email"));
                    assertEquals("123 Main St", resultSet.getString("address"));
                    assertEquals("123456789", resultSet.getString("pid"));
                    assertEquals("123456789", resultSet.getString("poid"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    @Order(8)
    void updateDependent() {
        databaseDriver.updateDependent(dependentId, "John Test", "123456789", "test@gmail", "123 Main St");
        try (PreparedStatement statement = databaseDriver.getConn().prepareStatement("SELECT * FROM dependents WHERE did = ?")) {
            statement.setString(1, dependentId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                assertEquals(dependentId, resultSet.getString("did"));
                assertEquals("John Test", resultSet.getString("full_name"));
                assertEquals("123456789", resultSet.getString("insurance_card"));
                assertEquals("123456789", resultSet.getString("phone_number"));
                assertEquals("test@gmail", resultSet.getString("email"));
                assertEquals("123 Main St", resultSet.getString("address"));
                assertEquals("123456789", resultSet.getString("pid"));
                assertEquals("123456789", resultSet.getString("poid"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(9)
    void deleteDependent() {
        if (databaseDriver.deleteDependent(dependentId)) {
            try (PreparedStatement statement = databaseDriver.getConn().prepareStatement("SELECT * FROM dependents WHERE did = ?")) {
                statement.setString(1, dependentId);
                ResultSet resultSet = statement.executeQuery();
                assertFalse(resultSet.next());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //Insurance Card
    @Test
    @Order(10)
    void insertInsuranceCard() {
        if (databaseDriver.insertInsuranceCard(insuranceCard)) {
            try (PreparedStatement statement = databaseDriver.getConn().prepareStatement("SELECT * FROM insurance_cards WHERE insurance_id = ?")) {
                statement.setString(1, insuranceCard.getCardNumber());
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    assertEquals(insuranceCard.getCardNumber(), resultSet.getString("insurance_id"));
                    assertEquals(policyHolderId, resultSet.getString("owner_id"));
                    assertEquals(LocalDate.now().plusYears(2), resultSet.getDate("expiration_date").toLocalDate());
                    assertEquals(PolicyOwnerModel.getInstance().getPolicyOwner().getId(), resultSet.getString("policy_owner"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    @Order(11)
    void deleteInsuranceCard() {
        if (databaseDriver.deleteInsuranceCard(insuranceCard.getCardNumber())) {
            try (PreparedStatement statement = databaseDriver.getConn().prepareStatement("SELECT * FROM insurance_cards WHERE insurance_id = ?")) {
                statement.setString(1, insuranceCard.getCardNumber());
                ResultSet resultSet = statement.executeQuery();
                assertFalse(resultSet.next());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //Account Data
    @Test
    @Order(12)
    void insertAccountData() {
        if (databaseDriver.insertAccountData("test", "test", "123456789", "PH")) {
            try (PreparedStatement statement = databaseDriver.getConn().prepareStatement("SELECT * FROM account WHERE username = ?")) {
                statement.setString(1, "test");
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    assertEquals("test", resultSet.getString("username"));
                    assertEquals("test", resultSet.getString("password"));
                    assertEquals("123456789", resultSet.getString("id"));
                    assertEquals("PH", resultSet.getString("role"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    @Order(12)
    void updateAccountData() {
        databaseDriver.updateAccountData("testH", "test1234", "123456789");
        try (PreparedStatement statement = databaseDriver.getConn().prepareStatement("SELECT * FROM account WHERE username = ?")) {
            statement.setString(1, "test");
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                assertEquals("testH", resultSet.getString("username"));
                assertEquals("test1234", resultSet.getString("password"));
                assertEquals("123456789", resultSet.getString("id"));
                assertEquals("PH", resultSet.getString("role"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(13)
    void deleteAccountData() {
        if (databaseDriver.deleteAccountData("123456789")) {
            try (PreparedStatement statement = databaseDriver.getConn().prepareStatement("SELECT * FROM account WHERE id = ?")) {
                statement.setString(1, "123456789");
                ResultSet resultSet = statement.executeQuery();
                assertFalse(resultSet.next());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //Policy Owner
    @Test
    @Order(14)
    void insertNewPolicyOwner() {
        PolicyOwner policyOwner = new PolicyOwner("123456789", "John Doe", 1000.0);
        if (databaseDriver.insertNewPolicyOwner(policyOwner)) {
            try (PreparedStatement statement = databaseDriver.getConn().prepareStatement("SELECT * FROM policy_owners WHERE poid = ?")) {
                statement.setString(1, "123456789");
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    assertEquals("123456789", resultSet.getString("poid"));
                    assertEquals("John Doe", resultSet.getString("name"));
                    assertEquals(1000.0, resultSet.getDouble("fee"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    @Order(15)
    void updatePolicyOwnerById() {
        PolicyOwnerModel.getInstance().getPolicyOwner().setName("John Test");
        PolicyOwnerModel.getInstance().getPolicyOwner().setFee(2000.0);
        databaseDriver.updatePolicyOwnerById("123456789");
        try (PreparedStatement statement = databaseDriver.getConn().prepareStatement("SELECT * FROM policy_owners WHERE poid = ?")) {
            statement.setString(1, "123456789");
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                assertEquals("123456789", resultSet.getString("poid"));
                assertEquals("John Test", resultSet.getString("name"));
                assertEquals(2000.0, resultSet.getDouble("fee"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(16)
    void deletePolicyOwnerById() {
        if (databaseDriver.deletePolicyOwnerById("123456789")) {
            try (PreparedStatement statement = databaseDriver.getConn().prepareStatement("SELECT * FROM policy_owners WHERE poid = ?")) {
                statement.setString(1, "123456789");
                ResultSet resultSet = statement.executeQuery();
                assertFalse(resultSet.next());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //Insurance Manager
    @Test
    @Order(17)
    void insertNewEmployee() {
        insuranceManagerId = AdminModel.getInstance().generateIdForInsuranceManager();
        Employee employee = new Employee(insuranceManagerId, "123456789", "123 Address", "John Doe", "test@gmail.com");
        if (databaseDriver.insertNewEmployee(employee)) {
            try (PreparedStatement statement = databaseDriver.getConn().prepareStatement("SELECT * FROM insurance_employees WHERE id = ?")) {
                statement.setString(1, insuranceManagerId);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    assertEquals(insuranceManagerId, resultSet.getString("id"));
                    assertEquals("John Doe", resultSet.getString("full_name"));
                    assertEquals("123456789", resultSet.getString("phone"));
                    assertEquals("123 Address", resultSet.getString("address"));
                    assertEquals("test@gmail.com", resultSet.getString("email"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    @Order(18)
    void updateManager() {
        databaseDriver.updateEmployee(insuranceManagerId, "John Test", "123456789", "Test@gmail.com", "Test Address");
        try (PreparedStatement statement = databaseDriver.getConn().prepareStatement("SELECT * FROM insurance_employees WHERE id = ?")) {
            statement.setString(1, insuranceManagerId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                assertEquals(insuranceManagerId, resultSet.getString("id"));
                assertEquals("John Test", resultSet.getString("full_name"));
                assertEquals("123456789", resultSet.getString("phone"));
                assertEquals("Test Address", resultSet.getString("address"));
                assertEquals("Test@gmail.com", resultSet.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(19)
    void deleteEmployee() {
        if (databaseDriver.deleteEmployee(insuranceManagerId)) {
            try (PreparedStatement statement = databaseDriver.getConn().prepareStatement("SELECT * FROM insurance_employees WHERE id = ?")) {
                statement.setString(1, insuranceManagerId);
                ResultSet resultSet = statement.executeQuery();
                assertFalse(resultSet.next());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //Insurance Surveyor
    @Test
    @Order(20)
    void insertNewSurveyor() {
        insuranceSurveyorId = AdminModel.getInstance().generateIdForInsuranceSurveyor();
        Employee employee = new Employee(insuranceSurveyorId, "123456789", "123 Address", "John Doe", "test@gmail.com");
        if (databaseDriver.insertNewEmployee(employee)) {
            try (PreparedStatement statement = databaseDriver.getConn().prepareStatement("SELECT * FROM insurance_employees WHERE id = ?")) {
                statement.setString(1, insuranceSurveyorId);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    assertEquals(insuranceSurveyorId, resultSet.getString("id"));
                    assertEquals("John Doe", resultSet.getString("full_name"));
                    assertEquals("123456789", resultSet.getString("phone"));
                    assertEquals("123 Address", resultSet.getString("address"));
                    assertEquals("test@gmail.com", resultSet.getString("email"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    @Order(21)
    void updateSurveyor() {
        databaseDriver.updateEmployee(insuranceSurveyorId, "John Test", "123456799", "Test@gmail.com", "Test Address");
        try (PreparedStatement statement = databaseDriver.getConn().prepareStatement("SELECT * FROM insurance_employees WHERE id = ?")) {
            statement.setString(1, insuranceSurveyorId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                assertEquals(insuranceSurveyorId, resultSet.getString("id"));
                assertEquals("John Test", resultSet.getString("full_name"));
                assertEquals("123456799", resultSet.getString("phone"));
                assertEquals("Test Address", resultSet.getString("address"));
                assertEquals("Test@gmail.com", resultSet.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(22)
    void deleteInsuranceSurveyor() {
        if (databaseDriver.deleteEmployee(insuranceSurveyorId)) {
            try (PreparedStatement statement = databaseDriver.getConn().prepareStatement("SELECT * FROM insurance_employees WHERE id = ?")) {
                statement.setString(1, insuranceSurveyorId);
                ResultSet resultSet = statement.executeQuery();
                assertFalse(resultSet.next());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}