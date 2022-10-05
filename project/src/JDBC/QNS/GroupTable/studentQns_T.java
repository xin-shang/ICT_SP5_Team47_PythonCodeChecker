package JDBC.QNS.GroupTable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;

import Type.QnS;

public class studentQns_T extends Qns_T {

    List<QnS> qnsDB;
    public static int dblength;
    public static int rowlength = 4;
    PreparedStatement PreStmt;

    public studentQns_T(Connection conn) {
        qnsDB = getStudentQns(conn);
        System.out.println("____________");
    }

    public List<QnS> getQNS() {
        return this.qnsDB;
    }

    private List<QnS> getStudentQns(Connection conn) {
        List<QnS> qnsDB = new ArrayList<QnS>();
        try {
            // System.out.println("Opened database successfully!");
            String sql = "SELECT question.id, " +
                    "question.question, " +
                    "solution.solution, " +
                    "solution.answer " +
                    "FROM question INNER JOIN solution ON question.id = solution.question_id";

            // System.out.println("student_qns");
            PreStmt = conn.prepareStatement(sql);
            ResultSet res = PreStmt.executeQuery();
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
            PreStmt.close();

            return qnsDB;

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ":" + e.getMessage());
            System.exit(0);
            return null;
        }
    }

    public Object getData_id(int y) {
        if (y > dblength) {
            System.out.println("column is out of index");
            return null;
        } else {
            return qnsDB.get(y).getQuestionID();
        }
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
            // return qnsDB.get(y).getQuestionID();
            return y + 1;
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

    public static int getDblength() {
        return dblength;
    }

    public int getRowlength() {
        return rowlength;
    }

}
