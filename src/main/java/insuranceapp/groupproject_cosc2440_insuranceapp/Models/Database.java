/**
 * @author Group 14
 */
package insuranceapp.groupproject_cosc2440_insuranceapp.Models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private Connection con;
    public Database() {
        // Establish Connection
        try {
            this.con = DriverManager.getConnection(
                    "jdbc:postgresql://aws-0-ap-southeast-1.pooler.supabase.com:5432/postgres?user=postgres.wyemaioitieansuvjkoi&password=Team14GroupProjectInsuranceApp");
            System.out.println("Connect to Supabase successfully!");
        } catch (SQLException e) {
            System.out.println("Connect to Supabase failed!: " + e.getMessage());
        }
    }

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }
}
