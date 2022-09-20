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
}
