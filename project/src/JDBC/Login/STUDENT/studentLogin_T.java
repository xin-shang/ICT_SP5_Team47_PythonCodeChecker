package JDBC.Login.STUDENT;

import java.sql.ResultSet;

import JDBC.PythonCodeChecker_db;

public class studentLogin_T extends PythonCodeChecker_db {

    // return 1, when userid correct,
    // return 2, when both correct,
    // return 0, when both incorrect
    public int checkUserID(String userID_u, String Password_u) {
        try {
            String userID = "";
            String password = "";
            connectDB();
            String sql = "SELECT user_id, password FROM student WHERE user_id = ?";
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
                return 2;
            }
        } catch (Exception e) {
            System.out.println("username is not exit");
            System.err.println(e.getClass().getName() + ":" + e.getMessage());
            System.exit(0);
            return 0;
        }
    }

}
