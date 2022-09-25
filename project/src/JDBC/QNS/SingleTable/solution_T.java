package JDBC.QNS.SingleTable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import methodAndTool.ProjectVariable;

public class solution_T extends STable_P {
    ProjectVariable PV = new ProjectVariable();
    String table = "solution";
    PreparedStatement PreStmt;

    public solution_T() {

    }

    public boolean inserRows(Connection conn, String questionID, String solution, String answer) {

        try {
            String id = PV.getID(solution, getRowsLength(table));

            System.out.println(questionID);

            String sql = "INSERT INTO " + table + " VALUES(?,?,?,?)";

            PreStmt = conn.prepareStatement(sql);
            // insert value
            PreStmt.setString(1, id);
            PreStmt.setString(2, questionID);
            PreStmt.setString(3, solution);
            PreStmt.setString(4, answer);
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
