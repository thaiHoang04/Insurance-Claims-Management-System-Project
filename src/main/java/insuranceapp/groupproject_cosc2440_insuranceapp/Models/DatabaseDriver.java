///**
// * @author Group 14
// */
//package insuranceapp.groupproject_cosc2440_insuranceapp.Models;
//
//import java.io.File;
//import java.io.IOException;
//import java.security.GeneralSecurityException;
//import java.sql.*;
//import java.text.NumberFormat;
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Locale;
//
//public class DatabaseDriver {
//    private Connection conn;
//
//    private GoogleDriveDatabase googleDriveDatabase;
//
//    //Constructor to connect to the database
//    public DatabaseDriver() {
//        try {
//            this.conn = DriverManager.getConnection("jdbc:postgresql://aws-0-ap-southeast-1.pooler.supabase.com:5432/postgres?user=postgres.wyemaioitieansuvjkoi&password=Team14GroupProjectInsuranceApp");
//            this.googleDriveDatabase = new GoogleDriveDatabase();
//        } catch (SQLException | IOException | GeneralSecurityException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public Connection getConn() {
//        return conn;
//    }
//
//    //Insert to the database
//    public boolean insertNewClaim (Claim claim) {
//        boolean flag = true;
//        try {
//            PreparedStatement pst = conn.prepareStatement("INSERT INTO claims (fid, claim_date, insured_person, card_number, exam_date, claim_amount, status, receiver_banking_info, policy_owner)" +
//                    "VALUES (?, ?, ?, ?, ? ,? ,? ,?, ?)");
//            pst.setString(1, claim.getId());
//            pst.setDate(2, Date.valueOf(claim.getClaimDate()));
//            pst.setString(3, claim.getInsuredPerson());
//            pst.setString(4, claim.getInsuranceCardNumber());
//            pst.setDate(5, Date.valueOf(claim.getExamDate()));
//            pst.setString(6, Double.toString(claim.getClaimAmount()));
//            pst.setString(7, claim.getStatus());
//            pst.setString(8, claim.getReceiverBankingInfo());
//            if (!(PolicyOwnerModel.getInstance().getPolicyOwner().getId() == null)) {
//                pst.setString(9, PolicyOwnerModel.getInstance().getPolicyOwner().getId());
//            } else {
//                pst.setString(9, PolicyHolderModel.getInstance().getPolicyHolder().getPolicyOwnerId());
//            }
//            pst.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            flag = false;
//        }
//        return flag;
//    }
//
//    public boolean uploadFile (List<File> fileList, String claimId, String cardNumber) {
//        boolean flag = true;
//        try {
//            for (File file: fileList) {
//                String fileName = claimId + "_" + cardNumber + "_" + file.getName();
//                String fileId = googleDriveDatabase.uploadFile(file, claimId, cardNumber);
//                PreparedStatement pst = conn.prepareStatement("INSERT INTO uploaded_file (name, file_id, fid)" +
//                        "VALUES (?, ?, ?)");
//                pst.setString(1, fileName);
//                pst.setString(2, fileId);
//                pst.setString(3, claimId);
//                pst.executeUpdate();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            flag = false;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return flag;
//    }
//
//    public boolean insertNewPolicyHolder (PolicyHolder policyHolder) {
//        boolean flag = true;
//        try {
//            PreparedStatement pst = conn.prepareStatement("INSERT INTO policy_holders (pid, full_name, insurance_card, email, phone_number, address, poid)" +
//                    "VALUES (?, ?, ?, ?, ?, ?, ?)");
//            pst.setString(1, policyHolder.getId());
//            pst.setString(2, policyHolder.getFullName());
//            pst.setString(3, policyHolder.getInsuranceCard());
//            pst.setString(4, policyHolder.getEmail());
//            pst.setString(5, policyHolder.getPhoneNumber());
//            pst.setString(6, policyHolder.getAddress());
//            pst.setString(7, policyHolder.getPolicyOwnerId());
//            pst.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            flag = false;
//        }
//        return flag;
//    }
//
//    public boolean insertNewDependent (Dependent dependent) {
//        boolean flag = true;
//        try {
//            PreparedStatement pst = conn.prepareStatement("INSERT INTO dependents (did, full_Name, insurance_card, address, email, phone_number, poid, pid)" +
//                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
//            pst.setString(1, dependent.getId());
//            pst.setString(2, dependent.getFullName());
//            pst.setString(3, dependent.getInsuranceCard());
//            pst.setString(4, dependent.getAddress());
//            pst.setString(5, dependent.getEmail());
//            pst.setString(6, dependent.getPhoneNumber());
//            pst.setString(7, dependent.getPolicyOwnerId());
//            pst.setString(8, dependent.getPolicyHolderId());
//            pst.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            flag = false;
//        }
//        return flag;
//    }
//
//    public boolean insertInsuranceCard(InsuranceCard insuranceCard) {
//        boolean flag = true;
//        try {
//            PreparedStatement pst = conn.prepareStatement("INSERT INTO insurance_cards (insurance_id, owner_id, policy_owner, expiration_date)" +
//                    "VALUES (?, ?, ?, ?)");
//            pst.setString(1, insuranceCard.getCardNumber());
//            pst.setString(2, insuranceCard.getCardHolder());
//            pst.setString(3, insuranceCard.getPolicyOwner());
//            pst.setDate(4, Date.valueOf(insuranceCard.getExpirationDate()));
//            pst.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            flag = false;
//        }
//        return flag;
//    }
//
//    public boolean insertAccountData(String username, String pwd, String id, String role) {
//        boolean flag = true;
//        try {
//            PreparedStatement pst = conn.prepareStatement("INSERT INTO account (username, password, id, role)" +
//                    "VALUES (?, ?, ?, ?)");
//            pst.setString(1, username);
//            pst.setString(2, pwd);
//            pst.setString(3, id);
//            pst.setString(4, role);
//            pst.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            flag = false;
//        }
//        return flag;
//    }
//
//    public boolean recordActivityHistory(String activity, String userId) {
//        boolean flag = true;
//        try {
//            PreparedStatement pst = conn.prepareStatement("INSERT INTO activity_history (created_at, activity, user_id)" +
//                    "VALUES (?, ?, ?)");
//            pst.setDate(1, Date.valueOf(LocalDate.now()));
//            pst.setString(2, activity);
//            pst.setString(3, userId);
//            pst.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            flag = false;
//        }
//        return flag;
//    }
//
//    public boolean insertNewPolicyOwner(PolicyOwner policyOwner) {
//        boolean flag = true;
//        try {
//            PreparedStatement pst = conn.prepareStatement("INSERT INTO policy_owners (poid, name, fee)" +
//                    "VALUES (?, ?, ?)");
//            pst.setString(1, policyOwner.getId());
//            pst.setString(2, policyOwner.getName());
//            pst.setDouble(3, policyOwner.getFee());
//            pst.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            flag = false;
//        }
//        return flag;
//    }
//
//    public boolean insertNewEmployee(Employee employee) {
//        boolean flag = true;
//        try {
//            PreparedStatement pst = conn.prepareStatement("INSERT INTO insurance_employees (id, full_name, email, phone, address)" +
//                    "VALUES (?, ?, ?, ?, ?)");
//            pst.setString(1, employee.getId());
//            pst.setString(2, employee.getFullName());
//            pst.setString(3, employee.getEmail());
//            pst.setString(4, employee.getPhone());
//            pst.setString(5, employee.getAddress());
//            pst.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            flag = false;
//        }
//        return flag;
//    }
//
//
//    //Delete the object from the database
//    public boolean deleteClaim(String id) {
//        boolean flag = true;
//        try {
//            PreparedStatement pst = conn.prepareStatement("DELETE FROM claims WHERE fid = ?");
//            pst.setString(1, id);
//            pst.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            flag = false;
//        }
//        return flag && deleteFileOfClaim(id);
//
//    }
//
//    public boolean deleteFileOfClaim(String id) {
//        boolean flag = true;
//        try {
//            PreparedStatement pst = conn.prepareStatement("DELETE FROM uploaded_file WHERE fid = ?");
//            pst.setString(1, id);
//            pst.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            flag = false;
//        }
//        return flag;
//    }
//
//    public boolean deletePolicyHolder(String id) {
//        boolean flag = true;
//        try {
//            PreparedStatement pst = conn.prepareStatement("DELETE FROM policy_holders WHERE pid = ?");
//            pst.setString(1, id);
//            pst.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            flag = false;
//        }
//        return flag;
//    }
//
//    public boolean deleteDependentByPolicyHolderId(String id) {
//        boolean flag = true;
//        try {
//            PreparedStatement pst = conn.prepareStatement("SELECT *  FROM dependents WHERE pid = ?");
//            pst.setString(1, id);
//            ResultSet rs = pst.executeQuery();
//            while (rs.next()) {
//                String did = rs.getString("did");
//                deleteDependent(did);
//                deleteAccountData(did);
//                deleteClaimByCustomerId(did);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            flag = false;
//        }
//        return flag;
//    }
//
//    public boolean deleteDependent(String id) {
//        boolean flag = true;
//        try {
//            PreparedStatement pst = conn.prepareStatement("DELETE FROM dependents WHERE did = ?");
//            pst.setString(1, id);
//            pst.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            flag = false;
//        }
//        return flag;
//    }
//
//    public boolean deleteClaimByCustomerId(String id) {
//        boolean flag = true;
//        try {
//            PreparedStatement pst = conn.prepareStatement("DELETE FROM claims WHERE insured_person = ?");
//            pst.setString(1, id);
//            pst.executeUpdate();
//            PolicyOwnerModel.getInstance().getClaims().removeIf(claim -> claim.getInsuredPerson().equals(id));
//            PolicyOwnerModel.getInstance().getPolicyOwnerViewFactory().updateClaimView();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            flag = false;
//        }
//        return flag;
//    }
//
//    public boolean deleteClaimByInsuranceCard(String id) {
//        boolean flag = true;
//        try {
//            PreparedStatement pst = conn.prepareStatement("DELETE FROM claims WHERE card_number = ?");
//            pst.setString(1, id);
//            pst.executeUpdate();
//            PolicyOwnerModel.getInstance().getClaims().removeIf(claim -> claim.getInsuranceCardNumber().equals(id));
//            PolicyOwnerModel.getInstance().getPolicyOwnerViewFactory().updateClaimView();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            flag = false;
//        }
//        return flag;
//    }
//
//    public boolean deleteInsuranceCard(String id) {
//        boolean flag = true;
//        try {
//            PreparedStatement pst = conn.prepareStatement("DELETE FROM insurance_cards WHERE insurance_id = ?");
//            pst.setString(1, id);
//            pst.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            flag = false;
//        }
//        return flag;
//    }
//
//    public boolean deleteInsuranceCardByPolicyOwnerId(String id) {
//        boolean flag = true;
//        try {
//            PreparedStatement pst = conn.prepareStatement("DELETE FROM insurance_cards WHERE policy_owner = ?");
//            pst.setString(1, id);
//            pst.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            flag = false;
//        }
//        return flag;
//    }
//
//    public boolean deleteAccountData(String id) {
//        boolean flag = true;
//        try {
//            PreparedStatement pst = conn.prepareStatement("DELETE FROM account WHERE id = ?");
//            pst.setString(1, id);
//            pst.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            flag = false;
//        }
//        return flag;
//    }
//
//    public boolean deletePolicyOwnerById(String id) {
//        boolean flag = true;
//        try {
//            PreparedStatement pst = conn.prepareStatement("DELETE FROM policy_owners WHERE poid = ?");
//            pst.setString(1, id);
//            pst.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            flag = false;
//        }
//        return flag;
//    }
//
//    public boolean deleteClaimByPolicyOwnerId(String id) {
//        boolean flag = true;
//        try {
//            PreparedStatement pst = conn.prepareStatement("DELETE FROM claims WHERE policy_owner = ?");
//            pst.setString(1, id);
//            pst.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            flag = false;
//        }
//        return flag;
//    }
//
//    public boolean deleteDependentByPolicyOwnerId(String id) {
//        boolean flag = true;
//        try {
//            PreparedStatement pst = conn.prepareStatement("SELECT *  FROM dependents WHERE poid = ?");
//            pst.setString(1, id);
//            ResultSet rs = pst.executeQuery();
//            while (rs.next()) {
//                String did = rs.getString("did");
//                deleteDependent(did);
//                deleteAccountData(did);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            flag = false;
//        }
//        return flag;
//    }
//
//    public boolean deletePolicyHolderByPolicyOwnerID(String id) {
//        boolean flag = true;
//        try {
//            PreparedStatement pst = conn.prepareStatement("SELECT *  FROM policy_holders WHERE poid = ?");
//            pst.setString(1, id);
//            ResultSet rs = pst.executeQuery();
//            while (rs.next()) {
//                String pid = rs.getString("pid");
//                deletePolicyHolder(pid);
//                deleteAccountData(pid);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            flag = false;
//        }
//        return flag;
//    }
//
//    public boolean deleteEmployee(String id) {
//        boolean flag = true;
//        try {
//            PreparedStatement pst = conn.prepareStatement("DELETE FROM insurance_employees WHERE id = ?");
//            pst.setString(1, id);
//            pst.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            flag = false;
//        }
//        return flag;
//    }
//
//    //Get data from database
//    public List<Claim> getListClaimByPolicyOwnerId(String searchString, String fileterStatus , String poid) {
//        List<Claim> claimList = new ArrayList<>();
//        String sqlStatement = "";
//        if (fileterStatus != null && !fileterStatus.equals("ALL")) {
//            sqlStatement = "SELECT * FROM claims WHERE fid LIKE ? AND policy_owner = ? AND status = ?";
//        } else {
//            sqlStatement = "SELECT * FROM claims WHERE fid LIKE ? AND policy_owner = ?";
//        }
//        try (PreparedStatement pstmt = conn.prepareStatement(sqlStatement)) {
//            pstmt.setString(1, "%" + searchString + "%");
//            pstmt.setString(2, poid);
//            if (fileterStatus != null && !fileterStatus.equals("ALL")) {
//                pstmt.setString(3, fileterStatus);
//            }
//            ResultSet rs = pstmt.executeQuery();
//            while (rs.next()) {
//                String fid, insuredPerson, insuranceCardNumber, claimAmount, status, receiverBankingInfo;
//                Date claimDate, examDate;
//                fid = rs.getString("fid");
//                claimDate = rs.getDate("claim_date");
//                insuredPerson = rs.getString("insured_person");
//                insuranceCardNumber = rs.getString("card_number");
//                claimAmount = rs.getString("claim_amount");
//                claimAmount = claimAmount.substring(1);
//                claimAmount = claimAmount.replace(",", "");
//                examDate = rs.getDate("exam_date");
//                status = rs.getString("status");
//                receiverBankingInfo = rs.getString("receiver_banking_info");
//                Claim claim = new Claim(fid, claimDate.toLocalDate(), insuredPerson, insuranceCardNumber, examDate.toLocalDate(), Double.parseDouble(claimAmount) ,status, receiverBankingInfo);
//                claimList.add(claim);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return claimList;
//    }
//
//    public List<Claim> getListClaimByInsuredPersonId(String searchString, String fileterStatus , String id) {
//        List<Claim> claimList = new ArrayList<>();
//        String sqlStatement = "";
//        if (fileterStatus != null && !fileterStatus.equals("ALL")) {
//            sqlStatement = "SELECT * FROM claims WHERE fid LIKE ? AND insured_person = ? AND status = ?";
//        } else {
//            sqlStatement = "SELECT * FROM claims WHERE fid LIKE ? AND insured_person = ?";
//        }
//        try (PreparedStatement pstmt = conn.prepareStatement(sqlStatement)) {
//            pstmt.setString(1, "%" + searchString + "%");
//            pstmt.setString(2, id);
//            if (fileterStatus != null && !fileterStatus.equals("ALL")) {
//                pstmt.setString(3, fileterStatus);
//            }
//            ResultSet rs = pstmt.executeQuery();
//            while (rs.next()) {
//                String fid, insuredPerson, insuranceCardNumber, claimAmount, status, receiverBankingInfo;
//                Date claimDate, examDate;
//                fid = rs.getString("fid");
//                claimDate = rs.getDate("claim_date");
//                insuredPerson = rs.getString("insured_person");
//                insuranceCardNumber = rs.getString("card_number");
//                claimAmount = rs.getString("claim_amount");
//                claimAmount = claimAmount.substring(1);
//                claimAmount = claimAmount.replace(",", "");
//                examDate = rs.getDate("exam_date");
//                status = rs.getString("status");
//                receiverBankingInfo = rs.getString("receiver_banking_info");
//                Claim claim = new Claim(fid, claimDate.toLocalDate(), insuredPerson, insuranceCardNumber, examDate.toLocalDate(), Double.parseDouble(claimAmount) ,status, receiverBankingInfo);
//                claimList.add(claim);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return claimList;
//    }
//
//    public List<Claim> searchListClaim(String searchString, String fileterStatus) {
//        List<Claim> claimList = new ArrayList<>();
//        String sqlStatement = "";
//        if (fileterStatus != null && !fileterStatus.equals("ALL")) {
//            sqlStatement = "SELECT * FROM claims WHERE fid LIKE ? AND status = ?";
//        } else {
//            sqlStatement = "SELECT * FROM claims WHERE fid LIKE ?";
//        }
//        try (PreparedStatement pstmt = conn.prepareStatement(sqlStatement)) {
//            pstmt.setString(1, "%" + searchString + "%");
//            if (fileterStatus != null && fileterStatus != "ALL") {
//                pstmt.setString(2, fileterStatus);
//            }
//            ResultSet rs = pstmt.executeQuery();
//            while (rs.next()) {
//                String fid, insuredPerson, insuranceCardNumber, claimAmount, status, receiverBankingInfo;
//                Date claimDate, examDate;
//                fid = rs.getString("fid");
//                claimDate = rs.getDate("claim_date");
//                insuredPerson = rs.getString("insured_person");
//                insuranceCardNumber = rs.getString("card_number");
//                claimAmount = rs.getString("claim_amount");
//                claimAmount = claimAmount.substring(1);
//                claimAmount = claimAmount.replace(",", "");
//                examDate = rs.getDate("exam_date");
//                status = rs.getString("status");
//                receiverBankingInfo = rs.getString("receiver_banking_info");
//                Claim claim = new Claim(fid, claimDate.toLocalDate(), insuredPerson, insuranceCardNumber, examDate.toLocalDate(), Double.parseDouble(claimAmount) ,status, receiverBankingInfo);
//                claimList.add(claim);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return claimList;
//    }
//
//    public List<Claim> searchListClaimForPolicyHolder(String searchString, String fileterStatus, String id) {
//        List<Claim> claimList = new ArrayList<>();
//        String sqlStatement = "";
//        if (fileterStatus != null && !fileterStatus.equals("ALL")) {
//            sqlStatement = "SELECT * FROM claims WHERE fid LIKE ? AND status = ? AND card_number = ?";
//        } else {
//            sqlStatement = "SELECT * FROM claims WHERE fid LIKE ? AND card_number = ?";
//        }
//        try (PreparedStatement pstmt = conn.prepareStatement(sqlStatement)) {
//            pstmt.setString(1, "%" + searchString + "%");
//            if (fileterStatus != null && fileterStatus != "ALL") {
//                pstmt.setString(2, fileterStatus);
//                pstmt.setString(3, id);
//            } else {
//                pstmt.setString(2, id);
//            }
//            ResultSet rs = pstmt.executeQuery();
//            while (rs.next()) {
//                String fid, insuredPerson, insuranceCardNumber, claimAmount, status, receiverBankingInfo;
//                Date claimDate, examDate;
//                fid = rs.getString("fid");
//                claimDate = rs.getDate("claim_date");
//                insuredPerson = rs.getString("insured_person");
//                insuranceCardNumber = rs.getString("card_number");
//                claimAmount = rs.getString("claim_amount");
//                claimAmount = claimAmount.substring(1);
//                claimAmount = claimAmount.replace(",", "");
//                examDate = rs.getDate("exam_date");
//                status = rs.getString("status");
//                receiverBankingInfo = rs.getString("receiver_banking_info");
//                Claim claim = new Claim(fid, claimDate.toLocalDate(), insuredPerson, insuranceCardNumber, examDate.toLocalDate(), Double.parseDouble(claimAmount) ,status, receiverBankingInfo);
//                claimList.add(claim);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return claimList;
//    }
//
//    public List<PolicyHolder> getListPolicyHolderById(String searchString, String poid) {
//        List<PolicyHolder> policyHolderList = new ArrayList<>();
//        String sqlStatement;
//        if (poid == null) {
//            sqlStatement = "SELECT * FROM policy_holders WHERE pid LIKE ?";
//        } else {
//            sqlStatement = "SELECT * FROM policy_holders WHERE pid LIKE ? AND poid = ?";
//        }
//        try (PreparedStatement pstmt = conn.prepareStatement(sqlStatement)) {
//            pstmt.setString(1, "%" + searchString + "%");
//            if (poid != null) {
//                pstmt.setString(2, poid);
//            }
//            ResultSet rs = pstmt.executeQuery();
//            while (rs.next()) {
//                String pid, fullName, insuranceCardNumber, email, phoneNumber, address;
//                pid = rs.getString("pid");
//                fullName = rs.getString("full_name");
//                insuranceCardNumber = rs.getString("insurance_card");
//                email = rs.getString("email");
//                phoneNumber = rs.getString("phone_number");
//                address = rs.getString("address");
//                PolicyHolder policyHolder = new PolicyHolder(pid, fullName, insuranceCardNumber, phoneNumber, email, address, poid);
//                policyHolderList.add(policyHolder);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return policyHolderList;
//    }
//
//    public List<Dependent> searchListDependentById(String searchString, String pid) {
//        List<Dependent> dependentsList = new ArrayList<>();
//        try (PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM dependents WHERE did LIKE ? AND insurance_card = ?")) {
//            pstmt.setString(1, "%" + searchString + "%");
//            pstmt.setString(2, pid);
//            ResultSet rs = pstmt.executeQuery();
//            while (rs.next()) {
//                String did, fullName, insuranceCardNumber, email, phoneNumber, address, poid;
//                did = rs.getString("did");
//                fullName = rs.getString("full_name");
//                insuranceCardNumber = rs.getString("insurance_card");
//                email = rs.getString("email");
//                phoneNumber = rs.getString("phone_number");
//                address = rs.getString("address");
//                poid = rs.getString("poid");
//                Dependent dependent = new Dependent(did, fullName, insuranceCardNumber, phoneNumber, email, address, pid, poid);
//                dependentsList.add(dependent);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return dependentsList;
//    }
//
//    public List<PolicyOwner> getListPolicyOwnerForAdmin(String searchString) {
//        List<PolicyOwner> policyOwnerList = new ArrayList<>();
//        try (PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM policy_owners WHERE poid LIKE ?")) {
//            pstmt.setString(1, "%" + searchString + "%");
//            ResultSet rs = pstmt.executeQuery();
//            while (rs.next()) {
//                String poid, name;
//                double fee;
//                poid = rs.getString("poid");
//                name = rs.getString("name");
//                fee = rs.getDouble("fee");
//                PolicyOwner policyOwner = new PolicyOwner(poid, name, fee);
//                policyOwnerList.add(policyOwner);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return policyOwnerList;
//    }
//
//    public List<Claim> getListClaimByStatusWithPolicyOwnerID(String searchString, String poid) {
//        List<Claim> claimList = new ArrayList<>();
//        try (PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM claims WHERE status = ? and policy_owner = ?")) {
//            pstmt.setString(1, searchString);
//            pstmt.setString(2, poid);
//            ResultSet rs = pstmt.executeQuery();
//            while (rs.next()) {
//                String fid, insuredPerson, insuranceCardNumber, claimAmount, status, receiverBankingInfo;
//                Date claimDate, examDate;
//                fid = rs.getString("fid");
//                claimDate = rs.getDate("claim_date");
//                insuredPerson = rs.getString("insured_person");
//                insuranceCardNumber = rs.getString("card_number");
//                claimAmount = rs.getString("claim_amount");
//                claimAmount = claimAmount.substring(1);
//                claimAmount = claimAmount.replace(",", "");
//                examDate = rs.getDate("exam_date");
//                status = rs.getString("status");
//                receiverBankingInfo = rs.getString("receiver_banking_info");
//                Claim claim = new Claim(fid, claimDate.toLocalDate(), insuredPerson, insuranceCardNumber, examDate.toLocalDate(), Double.parseDouble(claimAmount) ,status, receiverBankingInfo);
//                claimList.add(claim);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return claimList;
//    }
//
//    public List<Claim> getListClaimByStatusWithInsuredPersonID(String searchString, String id) {
//        List<Claim> claimList = new ArrayList<>();
//        try (PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM claims WHERE status = ? and insured_person = ?")) {
//            pstmt.setString(1, searchString);
//            pstmt.setString(2, id);
//            ResultSet rs = pstmt.executeQuery();
//            while (rs.next()) {
//                String fid, insuredPerson, insuranceCardNumber, claimAmount, status, receiverBankingInfo;
//                Date claimDate, examDate;
//                fid = rs.getString("fid");
//                claimDate = rs.getDate("claim_date");
//                insuredPerson = rs.getString("insured_person");
//                insuranceCardNumber = rs.getString("card_number");
//                claimAmount = rs.getString("claim_amount");
//                claimAmount = claimAmount.substring(1);
//                claimAmount = claimAmount.replace(",", "");
//                examDate = rs.getDate("exam_date");
//                status = rs.getString("status");
//                receiverBankingInfo = rs.getString("receiver_banking_info");
//                Claim claim = new Claim(fid, claimDate.toLocalDate(), insuredPerson, insuranceCardNumber, examDate.toLocalDate(), Double.parseDouble(claimAmount) ,status, receiverBankingInfo);
//                claimList.add(claim);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return claimList;
//    }
//
//    public List<Claim> searchListClaimByStatus(String searchString) {
//        List<Claim> claimList = new ArrayList<>();
//        String sqlStatement;
//        if (searchString == "ALL") {
//            sqlStatement = "SELECT * FROM claims";
//        } else {
//            sqlStatement = "SELECT * FROM claims WHERE status = ?";
//        }
//        try (PreparedStatement pstmt = conn.prepareStatement(sqlStatement)) {
//            if (searchString != "ALL") {
//                pstmt.setString(1, searchString);
//            }
//            ResultSet rs = pstmt.executeQuery();
//            while (rs.next()) {
//                String fid, insuredPerson, insuranceCardNumber, claimAmount, status, receiverBankingInfo;
//                Date claimDate, examDate;
//                fid = rs.getString("fid");
//                claimDate = rs.getDate("claim_date");
//                insuredPerson = rs.getString("insured_person");
//                insuranceCardNumber = rs.getString("card_number");
//                claimAmount = rs.getString("claim_amount");
//                claimAmount = claimAmount.substring(1);
//                claimAmount = claimAmount.replace(",", "");
//                examDate = rs.getDate("exam_date");
//                status = rs.getString("status");
//                receiverBankingInfo = rs.getString("receiver_banking_info");
//                Claim claim = new Claim(fid, claimDate.toLocalDate(), insuredPerson, insuranceCardNumber, examDate.toLocalDate(), Double.parseDouble(claimAmount) ,status, receiverBankingInfo);
//                claimList.add(claim);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return claimList;
//    }
//
//    public List<Claim> searchListClaimByStatusForPolicyHolder(String searchString, String id) {
//        List<Claim> claimList = new ArrayList<>();
//        String sqlStatement;
//        if (searchString == "ALL") {
//            sqlStatement = "SELECT * FROM claims WHERE card_number = ?";
//        } else {
//            sqlStatement = "SELECT * FROM claims WHERE status = ? AND card_number = ?";
//        }
//        try (PreparedStatement pstmt = conn.prepareStatement(sqlStatement)) {
//            if (searchString != "ALL") {
//                pstmt.setString(1, searchString);
//                pstmt.setString(2, id);
//            } else {
//                pstmt.setString(1, id);
//            }
//            ResultSet rs = pstmt.executeQuery();
//            while (rs.next()) {
//                String fid, insuredPerson, insuranceCardNumber, claimAmount, status, receiverBankingInfo;
//                Date claimDate, examDate;
//                fid = rs.getString("fid");
//                claimDate = rs.getDate("claim_date");
//                insuredPerson = rs.getString("insured_person");
//                insuranceCardNumber = rs.getString("card_number");
//                claimAmount = rs.getString("claim_amount");
//                claimAmount = claimAmount.substring(1);
//                claimAmount = claimAmount.replace(",", "");
//                examDate = rs.getDate("exam_date");
//                status = rs.getString("status");
//                receiverBankingInfo = rs.getString("receiver_banking_info");
//                Claim claim = new Claim(fid, claimDate.toLocalDate(), insuredPerson, insuranceCardNumber, examDate.toLocalDate(), Double.parseDouble(claimAmount) ,status, receiverBankingInfo);
//                claimList.add(claim);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return claimList;
//    }
//
//    public List<DocumentOrg> getListOfFile(String id) {
//        List<DocumentOrg> documentList = new ArrayList<>();
//        try (PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM uploaded_file WHERE fid = ?")) {
//            pstmt.setString(1, id);
//            ResultSet rs = pstmt.executeQuery();
//            while (rs.next()) {
//                String name, fileId;
//                name = rs.getString("name");
//                fileId = rs.getString("file_id");
//                DocumentOrg document = new DocumentOrg(fileId, name);
//                documentList.add(document);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return documentList;
//    }
//
//    public List<Employee> searchListInsuranceManagerWithId(String searchString) {
//        List<Employee> employeeList = new ArrayList<>();
//        try (PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM insurance_employees WHERE id LIKE ?")) {
//            if (searchString.charAt(0) == 'm') {
//                pstmt.setString(1, searchString + "%");
//            } else {
//                pstmt.setString(1, "m-%" + searchString + "%");
//            }
//            ResultSet rs = pstmt.executeQuery();
//            while (rs.next()) {
//                String id, fullName, email, phone, address;
//                id = rs.getString("id");
//                fullName = rs.getString("full_name");
//                email = rs.getString("email");
//                phone = rs.getString("phone");
//                address = rs.getString("address");
//                Employee employee = new Employee(id, phone, address ,fullName, email);
//                employeeList.add(employee);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return employeeList;
//    }
//
//    public List<Dependent> getListDependentById(String searchString, String pid) {
//        List<Dependent> dependentList = new ArrayList<>();
//        try (PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM dependents WHERE did LIKE ? AND pid = ?")) {
//            pstmt.setString(1, searchString + "%");
//            pstmt.setString(2, pid);
//            ResultSet rs = pstmt.executeQuery();
//            while (rs.next()) {
//                String did, fullName, insuranceCardNumber, email, phoneNumber, address, poid;
//                did = rs.getString("did");
//                fullName = rs.getString("full_name");
//                insuranceCardNumber = rs.getString("insurance_card");
//                email = rs.getString("email");
//                phoneNumber = rs.getString("phone_number");
//                address = rs.getString("address");
//                poid = rs.getString("poid");
//                Dependent dependent = new Dependent(did, fullName, insuranceCardNumber, phoneNumber, email, address, pid, poid);
//                dependentList.add(dependent);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return dependentList;
//    }
//
//    public List<Claim> getListClaimByClaimId(String searchString, String fileterStatus ,String pid) {
//        List<Claim> claimList = new ArrayList<>();
//        String sqlStatement = "";
//        if (fileterStatus != null) {
//            sqlStatement = "SELECT * FROM claims WHERE fid LIKE ? AND pid = ? AND status = ?";
//        } else {
//            sqlStatement = "SELECT * FROM claims WHERE fid LIKE ? AND pid = ?";
//        }
//        try (PreparedStatement pstmt = conn.prepareStatement(sqlStatement)) {
//            pstmt.setString(1, searchString + "%");
//            pstmt.setString(2, pid);
//            if (fileterStatus != null) {
//                pstmt.setString(3, fileterStatus);
//            }
//            ResultSet rs = pstmt.executeQuery();
//            while (rs.next()) {
//                String fid, insuredPerson, insuranceCardNumber, claimAmount, status, receiverBankingInfo;
//                LocalDate claimDate, examDate;
//                fid = rs.getString("fid");
//                claimDate = rs.getDate("claim_date").toLocalDate();
//                insuredPerson = rs.getString("insured_person");
//                insuranceCardNumber = rs.getString("card_number");
//                claimAmount = rs.getString("claim_amount");
//                claimAmount = claimAmount.substring(1);
//                claimAmount = claimAmount.replace(",", "");
//                examDate = rs.getDate("exam_date").toLocalDate();
//                status = rs.getString("status");
//                receiverBankingInfo = rs.getString("receiver_banking_info");
//                Claim claim = new Claim(fid, claimDate, insuredPerson, insuranceCardNumber, examDate, Double.parseDouble(claimAmount) ,status, receiverBankingInfo);
//                claimList.add(claim);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return claimList;
//    }
//
//    public List<Claim> getListClaimByStatus(String searchString, String pid) {
//        List<Claim> claimList = new ArrayList<>();
//        try (PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM claims WHERE status = ? and pid = ?")) {
//            pstmt.setString(1, searchString);
//            pstmt.setString(2, pid);
//            ResultSet rs = pstmt.executeQuery();
//            while (rs.next()) {
//                String fid, insuredPerson, insuranceCardNumber, claimAmount, status, receiverBankingInfo;
//                LocalDate claimDate, examDate;
//                fid = rs.getString("fid");
//                claimDate = rs.getDate("claim_date").toLocalDate();
//                insuredPerson = rs.getString("insured_person");
//                insuranceCardNumber = rs.getString("card_number");
//                claimAmount = rs.getString("claim_amount");
//                claimAmount = claimAmount.substring(1);
//                claimAmount = claimAmount.replace(",", "");
//                examDate = rs.getDate("exam_date").toLocalDate();
//                status = rs.getString("status");
//                receiverBankingInfo = rs.getString("receiver_banking_info");
//                Claim claim = new Claim(fid, claimDate, insuredPerson, insuranceCardNumber, examDate, Double.parseDouble(claimAmount) ,status, receiverBankingInfo);
//                claimList.add(claim);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return claimList;
//    }
//
//    public List<Employee> searchListInsuranceSurveyorWithId(String searchString) {
//        List<Employee> employeeList = new ArrayList<>();
//        try (PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM insurance_employees WHERE id LIKE ?")) {
//            if (searchString.charAt(0) == 's') {
//                pstmt.setString(1, searchString + "%");
//            } else {
//                pstmt.setString(1, "s-%" + searchString + "%");
//            }
//            ResultSet rs = pstmt.executeQuery();
//            while (rs.next()) {
//                String id, fullName, email, phone, address;
//                id = rs.getString("id");
//                fullName = rs.getString("full_name");
//                email = rs.getString("email");
//                phone = rs.getString("phone");
//                address = rs.getString("address");
//                Employee employee = new Employee(id, phone, address ,fullName, email);
//                employeeList.add(employee);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return employeeList;
//    }
//
//    public int countNumberOfClaimById(String id) {
//        int count = 0;
//        try {
//            PreparedStatement statement = conn.prepareStatement("SELECT COUNT(id) FROM claims WHERE policy_owner = ? ");
//            statement.setString(1, id);
//            ResultSet rs = statement.executeQuery();
//            if (rs.next()) {
//                count = rs.getInt(1);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return count;
//    }
//
//    public int countNumberOfClaim() {
//        int count = 0;
//        try {
//            Statement statement = conn.createStatement();
//            ResultSet rs = statement.executeQuery("SELECT COUNT(id) FROM claims");
//            if (rs.next()) {
//                count = rs.getInt(1);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return count;
//    }
//
//    public int getTheLastNumberOfClaim() {
//        int count = 0;
//        try {
//            Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
//            ResultSet rs = statement.executeQuery("SELECT id FROM claims ORDER BY id ASC ");
//            if (rs.last()) {
//                count = rs.getInt(1);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return count;
//    }
//
//    public int getTheLastNumberOfPolicyOwner() {
//        int count = 0;
//        try {
//            Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
//            ResultSet rs = statement.executeQuery("SELECT id FROM policy_owners ORDER BY id ASC ");
//            if (rs.last()) {
//                count = rs.getInt(1);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return count;
//    }
//
//    public int getTheLastNumberOfEmployee() {
//        int count = 0;
//        try {
//            Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
//            ResultSet rs = statement.executeQuery("SELECT unique_id FROM insurance_employees ORDER BY unique_id ASC");
//            if (rs.last()) {
//                count = rs.getInt(1);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return count;
//    }
//
//    public int countNumberOfPendingClaim(String id) {
//        int count = 0;
//        try {
//            PreparedStatement statement = conn.prepareStatement("SELECT COUNT(id) FROM claims WHERE policy_owner = ? AND status = 'pending' ");
//            statement.setString(1, id);
//            ResultSet rs = statement.executeQuery();
//            if (rs.next()) {
//                count = rs.getInt(1);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return count;
//    }
//
//    public int countNumberOfInsuranceCard() {
//        int count = 0;
//        try {
//            Statement statement = conn.createStatement();
//            ResultSet rs = statement.executeQuery("SELECT COUNT(id) FROM insurance_cards");
//            if (rs.next()) {
//                count = rs.getInt(1);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return count;
//    }
//
//    public int countNumberOfPolicyHolders(String id) {
//        int count = 0;
//        try {
//            PreparedStatement preparedStatement = conn.prepareStatement("SELECT COUNT(id) FROM policy_holders WHERE poid = ? ");
//            preparedStatement.setString(1, id);
//            ResultSet rs = preparedStatement.executeQuery();
//            if (rs.next()) {
//                count = rs.getInt(1);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return count;
//    }
//
//    public int countNumberOfDependents(String id) {
//        int count = 0;
//        try {
//            PreparedStatement preparedStatement = conn.prepareStatement("SELECT COUNT(id) FROM dependents WHERE poid = ?");
//            preparedStatement.setString(1, id);
//            ResultSet rs = preparedStatement.executeQuery();
//            if (rs.next()) {
//                count = rs.getInt(1);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return count;
//    }
//
//    public void getListClaimByPolicyOwnerId(String id) {
//        try (PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM claims WHERE policy_owner = ?")) {
//            pstmt.setString(1, id);
//            ResultSet rs = pstmt.executeQuery();
//            while (rs.next()) {
//                String fid, insuredPerson, insuranceCardNumber, claimAmount, status, receiverBankingInfo;
//                Date claimDate, examDate;
//                fid = rs.getString("fid");
//                claimDate = rs.getDate("claim_date");
//                insuredPerson = rs.getString("insured_person");
//                insuranceCardNumber = rs.getString("card_number");
//                claimAmount = rs.getString("claim_amount");
//                claimAmount = claimAmount.substring(1);
//                claimAmount = claimAmount.replace(",", "");
//                examDate = rs.getDate("exam_date");
//                status = rs.getString("status");
//                receiverBankingInfo = rs.getString("receiver_banking_info");
//                Claim claim = new Claim(fid, claimDate.toLocalDate(), insuredPerson, insuranceCardNumber, examDate.toLocalDate(), Double.parseDouble(claimAmount) ,status, receiverBankingInfo);
//                PolicyOwnerModel.getInstance().addClaims(claim);
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public void getListClaimByInsuredPersonId(String id) {
//        try (PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM claims WHERE insured_person = ?")) {
//            pstmt.setString(1, id);
//            ResultSet rs = pstmt.executeQuery();
//            while (rs.next()) {
//                String fid, insuredPerson, insuranceCardNumber, claimAmount, status, receiverBankingInfo;
//                Date claimDate, examDate;
//                fid = rs.getString("fid");
//                claimDate = rs.getDate("claim_date");
//                insuredPerson = rs.getString("insured_person");
//                insuranceCardNumber = rs.getString("card_number");
//                claimAmount = rs.getString("claim_amount");
//                claimAmount = claimAmount.substring(1);
//                claimAmount = claimAmount.replace(",", "");
//                examDate = rs.getDate("exam_date");
//                status = rs.getString("status");
//                receiverBankingInfo = rs.getString("receiver_banking_info");
//                Claim claim = new Claim(fid, claimDate.toLocalDate(), insuredPerson, insuranceCardNumber, examDate.toLocalDate(), Double.parseDouble(claimAmount) ,status, receiverBankingInfo);
//                DependentModel.getInstance().addClaim(claim);
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public void getListClaimForAdmin() {
//        try (Statement statement = conn.createStatement()) {
//            ResultSet rs = statement.executeQuery("SELECT * FROM claims");
//            while (rs.next()) {
//                String fid, insuredPerson, insuranceCardNumber, claimAmount, status, receiverBankingInfo;
//                Date claimDate, examDate;
//                fid = rs.getString("fid");
//                claimDate = rs.getDate("claim_date");
//                insuredPerson = rs.getString("insured_person");
//                insuranceCardNumber = rs.getString("card_number");
//                claimAmount = rs.getString("claim_amount");
//                claimAmount = claimAmount.substring(1);
//                claimAmount = claimAmount.replace(",", "");
//                examDate = rs.getDate("exam_date");
//                status = rs.getString("status");
//                receiverBankingInfo = rs.getString("receiver_banking_info");
//                Claim claim = new Claim(fid, claimDate.toLocalDate(), insuredPerson, insuranceCardNumber, examDate.toLocalDate(), Double.parseDouble(claimAmount) ,status, receiverBankingInfo);
//                AdminModel.getInstance().addClaim(claim);
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public void getListClaimForPolicyHolder(String id) {
//        try (PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM claims WHERE card_number = ?")) {
//            System.out.println(id);
//            pstmt.setString(1, id);
//            ResultSet rs = pstmt.executeQuery();
//            while (rs.next()) {
//                String fid, insuredPerson, insuranceCardNumber, claimAmount, status, receiverBankingInfo;
//                Date claimDate, examDate;
//                fid = rs.getString("fid");
//                claimDate = rs.getDate("claim_date");
//                insuredPerson = rs.getString("insured_person");
//                insuranceCardNumber = rs.getString("card_number");
//                claimAmount = rs.getString("claim_amount");
//                claimAmount = claimAmount.substring(1);
//                claimAmount = claimAmount.replace(",", "");
//                examDate = rs.getDate("exam_date");
//                status = rs.getString("status");
//                receiverBankingInfo = rs.getString("receiver_banking_info");
//                Claim claim = new Claim(fid, claimDate.toLocalDate(), insuredPerson, insuranceCardNumber, examDate.toLocalDate(), Double.parseDouble(claimAmount) ,status, receiverBankingInfo);
//                PolicyHolderModel.getInstance().addClaim(claim);
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public void getListPolicyOwnerForAdmin() {
//        try (Statement pstmt = conn.createStatement()) {
//            ResultSet rs = pstmt.executeQuery("SELECT * FROM policy_owners");
//            while (rs.next()) {
//                String poid, name;
//                Double fee;
//                poid = rs.getString("poid");
//                name = rs.getString("name");
//                fee = rs.getDouble("fee");
//                PolicyOwner policyOwner = new PolicyOwner(poid, name, fee);
//                AdminModel.getInstance().addPolicyOwners(policyOwner);
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public void getListPolicyHolderByPolicyOwnerId(String id) {
//        try (PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM policy_holders WHERE poid = ?")) {
//            pstmt.setString(1, id);
//            ResultSet rs = pstmt.executeQuery();
//            while (rs.next()) {
//                String idPH, fullName, insuranceCard, email, address, phoneNum, policyOwnerId;
//                idPH = rs.getString("pid");
//                fullName = rs.getString("full_name");
//                insuranceCard = rs.getString("insurance_card");
//                email = rs.getString("email");
//                address = rs.getString("address");
//                phoneNum = rs.getString("phone_number");
//                policyOwnerId = rs.getString("poid");
//                PolicyHolder policyHolder = new PolicyHolder(idPH, fullName, insuranceCard, phoneNum, email, address, policyOwnerId);
//                PolicyOwnerModel.getInstance().addPolicyHolders(policyHolder);
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public void getListDependentByPolicyOwnerId(String id) {
//        try (PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM dependents WHERE poid = ?")) {
//            pstmt.setString(1, id);
//            ResultSet rs = pstmt.executeQuery();
//            while (rs.next()) {
//                String idPH, fullName, insuranceCard, email, address, phoneNum, policyHolderId, policyOwnerId;
//                idPH = rs.getString("did");
//                fullName = rs.getString("full_name");
//                insuranceCard = rs.getString("insurance_card");
//                email = rs.getString("email");
//                address = rs.getString("address");
//                phoneNum = rs.getString("phone_number");
//                policyHolderId = rs.getString("pid");
//                policyOwnerId = rs.getString("poid");
//                Dependent dependent = new Dependent(idPH, fullName, insuranceCard, phoneNum, email, address, policyHolderId, policyOwnerId);
//                PolicyOwnerModel.getInstance().addDependents(dependent);
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public void getListDependentByPolicyHolderId(String id, String role) {
//        try (PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM dependents WHERE pid = ?")) {
//            pstmt.setString(1, id);
//            ResultSet rs = pstmt.executeQuery();
//            while (rs.next()) {
//                String idPH, fullName, insuranceCard, email, address, phoneNum, policyHolderId, policyOwnerId;
//                idPH = rs.getString("did");
//                fullName = rs.getString("full_name");
//                insuranceCard = rs.getString("insurance_card");
//                email = rs.getString("email");
//                address = rs.getString("address");
//                phoneNum = rs.getString("phone_number");
//                policyHolderId = rs.getString("pid");
//                policyOwnerId = rs.getString("poid");
//                Dependent dependent = new Dependent(idPH, fullName, insuranceCard, phoneNum, email, address, policyHolderId, policyOwnerId);
//                if (role.equals("PO")) {
//                    PolicyOwnerModel.getInstance().addDependentsOfCurrentPolicyHolder(dependent);
//                } else {
//                    PolicyHolderModel.getInstance().addDependent(dependent);
//                }
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public void getListInsuranceSurveyorForAdmin() {
//        try (Statement pstmt = conn.createStatement()) {
//            ResultSet rs = pstmt.executeQuery("SELECT * FROM insurance_employees WHERE id LIKE 's-%'");
//            while (rs.next()) {
//                String id, phone, address, name, email;
//                id = rs.getString("id");
//                phone = rs.getString("phone");
//                address = rs.getString("address");
//                name = rs.getString("full_name");
//                email = rs.getString("email");
//                Employee insuranceSurveyor = new Employee(id, phone, address, name, email);
//                AdminModel.getInstance().addInsuranceSurveyors(insuranceSurveyor);
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public void getListInsuranceManagerForAdmin() {
//        try (Statement pstmt = conn.createStatement()) {
//            ResultSet rs = pstmt.executeQuery("SELECT * FROM insurance_employees WHERE id LIKE 'm-%'");
//            while (rs.next()) {
//                String id, phone, address, name, email;
//                id = rs.getString("id");
//                phone = rs.getString("phone");
//                address = rs.getString("address");
//                name = rs.getString("full_name");
//                email = rs.getString("email");
//                Employee insuranceManager = new Employee(id, phone, address, name, email);
//                AdminModel.getInstance().addInsuranceManagers(insuranceManager);
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public ResultSet getAccountData(String username, String password){
//        ResultSet resultSet = null;
//        try {
//            PreparedStatement pst = conn.prepareStatement("SELECT * FROM account WHERE username = ? AND password = ?");
//            pst.setString(1, username);
//            pst.setString(2, password);
//            resultSet = pst.executeQuery();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return resultSet;
//    }
//
//    public ResultSet getAccountDataById(String id){
//        ResultSet resultSet = null;
//        try {
//            PreparedStatement pst = conn.prepareStatement("SELECT * FROM account WHERE id = ?");
//            pst.setString(1, id);
//            resultSet = pst.executeQuery();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return resultSet;
//    }
//
//    public void getPolicyOwnerDataById(String id) {
//        ResultSet rs = null;
//        try (PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM policy_owners WHERE poid = ?")) {
//            pstmt.setString(1, id);
//            rs = pstmt.executeQuery();
//            if (rs.next()) {
//                PolicyOwnerModel.getInstance().evaluatePolicyOwnerCred(rs.getString("poid"), rs.getString("name"), rs.getString("fee"));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void getPolicyHolderDataById(String id) {
//        ResultSet rs = null;
//        try (PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM policy_holders WHERE pid = ?")) {
//            pstmt.setString(1, id);
//            rs = pstmt.executeQuery();
//            if (rs.next()) {
//                PolicyHolderModel.getInstance().evaluatePolicyHolder(rs.getString("pid"), rs.getString("full_name"), rs.getString("insurance_card"), rs.getString("phone_number"), rs.getString("email"), rs.getString("address"), rs.getString("poid"));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void getDependentDataById(String id) {
//        ResultSet rs = null;
//        try (PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM dependents WHERE did = ?")) {
//            pstmt.setString(1, id);
//            rs = pstmt.executeQuery();
//            if (rs.next()) {
//                DependentModel.getInstance().evaluateDependentCred(rs.getString("did"), rs.getString("full_name"), rs.getString("insurance_card"), rs.getString("phone_number"), rs.getString("email"), rs.getString("address"), rs.getString("pid"), rs.getString("poid"));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public int getTheLastOfPolicyHolders() {
//        int count = 0;
//        try {
//            Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);;
//            ResultSet rs = statement.executeQuery("SELECT id FROM policy_holders ORDER BY  id ASC");
//            if (rs.last()) {
//                count = rs.getInt(1);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return count;
//    }
//
//    public int getTheLastOfDependents() {
//        int count = 0;
//        try {
//            Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);;
//            ResultSet rs = statement.executeQuery("SELECT id FROM dependents ORDER BY  id ASC");
//            if (rs.last()) {
//                count = rs.getInt(1);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return count;
//    }
//
//
//    //Update data
//    public void updatePolicyOwnerById(String id) {
//        try (PreparedStatement pstmt = conn.prepareStatement("UPDATE policy_owners SET name = ?, fee = ? WHERE poid = ?")) {
//            pstmt.setString(1, PolicyOwnerModel.getInstance().getPolicyOwner().getName());
//            pstmt.setString(2, Double.toString(PolicyOwnerModel.getInstance().getPolicyOwner().getFee()));
//            pstmt.setString(3, id);
//            pstmt.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//    public void updatePolicyHolder(String id, String fullName,String phoneNumber, String email, String address) {
//        try (PreparedStatement pstmt = conn.prepareStatement("UPDATE policy_holders SET full_name = ?, phone_number = ?, email = ?, address = ? WHERE pid = ?")) {
//            pstmt.setString(1, fullName);
//            pstmt.setString(2, phoneNumber);
//            pstmt.setString(3, email);
//            pstmt.setString(4, address);
//            pstmt.setString(5, id);
//            pstmt.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//    public void updateDependent(String id, String fullName,String phoneNumber, String email, String address) {
//        try (PreparedStatement pstmt = conn.prepareStatement("UPDATE dependents SET full_name = ?, phone_number = ?, email = ?, address = ? WHERE did = ?")) {
//            pstmt.setString(1, fullName);
//            pstmt.setString(2, phoneNumber);
//            pstmt.setString(3, email);
//            pstmt.setString(4, address);
//            pstmt.setString(5, id);
//            pstmt.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//    public void updateAccountData(String username, String password, String id) {
//        try (PreparedStatement pstmt = conn.prepareStatement("UPDATE account SET username = ?, password = ? WHERE id = ?")) {
//            pstmt.setString(1, username);
//            pstmt.setString(2, password);
//            pstmt.setString(3, id);
//            pstmt.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//    public void updateEmployee(String id, String fullName, String phoneNumber, String email, String address) {
//        try (PreparedStatement pstmt = conn.prepareStatement("UPDATE insurance_employees SET full_name = ?, phone = ?, email = ?, address = ? WHERE id = ?")) {
//            pstmt.setString(1, fullName);
//            pstmt.setString(2, phoneNumber);
//            pstmt.setString(3, email);
//            pstmt.setString(4, address);
//            pstmt.setString(5, id);
//            pstmt.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//    public void updateClaim(Claim claim) {
//        try (PreparedStatement pstmt = conn.prepareStatement("UPDATE claims SET claim_date = ?, claim_amount = ?, exam_date = ?, receiver_banking_info = ? WHERE fid = ?")) {
//            pstmt.setDate(1, Date.valueOf(claim.getClaimDate()));
//            pstmt.setString(2, String.valueOf(claim.getClaimAmount()));
//            pstmt.setDate(3, Date.valueOf(claim.getExamDate()));
//            pstmt.setString(4, String.valueOf(claim.getReceiverBankingInfo()));
//            pstmt.setString(5, claim.getId());
//            pstmt.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void getEmployeeDataById(String id) {
//        ResultSet rs = null;
//        try (PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM account WHERE id = ?")) {
//            pstmt.setString(1, id);
//            rs = pstmt.executeQuery();
//            if (rs.next()) {
//                EmployeeModel.getInstance().setEmployeeModel(rs.getString("id"), rs.getString("username"), rs.getString("role"));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//}
