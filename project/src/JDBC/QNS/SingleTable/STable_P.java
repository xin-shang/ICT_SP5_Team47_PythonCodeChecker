package JDBC.QNS.SingleTable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import JDBC.dbConnection.PythonCodeChecker_db;
import methodAndTool.ProjectVariable;

public class STable_P {
    ProjectVariable PV = new ProjectVariable();
    PreparedStatement PreStmt;
    ResultSet res;

    Connection conn;
    PythonCodeChecker_db pb = new PythonCodeChecker_db();

    public int getRowsLength(String table) {
        try {

            String sql = "select count(*) from " + table;
            conn = pb.get_connection();
            PreStmt = conn.prepareStatement(sql);

            res = PreStmt.executeQuery();
            res.next();
            int rowsLength = res.getInt("count(*)");
            // conn.close();
            return rowsLength;

        } catch (Exception e) {
            System.out.println(e);
            return 0;

        }
    }

}
