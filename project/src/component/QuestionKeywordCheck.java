package component;

import java.util.LinkedHashMap;

import java.util.Map;

import methodAndTool.WriteAndRead;

public class QuestionKeywordCheck {

    WriteAndRead WAR = new WriteAndRead();
    

    /**
     * 固定的预选关键词。
    */
    
    Map<Integer,String > keyWordsList;

    static int dblength;
    // 方法三：还是用Map，配套方法需要重新开发。
    public QuestionKeywordCheck() {

        dblength = getYlength();
        keyWordsList = ReadKeywordsList();
        
    }

    
    private Map<Integer, String> ReadKeywordsList() {
        Map<Integer,String > keyWords = new LinkedHashMap<>();
        if (dblength > 0) {
            // 储存二维数组
            for (int i = 0; i < dblength; i++) {
                // 使 id 和 i 对齐，把需要的ID 写进txt 里面 给python 提取
                String rowid = String.valueOf(i+1);
                WAR.write2TextFileOutStream("./src/dbData/keywordAlternative/rowid.txt", rowid);
                // 拿到id 后运行python, python会根据id 刷新txt里面的值，txt文件储存在dbData 的文件夹里面
                WAR.run_python_code("./src/pythonDB/PYDb_getkeywordAlternative.py");
                // 运行完python，txt刷新之后暂时储存数据
                String keyword = WAR.readText("./src/dbData/keywordAlternative/keywords.txt");
                keyWords.put(i+1, keyword);
            }
            return keyWords;

        } else {
            System.out.println("the db is empty!!!!---------------");
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
            return keyWordsList.get(x+1);
        } else if (y == 2) {
            return 0;
        }  else {
            return null;
        }
    }


    public int getYlength() {
        String length = WAR.getPythonOutPut("./src/pythonDB/PYDb_keywordAlternativeYLength.py");
        try {
            int number = Integer.parseInt(length);
            return number;
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
            System.out.println("db load fail");
            return 0;
        }
    }

    public static int getDblength() {
        return dblength;
    }



    public Map<Integer,String > GetKeywordsList() {
        return keyWordsList;
    }

}
