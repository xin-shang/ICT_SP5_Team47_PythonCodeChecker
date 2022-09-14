package JDBC;

import methodAndTool.QnS;
import methodAndTool.WriteAndRead;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import component.AddQuestionComponent;

public class staffQns_T {

    Connection conn = null;
    Statement stmt = null;
    String URL = "jdbc:sqlite:./src/sqlite/PYCodeChecker.db";
    List<QnS> qnsDB;

    public static int dblength;

    public static int rowlength = 4;

    static WriteAndRead WAR = new WriteAndRead();

    public staffQns_T() {
        qnsDB = getStaffQns();
    }

    public List<QnS> getdb() {
        return this.qnsDB;
    }

    private List<QnS> getStaffQns() {
        List<QnS> qnsDB = new ArrayList<QnS>();
        String userid = WAR.readText("./src/dbData/LOGIN/STAFF/Login_StaffUserName.txt");
        System.out.println(userid);

        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(URL);
            // System.out.println("Opened database successfully!");
            String sql = "SELECT question.id, " +
                    "question.question, " +
                    "solution.solution, " +
                    "solution.answer " +
                    "FROM question INNER JOIN solution ON question.id = solution.question_id WHERE question.user_id = ?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, userid);

            ResultSet res = statement.executeQuery();

            // System.out.println("Results:");
            int num = 0;

            while (res.next()) {
                num++;
                String question_id = res.getString(1);
                // System.out.println(question_id);
                String question = res.getString(2);
                // System.out.println(question);
                String solution = res.getString(3);
                // System.out.println(solution);
                String answer = res.getString(4);
                // System.out.println(answer);
                // System.out.println();
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

    public static int getRowlength() {
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
