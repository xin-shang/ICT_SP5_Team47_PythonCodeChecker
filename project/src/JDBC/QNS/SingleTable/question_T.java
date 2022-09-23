package JDBC.QNS.SingleTable;

import java.sql.SQLException;
import methodAndTool.*;

public class question_T extends STable_P {
    ProjectVariable PV = new ProjectVariable();
    String table = "question";

    public question_T() {

    }

    public boolean inserRows(String user_id, String question) {

        try {
            String id = PV.getID(question, getRowsLength(table));
            connectDB();
            String sql = "INSERT INTO " + table + " VALUES(?,?,?)";
            PreStmt = conn.prepareStatement(sql);
            // insert value
            PreStmt.setString(1, id);
            PreStmt.setString(2, user_id);
            PreStmt.setString(3, question);
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
            String sql = "DELETE FROM " + table + " where id = ?";
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

    public String getQuestionID(String question) {
        try {
            String question_id = null;
            connectDB();
            String sql = "SELECT id FROM " + table + " WHERE question = ?";
            PreStmt = conn.prepareStatement(sql);
            PreStmt.setString(1, question);
            res = PreStmt.executeQuery();
            question_id = res.getString(1);
            PreStmt.close();
            disConnectDB();
            return question_id;

        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }

    public boolean bCheckQuestion(String question) {
        try {
            boolean bQuestion;
            connectDB();
            String sql = "SELECT question FROM " + table + " WHERE question = ?";
            PreStmt = conn.prepareStatement(sql);
            PreStmt.setString(1, question);
            res = PreStmt.executeQuery();
            bQuestion = res.next();
            disConnectDB();
            return bQuestion;

        } catch (SQLException e) {
            return false;
        }

    }

}
