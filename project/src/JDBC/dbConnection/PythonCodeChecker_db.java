package JDBC.dbConnection;

import java.sql.Connection;
import java.sql.DriverManager;

public class PythonCodeChecker_db {

    public Connection get_connection() {
        Connection connection = null;

        String host = "mysqlpythoncodechecker.cxz65o79hwmr.ap-southeast-2.rds.amazonaws.com";
        String port = "3306";
        String db_name = "PythonCodeChecker";
        String username = "admin";
        String password = "5281688.";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://" + "" + host + ":" + port + "/" + db_name + "",
                    "" + username + "", "" + password + "");
            if (connection != null) {
                System.out.println("Connection OK");
            } else {
                System.out.println("Connection Failed");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}
