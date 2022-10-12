package JDBC.QNS.GroupTable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import JDBC.Login.staff_T;
import Type.QnS;

public class staffQns_T extends Qns_T {

    List<QnS> qnsDB;
    String userid;

    public static int dblength;
    public static int rowlength = 4;
    PreparedStatement PreStmt;

    public staffQns_T(Connection conn) {
        // load the username first, then get staff qns
        userid = staff_T.getUsername();
        System.out.println("staffQNS");
        qnsDB = getStaffQns(conn, userid);

    }

    public List<QnS> getQNS() {
        return this.qnsDB;
    }

    private List<QnS> getStaffQns(Connection conn, String staffID) {
        List<QnS> qnsDB = new ArrayList<QnS>();
        try {

            // System.out.println("Opened database successfully!");
            String sql = "SELECT question.id, " +
                    "question.question, " +
                    "solution.solution, " +
                    "solution.answer, " +
                    "answerMark.score " +
                    "FROM question INNER JOIN solution ON question.id = solution.question_id LEFT JOIN answerMark ON solution.question_id = answerMark.question_id WHERE question.user_id = ?";

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
                int answerSocre = res.getInt(5);
                QnS qns = new QnS(question_id, question, solution, answer, answerSocre);
                qnsDB.add(qns);
            }
            dblength = num;
            statement.close();
            return qnsDB;

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ":" + e.getMessage());
            System.exit(0);
            return null;
        }
    }

    public boolean insertQuestion(Connection conn, String question, String solution, String answer, int answerScore)
            throws SQLException {
        // conn = pb.get_connection();
        String user_id = staff_T.getUsername();
        if (user_id != null) {
            System.out.println("insert question....");
            boolean b_qt = qt.inserRows(conn, user_id, question);

            if (b_qt == true) {
                // System.out.println(question);
                String qs_id = qt.getQuestionID(conn, question);
                sl.inserRows(conn, qs_id, solution, answer);
                as.inserRows(conn, qs_id, answerScore);
                System.out.println("insert question successful");
                // conn.close();
                return true;
            } else {
                // conn.close();
                return false;
            }
        } else {
            // conn.close();
            return false;
        }
    }

    public boolean updateQuestion(Connection conn, String question_id, String question, String solution,
            String answer, int answerScore) {

        boolean bUqt = qt.updateQuestion(conn, question_id, question);
        boolean bUsl = sl.updateSolution(conn, question_id, solution, answer);
        boolean bUas = as.updateQuestion(conn, question_id, answerScore);

        if (bUqt == true && bUsl == true && bUas == true) {
            return true;
        } else {
            return false;
        }

    }

    public boolean insertQuestionMarkSheme(Connection conn, String question, String keyword, int Point)
            throws SQLException {

        String qs_id = qt.getQuestionID(conn, question);

        if (qs_id != null) {
            String keywordID = kw.getKeywordID(conn, keyword);

            if (keywordID != null) {
                mk.inserRows(conn, qs_id, keywordID, Point);
                // conn.close();
                return true;
            } else {
                kw.inserRows(conn, keyword);
                keywordID = kw.getKeywordID(conn, keyword);
                mk.inserRows(conn, qs_id, keywordID, Point);
                // conn.close();
                return true;
            }
        } else {
            return false;
        }
    }

    public boolean updateQuestionMarkSheme(Connection conn, String question_id, String keyword, int Point) {
        String keywordID = kw.getKeywordID(conn, keyword);
        if (keywordID != null) {
            boolean b_add_mk = mk.inserRows(conn, question_id, keywordID, Point);
            if (b_add_mk == true) {

                return true;
            } else {

                return false;
            }

        } else {
            kw.inserRows(conn, keyword);
            keywordID = kw.getKeywordID(conn, keyword);
            boolean b_add_mk = mk.inserRows(conn, question_id, keywordID, Point);
            if (b_add_mk == true) {

                return true;
            } else {
                return false;
            }
        }
    }

    public void deleteQuestion(String question) throws SQLException {
        conn = pb.get_connection();
        boolean bQuestion = qt.bCheckQuestion(conn, question);
        if (bQuestion == true) {
            String qs_id = qt.getQuestionID(conn, question);
            mk.deletRows(conn, qs_id);
            sl.deletRows(conn, qs_id);
            as.deletRows(conn, qs_id);

            // question has the root id connected the rest of tables, it must be deleted at
            // the end
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
        } else if (x == 4) {
            return qnsDB.get(y).getAnswerScore();
        } else {
            return null;
        }
    }

}
