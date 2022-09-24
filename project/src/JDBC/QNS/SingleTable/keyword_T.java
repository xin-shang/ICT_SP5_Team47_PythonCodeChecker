package JDBC.QNS.SingleTable;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import methodAndTool.ProjectVariable;

public class keyword_T extends STable_P {
    ProjectVariable PV = new ProjectVariable();
    String table = "keywords";
    PreparedStatement PreStmt;
    ResultSet res;

    public boolean inserRows(String keyword) {

        try {
            String id = PV.getID(keyword, getRowsLength(table));

            String sql = "INSERT INTO " + table + " VALUES(?,?)";
            conn = pb.get_connection();
            PreStmt = conn.prepareStatement(sql);
            // insert value
            PreStmt.setString(1, id);
            PreStmt.setString(2, keyword);

            PreStmt.executeUpdate();
            PreStmt.close();
            conn.close();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean deletRows(String id) {
        try {

            String sql = "DELETE from " + table + " where id = ?";
            conn = pb.get_connection();
            PreStmt = conn.prepareStatement(sql);
            PreStmt.setString(1, id);
            PreStmt.executeUpdate();
            PreStmt.close();
            conn.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean bCheckKeyword(String keyword) {
        try {
            boolean bKeyword;

            String sql = "SELECT question FROM " + table + " WHERE keywords = ?";
            conn = pb.get_connection();
            PreStmt = conn.prepareStatement(sql);
            PreStmt.setString(1, keyword);
            res = PreStmt.executeQuery();
            bKeyword = res.next();
            conn.close();
            return bKeyword;

        } catch (SQLException e) {
            return false;
        }
    }

    public String getKeywordID(String keyword) {
        try {
            String keyword_id = null;

            String sql = "SELECT id FROM " + table + " WHERE keywords = ?";
            conn = pb.get_connection();
            PreStmt = conn.prepareStatement(sql);
            PreStmt.setString(1, keyword);
            res = PreStmt.executeQuery();
            keyword_id = res.getString(1);
            PreStmt.close();
            conn.close();
            return keyword_id;

        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }

}
