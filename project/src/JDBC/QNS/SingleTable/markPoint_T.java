package JDBC.QNS.SingleTable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class markPoint_T extends STable_P {
    String table = "markPoint";
    PreparedStatement PreStmt;

    public boolean inserRows(Connection conn, String question_id, String keyword_id, int score) {
        try {

            String sql = "INSERT INTO " + table + " VALUES(?,?,?)";

            PreStmt = conn.prepareStatement(sql);
            // insert value
            PreStmt.setString(1, question_id);
            PreStmt.setString(2, keyword_id);
            PreStmt.setInt(3, score);
            PreStmt.executeUpdate();
            PreStmt.close();

            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean deletRows(Connection conn, String question_id) {
        try {

            String sql = "DELETE from " + table + " where question_id = ?";

            PreStmt = conn.prepareStatement(sql);
            PreStmt.setString(1, question_id);
            PreStmt.executeUpdate();
            PreStmt.close();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
