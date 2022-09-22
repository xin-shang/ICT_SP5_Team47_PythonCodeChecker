package JDBC.QNS.SingleTable;

import java.sql.SQLException;

import methodAndTool.ProjectVariable;

public class keyword_T extends STable_P {
    ProjectVariable PV = new ProjectVariable();
    String table = "keywords";

    public boolean inserRows(String keyword) {

        try {
            String id = PV.getID(keyword, getRowsLength(table));
            connectDB();
            String sql = "INSERT INTO " + table + " VALUES(?,?)";
            PreStmt = conn.prepareStatement(sql);
            // insert value
            PreStmt.setString(1, id);
            PreStmt.setString(2, keyword);

            PreStmt.executeUpdate();
            PreStmt.close();
            disConnectDB();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean deletRows(String id) {
        try {
            connectDB();
            String sql = "DELETE from " + table + " where id = ?";
            PreStmt = conn.prepareStatement(sql);
            PreStmt.setString(1, id);
            PreStmt.executeUpdate();
            PreStmt.close();
            disConnectDB();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean bCheckKeyword(String keyword) {
        try {
            boolean bKeyword;
            connectDB();
            String sql = "SELECT question FROM " + table + " WHERE keywords = ?";
            PreStmt = conn.prepareStatement(sql);
            PreStmt.setString(1, keyword);
            res = PreStmt.executeQuery();
            bKeyword = res.next();
            disConnectDB();
            return bKeyword;

        } catch (SQLException e) {
            return false;
        }
    }

    public String getKeywordID(String keyword) {
        try {
            String keyword_id = null;
            connectDB();
            String sql = "SELECT id FROM " + table + " WHERE keywords = ?";
            PreStmt = conn.prepareStatement(sql);
            PreStmt.setString(1, keyword);
            res = PreStmt.executeQuery();
            keyword_id = res.getString(1);
            PreStmt.close();
            disConnectDB();
            return keyword_id;

        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }

}
