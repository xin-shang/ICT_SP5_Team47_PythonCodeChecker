package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class PythonCodeChecker_db {

    protected Connection conn = null;
    Statement stmt = null;
    String URL = "jdbc:sqlite:./src/sqlite/PYCodeChecker.db";
    protected PreparedStatement PreStmt = null;

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
