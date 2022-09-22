package JDBC.QNS.SingleTable;

import JDBC.dbConnection.PythonCodeChecker_db;
import methodAndTool.ProjectVariable;

public class STable_P extends PythonCodeChecker_db {
    ProjectVariable PV = new ProjectVariable();

    public int getRowsLength(String table) {
        try {
            connectDB();
            String sql = "select count(*) from " + table;
            PreStmt = conn.prepareStatement(sql);

            res = PreStmt.executeQuery();
            res.next();
            int rowsLength = res.getInt("count(*)");
            disConnectDB();
            return rowsLength;

        } catch (Exception e) {
            System.out.println(e);
            return 0;

        }
    }

}
