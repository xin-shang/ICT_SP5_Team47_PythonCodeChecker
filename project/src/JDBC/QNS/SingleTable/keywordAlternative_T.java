package JDBC.QNS.SingleTable;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedHashMap;
import java.util.Map;

import JDBC.dbConnection.PythonCodeChecker_db;

public class keywordAlternative_T extends PythonCodeChecker_db {

    static int dblength;
    Map<Integer, String> keyWordsList;

    public keywordAlternative_T() {
        keyWordsList = getKeywordsList();
    }

    // get table list
    private Map<Integer, String> getKeywordsList() {
        Map<Integer, String> keyWords = new LinkedHashMap<>();
        try {
            connectDB();
            String sql = "select rowid, * From keywordAlternative";
            stmt = (Statement) conn.createStatement();
            ResultSet res = stmt.executeQuery(sql);

            int num = 0;
            while (res.next()) {
                num++;
                keyWords.put(num, res.getString("keywords"));
            }
            dblength = num;

            stmt.close();
            disConnectDB();
            return keyWords;
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
        if (x > 3) {
            System.out.println("row is out of index");
            return null;
        }
        if (x == 0) {
            return y;
        } else if (x == 1) {
            return keyWordsList.get(y + 1);
        } else if (x == 2) {
            return 0;
        } else {
            return null;
        }
    }

    public int getDblength() {
        return dblength;
    }

}
