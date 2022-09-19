package JDBC.Login;

import java.sql.ResultSet;
import java.sql.SQLException;

import JDBC.dbConnection.PythonCodeChecker_db;

public class staff_T extends PythonCodeChecker_db {

    static String Username_exit;

    String table = "staff";

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
            connectDB();
            String sql = "SELECT user_id, password FROM " + table + " WHERE user_id = ?";
            PreStmt = conn.prepareStatement(sql);
            PreStmt.setString(1, userID_u);
            PreStmt.executeQuery();
            ResultSet res = PreStmt.executeQuery();
            while (res.next()) {
                userID = res.getString(1);
                password = res.getString(2);
            }
            PreStmt.close();
            disConnectDB();

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

    public boolean inserRows(String username, String password) {
        try {
            connectDB();
            String sql = "INSERT INTO " + table + " VALUES(?,?)";
            PreStmt = conn.prepareStatement(sql);
            // insert value
            PreStmt.setString(1, username);
            PreStmt.setString(2, password);

            PreStmt.executeUpdate();
            PreStmt.close();
            disConnectDB();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

}
