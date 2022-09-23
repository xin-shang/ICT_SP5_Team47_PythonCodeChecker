package JDBC.QNS.SingleTable;

import java.sql.SQLException;

public class markPoint_T extends STable_P {
    String table = "markPoint";

    public boolean inserRows(String question_id, String keyword_id, int score) {
        try {
            connectDB();
            String sql = "INSERT INTO " + table + " VALUES(?,?,?)";
            PreStmt = conn.prepareStatement(sql);
            // insert value
            PreStmt.setString(1, question_id);
            PreStmt.setString(2, keyword_id);
            PreStmt.setInt(3, score);
            PreStmt.executeUpdate();
            PreStmt.close();
            disConnectDB();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean deletRows(String question_id) {
        try {
            connectDB();
            String sql = "DELETE from " + table + " where question_id = ?";
            PreStmt = conn.prepareStatement(sql);
            PreStmt.setString(1, question_id);
            PreStmt.executeUpdate();
            PreStmt.close();
            disConnectDB();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
