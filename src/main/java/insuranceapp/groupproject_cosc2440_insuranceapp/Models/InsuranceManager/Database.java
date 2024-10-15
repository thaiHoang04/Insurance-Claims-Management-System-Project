/**
 * @author Group 14
 */
package insuranceapp.groupproject_cosc2440_insuranceapp.Models.InsuranceManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    public static Connection connection = null;

    public static void setConnectionDatabase() {
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://aws-0-ap-southeast-1.pooler.supabase.com:5432/postgres?user=postgres.wyemaioitieansuvjkoi&password=Team14GroupProjectInsuranceApp");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
