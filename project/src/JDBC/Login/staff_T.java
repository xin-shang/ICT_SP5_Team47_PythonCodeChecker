package JDBC.Login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;

import JDBC.dbConnection.PythonCodeChecker_db;

public class staff_T {

    // static value to store who is logging the system
    static String Username_exit;
    String table = "staff";
    JFrame frame = new JFrame();
    PreparedStatement PreStmt;
    Connection conn;
    PythonCodeChecker_db pb = new PythonCodeChecker_db();

    public staff_T() {
        Username_exit = null;
    }

    public static String getUsername() {
        return Username_exit;
    }

    // return 1, when userid correct,
    // return 2, when both correct,
    // return 0, when both incorrect
    public int checkUserID(String userID_u, String Password_u) {
        try {
            String userID = "";
            String password = "";

            String sql = "SELECT user_id, password FROM " + table + " WHERE user_id = ?";
            conn = pb.get_connection();
            PreStmt = conn.prepareStatement(sql);
            PreStmt.setString(1, userID_u);
            PreStmt.executeQuery();
            ResultSet res = PreStmt.executeQuery();
            while (res.next()) {
                userID = res.getString(1);
                password = res.getString(2);
            }
            PreStmt.close();
            conn.close();

            if (userID.equals(userID_u) && !password.equals(Password_u)) {
                return 1;
            } else if (!userID.equals(userID_u) && !password.equals(Password_u)) {
                return 0;
            } else {
                Username_exit = userID_u;
                return 2;
            }
        } catch (Exception e) {
            System.out.println("username is not exit");
            System.err.println(e.getClass().getName() + ":" + e.getMessage());
            System.exit(0);
            return 0;
        }
    }

    // insert value for single table
    public boolean inserRows(String username, String password) {
        try {

            String sql = "INSERT INTO " + table + " VALUES(?,?)";
            conn = pb.get_connection();
            PreStmt = conn.prepareStatement(sql);
            // insert value
            PreStmt.setString(1, username);
            PreStmt.setString(2, password);

            PreStmt.executeUpdate();
            PreStmt.close();
            conn.close();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

}
