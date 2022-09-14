package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedHashMap;
import java.util.Map;

public class keywordAlternative {
    Connection conn = null;
    Statement stmt = null;
    String URL = "jdbc:sqlite:./src/sqlite/PYCodeChecker.db";
    static int dblength;
    Map<Integer, String> keyWordsList;

    public keywordAlternative() {
        keyWordsList = getKeywordsList();
    }

    private Map<Integer, String> getKeywordsList() {
        Map<Integer, String> keyWords = new LinkedHashMap<>();
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(URL);
            System.out.println("Opened database successfully!");
            String sql = "select rowid, * From keywordAlternative";
            stmt = (Statement) conn.createStatement();
            ResultSet res = stmt.executeQuery(sql);
            System.out.println("Results:");

            int num = 0;
            while (res.next()) {
                num++;
                keyWords.put(num, res.getString("keywords"));
            }
            dblength = num;

            stmt.close();
            conn.close();
            return keyWords;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ":" + e.getMessage());
            System.exit(0);
            return null;
        }

    }

    public Object getData(int x, int y) {
        if (x > dblength) {
            System.out.println("column is out of index");
            return null;
        }
        if (y > 3) {
            System.out.println("row is out of index");
            return null;
        }
        if (y == 0) {
            return x;
        } else if (y == 1) {
            return keyWordsList.get(x + 1);
        } else if (y == 2) {
            return 0;
        } else {
            return null;
        }
    }

    public int getDblength() {
        return dblength;
    }

}
