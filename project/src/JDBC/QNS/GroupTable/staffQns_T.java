package JDBC.QNS.GroupTable;

import methodAndTool.QnS;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import JDBC.Login.staff_T;

public class staffQns_T extends Qns_T {

    List<QnS> qnsDB;
    String userid;

    public static int dblength;
    public static int rowlength = 4;
    PreparedStatement PreStmt;

    public staffQns_T() {
        // load the username first, then get staff qns
        userid = staff_T.getUsername();
        qnsDB = getStaffQns(userid);
        System.out.println(dblength);
    }

    public List<QnS> getQNS() {
        return this.qnsDB;
    }

    private List<QnS> getStaffQns(String staffID) {
        List<QnS> qnsDB = new ArrayList<QnS>();
        try {

            // System.out.println("Opened database successfully!");
            String sql = "SELECT question.id, " +
                    "question.question, " +
                    "solution.solution, " +
                    "solution.answer " +
                    "FROM question INNER JOIN solution ON question.id = solution.question_id WHERE question.user_id = ? ORDER BY question.question ASC";
            conn = pb.get_connection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, staffID);
            ResultSet res = statement.executeQuery();
            int num = 0;
            while (res.next()) {
                num++;
                String question_id = res.getString(1);
                String question = res.getString(2);
                String solution = res.getString(3);
                String answer = res.getString(4);
                QnS qns = new QnS(question_id, question, solution, answer);
                qnsDB.add(qns);
            }
            dblength = num;
            statement.close();
            conn.close();
            return qnsDB;

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ":" + e.getMessage());
            System.exit(0);
            return null;
        }
    }

    public boolean insertQuestion(String question, String solution, String answer) throws SQLException {
        conn = pb.get_connection();
        String user_id = staff_T.getUsername();
        if (user_id != null) {
            boolean b_qt = qt.inserRows(conn, user_id, question);

            if (b_qt == true) {
                System.out.println(question);
                String qs_id = qt.getQuestionID(conn, question);
                sl.inserRows(conn, qs_id, solution, answer);
                System.out.println("insert question successful");
                conn.close();
                return true;
            } else {
                conn.close();
                return false;
            }
        } else {
            conn.close();
            return false;
        }
    }

    public boolean insertQuestion_id(String question_id, String question, String solution, String answer)
            throws SQLException {
        conn = pb.get_connection();
        String user_id = staff_T.getUsername();
        if (user_id != null) {
            boolean b_qt = qt.inserRows(conn, question_id, user_id, question);
            System.out.println(b_qt);
            if (b_qt == true) {
                sl.inserRows(conn, question_id, solution, answer);
                System.out.println("insert question successful");
                conn.close();
                return true;

            } else {
                conn.close();
                return false;
            }
        } else {
            conn.close();
            return false;
        }
    }

    public boolean insertQuestionMarkSheme(String question, String keyword, int Point) throws SQLException {
        conn = pb.get_connection();
        String qs_id = qt.getQuestionID(conn, question);

        if (qs_id != null) {
            String keywordID = kw.getKeywordID(conn, keyword);

            if (keywordID != null) {
                mk.inserRows(conn, qs_id, keywordID, Point);
                conn.close();
                return true;
            } else {
                kw.inserRows(keyword);
                keywordID = kw.getKeywordID(conn, keyword);
                mk.inserRows(conn, qs_id, keywordID, Point);
                conn.close();
                return true;
            }
        } else {
            conn.close();
            return false;
        }
    }

    public boolean insertQuestionMarkSheme_id(String question_id, String keyword, int Point) throws SQLException {
        conn = pb.get_connection();
        String keywordID = kw.getKeywordID(conn, keyword);

        if (keywordID != null) {
            boolean b_add_mk = mk.inserRows(conn, question_id, keywordID, Point);
            if (b_add_mk == true) {
                conn.close();
                return true;
            } else {
                conn.close();
                return false;
            }

        } else {
            kw.inserRows(keyword);
            keywordID = kw.getKeywordID(conn, keyword);
            boolean b_add_mk = mk.inserRows(conn, question_id, keywordID, Point);
            if (b_add_mk == true) {
                conn.close();
                return true;
            } else {
                conn.close();
                return false;
            }
        }

    }

    public void deleteQuestion_id(String question_id) throws SQLException {
        conn = pb.get_connection();
        mk.deletRows(conn, question_id);
        sl.deletRows(conn, question_id);
        qt.deletRows(conn, question_id);
        System.out.println("delete successful");
        conn.close();
    }

    public void deleteQuestion(String question) throws SQLException {
        conn = pb.get_connection();
        boolean bQuestion = qt.bCheckQuestion(conn, question);
        if (bQuestion == true) {
            String qs_id = qt.getQuestionID(conn, question);
            mk.deletRows(conn, qs_id);
            sl.deletRows(conn, qs_id);
            qt.deletRows(conn, qs_id);
        }
        conn.close();
    }

    public int getDblength() {
        return dblength;
    }

    public int getRowlength() {
        return rowlength;
    }

    public Object getData(int y, int x) {
        if (y > dblength) {
            System.out.println("column is out of index");
            return null;
        }
        if (x > rowlength) {
            System.out.println("row is out of index");
            return null;
        }
        if (x == 0) {
            return qnsDB.get(y).getQuestionID();
            // return y;
        } else if (x == 1) {
            return qnsDB.get(y).getQuestion();
        } else if (x == 2) {
            return qnsDB.get(y).getSolution();
        } else if (x == 3) {
            return qnsDB.get(y).getAnswer();
        } else {
            return null;
        }
    }

}
