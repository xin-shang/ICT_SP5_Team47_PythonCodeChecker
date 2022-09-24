package JDBC.QNS.SingleTable;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import methodAndTool.*;

public class question_T extends STable_P {
    ProjectVariable PV = new ProjectVariable();
    String table = "question";
    PreparedStatement PreStmt;
    ResultSet res;

    public question_T() {

    }

    public boolean inserRows(String user_id, String question) {

        try {
            String id = PV.getID(question, getRowsLength(table));

            String sql = "INSERT INTO " + table + " VALUES(?,?,?)";
            conn = pb.get_connection();
            PreStmt = conn.prepareStatement(sql);
            // insert value
            PreStmt.setString(1, id);
            PreStmt.setString(2, user_id);
            PreStmt.setString(3, question);
            PreStmt.executeUpdate();
            PreStmt.close();
            conn.close();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean inserRows(String id, String user_id, String question) {
        try {

            String sql = "INSERT INTO " + table + " VALUES(?,?,?)";
            conn = pb.get_connection();
            PreStmt = conn.prepareStatement(sql);
            // insert value
            PreStmt.setString(1, id);
            PreStmt.setString(2, user_id);
            PreStmt.setString(3, question);
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

            String sql = "DELETE FROM " + table + " where id = ?";
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

    public String getQuestionID(String question) {
        try {
            String question_id = null;

            String sql = "SELECT id FROM " + table + " WHERE question = ?";
            conn = pb.get_connection();
            conn.prepareStatement(sql);
            PreStmt.setString(1, question);
            res = PreStmt.executeQuery();
            question_id = res.getString(1);
            PreStmt.close();
            // conn.close();
            return question_id;

        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }

    public boolean bCheckQuestion(String question) {
        try {
            boolean bQuestion;

            String sql = "SELECT question FROM " + table + " WHERE question = ?";
            conn = pb.get_connection();
            conn.prepareStatement(sql);
            PreStmt.setString(1, question);
            res = PreStmt.executeQuery();
            bQuestion = res.next();
            // conn.close();
            return bQuestion;

        } catch (SQLException e) {
            return false;
        }

    }

}
