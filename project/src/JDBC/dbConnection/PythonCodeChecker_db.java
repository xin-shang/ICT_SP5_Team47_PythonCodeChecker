package JDBC.dbConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class PythonCodeChecker_db {

    protected Connection conn = null;
    protected Statement stmt = null;
    protected PreparedStatement PreStmt = null;
    protected ResultSet res = null;
    String URL = "jdbc:sqlite:./src/sqlite/PYCodeChecker.db";


    

    public void connectDB() {
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(URL);
        } catch (Exception e) {
            System.out.println("connect fail");
        }
    }

    public void disConnectDB() {
        try {
            conn.close();
        } catch (Exception e) {
            System.out.println(" disconnect fail");
        }
    }


    public static Connection get_connection() {
        Connection connection = null;
        String host="mysqlpythoncodechecker.cyi624ixm65n.us-east-2.rds.amazonaws.com";
        String port="3306";
        String db_name="PythonCodeChecker";
        String username="admin";
        String password="5281688.";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://"+""+host+":"+port+"/"+db_name+"", ""+username+"", ""+password+"");
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
