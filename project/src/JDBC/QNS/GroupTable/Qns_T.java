package JDBC.QNS.GroupTable;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import JDBC.dbConnection.PythonCodeChecker_db;
import methodAndTool.WriteAndRead;
import methodAndTool.markScheme;
import JDBC.QNS.SingleTable.markPoint_T;
import JDBC.QNS.SingleTable.keyword_T;
import JDBC.QNS.SingleTable.question_T;
import JDBC.QNS.SingleTable.solution_T;

public class Qns_T extends PythonCodeChecker_db {
    protected static WriteAndRead WAR = new WriteAndRead();
    markPoint_T mk = new markPoint_T();
    question_T qt = new question_T();
    solution_T sl = new solution_T();
    keyword_T kw = new keyword_T();

    public List<markScheme> getSelectedMarkScheme(String questionID) {
        try {
            connectDB();
            List<markScheme> mks = new ArrayList<markScheme>();
            String sql = "SELECT keywords.id, " +
                    "keywords.keywords, " +
                    "markPoint.score " +
                    "FROM markPoint INNER JOIN question ON markPoint.question_id = question.id " +
                    "LEFT JOIN keywords ON markPoint.keyword_id = keywords.id " +
                    "WHERE question.id = ?";
            PreStmt = conn.prepareStatement(sql);
            PreStmt.setString(1, questionID);
            ResultSet res = PreStmt.executeQuery();

            while (res.next()) {
                String keyword_id = res.getString("id");
                // System.out.println(keyword_id);
                String keyword = res.getString("keywords");
                // System.out.println(keyword);
                int markPoint = WAR.StringToInt(res.getString("score"));
                // System.out.println(markPoint);
                markScheme mk = new markScheme(keyword_id, keyword, markPoint);
                mks.add(mk);
            }
            PreStmt.close();
            disConnectDB();
            return mks;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ":" + e.getMessage());
            System.out.print("check the question id, which is exit or not");
            return null;
        }
    }
}
