package JDBC.Staff;

import methodAndTool.QnS;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import JDBC.Qns_T;
import JDBC.Login.STAFF.staffLogin_T;
import component.AddQuestionComponent;

public class staffQns_T extends Qns_T {

    List<QnS> qnsDB;
    String userid;

    public static int dblength;
    public static int rowlength = 4;

    public staffQns_T() {
        // load the username first, then get staff qns
        userid = staffLogin_T.getUsername_exit();
        qnsDB = getStaffQns(userid);
    }

    public List<QnS> getdb() {
        return this.qnsDB;
    }

    private List<QnS> getStaffQns(String staffID) {
        List<QnS> qnsDB = new ArrayList<QnS>();
        try {
            connectDB();
            // System.out.println("Opened database successfully!");
            String sql = "SELECT question.id, " +
                    "question.question, " +
                    "solution.solution, " +
                    "solution.answer " +
                    "FROM question INNER JOIN solution ON question.id = solution.question_id WHERE question.user_id = ?";
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
            disConnectDB();
            return qnsDB;

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ":" + e.getMessage());
            System.exit(0);
            return null;
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

    public int getDblength() {
        return dblength;
    }

    public int getRowlength() {
        return rowlength;
    }

    /*------------------------------------------------------------------------------------------*/
    /*------------------------------------------------------------------------------------------*/

    /**
     * Get Front-end data. Post to the database. Get 前端数据。 Post 传送到数据库。
     */
    /*------------------------------------------------------------------------------------------*/
    /*------------------------------------------------------------------------------------------*/
    // Pass the New Question to the database 将New Question传送到数据库
    public static void PostNewQuestionString() {
        char[] pyCharsNewQuestionString = AddQuestionComponent.getNewQuestionString().toCharArray();
        try {
            WAR.creatTxtFileDBData("dbQuestion_POST");
            System.out.println("_______________");
        } catch (IOException w) {
            w.printStackTrace();
        }
        WAR.writeAnswerInTxt(pyCharsNewQuestionString, AddQuestionComponent.getNewQuestionString());
    }

    // Transfer the New Solution to the database 将New Solution传送到数据库
    public static void PostNewSolutionString() {
        char[] pyNewSolutionString = AddQuestionComponent.getNewSolutionString().toCharArray();
        try {
            WAR.creatTxtFileDBData("dbSolution_POST");
            System.out.println("_______________");
        } catch (IOException w) {
            w.printStackTrace();
        }
        WAR.writeAnswerInTxt(pyNewSolutionString, AddQuestionComponent.getNewSolutionString());
    }

    // Transfer the New Score Point to the database 将New Score Point传送到数据库
    public static void PostNewScorePointString() {
        char[] pyNewScorePointString = AddQuestionComponent.getScorePointString().toCharArray();
        try {
            WAR.creatTxtFileDBData("dbScorePoint_POST");
            System.out.println("_______________");
        } catch (IOException w) {
            w.printStackTrace();
        }
        WAR.writeAnswerInTxt(pyNewScorePointString, AddQuestionComponent.getScorePointString());
    }

    public static void PostNewAnswerString() {
        char[] pyNewAnswerString = AddQuestionComponent.getNewAnswerString().toCharArray();
        try {
            WAR.creatTxtFileDBData("dbAnswer_POST");
            System.out.println("_______________");
        } catch (IOException w) {
            w.printStackTrace();
        }
        WAR.writeAnswerInTxt(pyNewAnswerString, AddQuestionComponent.getNewAnswerString());
    }

    /*------------------------------------------------------------------------------------------*/
    /*------------------------------------------------------------------------------------------*/

}
