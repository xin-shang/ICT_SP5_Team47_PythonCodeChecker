package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class student_T {
    Connection conn = null;
    Statement stmt = null;
    String URL = "jdbc:sqlite:./src/sqlite/PYCodeChecker.db";

    public student_T() {

        try {
            Class.forName("org.sqlite.JDBC");
            // Class.forName("org.postgresql.Driver");
            // DriverManager.register(new org.postgresql.Driver());
            conn = DriverManager.getConnection(URL);
            // System.out.println("Opened database successfully!");

            String sql = "select * From student";
            stmt = (Statement) conn.createStatement();

            ResultSet res = stmt.executeQuery(sql);
            System.out.println("Results:");
            while (res.next()) {
                System.out.println(res.getString("user_id") + "   " + res.getString("password") + "  ");
            }

            stmt.close();
            conn.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ":" + e.getMessage());
            System.exit(0);
        }
    }
}
