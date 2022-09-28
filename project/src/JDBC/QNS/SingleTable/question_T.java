package JDBC.QNS.SingleTable;

import java.sql.Connection;
import java.sql.SQLException;

import methodAndTool.*;

public class question_T extends STable_P {
    ProjectVariable PV = new ProjectVariable();
    String table = "question";

    public question_T() {

    }

    public boolean inserRows(Connection conn, String user_id, String question) {

        try {
            String id = PV.getID(question, getRowsLength(conn, table));

            String sql = "INSERT INTO " + table + " VALUES(?,?,?)";

            PreStmt = conn.prepareStatement(sql);
            // insert value
            PreStmt.setString(1, id);
            PreStmt.setString(2, user_id);
            PreStmt.setString(3, question);
            PreStmt.executeUpdate();
            PreStmt.close();

            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean inserRows(Connection conn, String id, String user_id, String question) {
        try {

            String sql = "INSERT INTO " + table + " VALUES(?,?,?)";

            PreStmt = conn.prepareStatement(sql);
            // insert value
            PreStmt.setString(1, id);
            PreStmt.setString(2, user_id);
            PreStmt.setString(3, question);
            PreStmt.executeUpdate();

            PreStmt.close();

            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean deletRows(Connection conn, String id) {
        try {

            String sql = "DELETE FROM " + table + " where id = ?";

            PreStmt = conn.prepareStatement(sql);
            PreStmt.setString(1, id);
            PreStmt.executeUpdate();
            PreStmt.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String getQuestionID(Connection conn, String question) {
        try {
            String question_id = null;

            String sql = "SELECT id FROM " + table + " WHERE question = ?";

            PreStmt = conn.prepareStatement(sql);

            PreStmt.setString(1, question);
            res = PreStmt.executeQuery();
            while (res.next()) {
                question_id = res.getString(1);
            }

            PreStmt.close();

            return question_id;

        } catch (SQLException e) {
            System.out.println(e);

            return null;
        }
    }

    public boolean bCheckQuestion(Connection conn, String question) {
        try {
            boolean bQuestion = false;
            String sql = "SELECT question FROM " + table + " WHERE question = ?";

            PreStmt = conn.prepareStatement(sql);
            PreStmt.setString(1, question);
            res = PreStmt.executeQuery();
            bQuestion = res.next();
            PreStmt.close();
            return bQuestion;

        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }

    }

}
