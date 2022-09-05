package methodAndTool;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import component.AddQuestionComponent;

public class dataIO {
    QnS[] qnsDB;
    public int dblength;
    public int rowlength;
    WriteAndRead WAR;

    /**
     * Get database. Post is sent to the front end.     Get 数据库。 Post 传送到 前端。
    */
    /*------------------------------------------------------------------------------------------*/
    /*------------------------------------------------------------------------------------------*/
    public dataIO() {
        WAR = new WriteAndRead();
        dblength = getDBlength();
        rowlength = getRowsLength();

        storeDb();
    }

    private void storeDb() {
        qnsDB = new QnS[dblength];

        // 储存二维数组
        for (int i = 0; i < dblength; i++) {
            // 使 id 和 i 对齐，把需要的ID 写进txt 里面 给python 提取
            String id = String.valueOf(i + 1);
            WAR.write2TextFileOutStream("./src/dbData/dbID.txt", id);
            // 拿到id 后运行python, python会根据id 刷新txt里面的值，txt文件储存在dbData 的文件夹里面
            WAR.run_python_code("./src/python/PYDb_returnRow.py");

            // 运行完python，txt刷新之后暂时储存数据
            String question = WAR.readText("./src/dbData/dbQuestion.txt");
            String solution = WAR.readText("./src/dbData/dbSolution.txt");
            String answer = WAR.readText("./src/dbData/dbAnswer.txt");
            String markScheme = WAR.readText("./src/dbData/dbScheme.txt");
            List<markScheme> ms = getMarkPointList(markScheme);

            // System.out.println(ms.get(0).getMarkPoint());
            QnS qns = new QnS(id, question, solution, answer, ms);
            // System.out.println(qns.getMarkList().get(0).getMarkPoint());
            qnsDB[i] = qns;
        }
    }

    private List<String> transferText2List(String StringToList) {
        List<String> elephantList = Arrays.asList(StringToList.split(","));
        return elephantList;
    }

    private String remove_space(String str) {
        if (str.charAt(0) == ' ') {
            return str.substring(1);
        }
        return str;
    }

    private int StringToInt(String string_int) {
        try {
            String str = string_int;
            int number = Integer.parseInt(str);
            return number;
        } catch (NumberFormatException ex) {

            return 0;
        }
    }

    public List<markScheme> getMarkPointList(String string_a) {
        List<String> values = transferText2List(string_a);
        List<markScheme> markSchemes = new ArrayList<markScheme>();
        String markPoint = null;
        int mark = 0;
        int count = 0;
        for (String value : values) {
            remove_space(value);
            // even(mark point)偶数时候是分数 开始为偶数，第一个数是偶数
            if (count % 2 == 0) {
                String newValue = remove_space(value);
                markPoint = newValue;
            }
            // odd(mark for the each point)奇数时候是评分点
            else {
                String newValue = remove_space(value);
                mark = StringToInt(newValue);
                markScheme ms = new markScheme(markPoint, mark);
                markSchemes.add(ms);

            }
            count++;
        }
        return markSchemes;
    }

    public QnS[] getDB() {
        return this.qnsDB;
    }

    public Object getData(int x, int y) {
        if (x > dblength) {
            System.out.println("column is out of index");
            return null;
        }
        if (y > rowlength) {
            System.out.println("row is out of index");
            return null;
        }
        if (y == 0) {
            return qnsDB[x].getId();
        } else if (y == 1) {
            return qnsDB[x].getQuestion();
        } else if (y == 2) {
            return qnsDB[x].getSolution();
        } else if (y == 3) {
            return qnsDB[x].getAnswer();
        } else if (y == 4) {
            return qnsDB[x].getMarkList();
        } else {
            return null;
        }
    }

    private int getRowsLength() {
        String rowsLength = WAR.getPythonOutPut("./src/python/PYDb_getRowsLength.py");
        try {
            int number = Integer.parseInt(rowsLength);
            return number;
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
            System.out.println("db load fail");
            return 0;
        }
    }

    // 获取db 的长度
    private int getDBlength() {
        String length = WAR.getPythonOutPut("./src/python/PYDb_getLength.py");
        try {
            int number = Integer.parseInt(length);
            return number;
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
            System.out.println("db load fail");
            return 0;
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
     * Get Front-end data. Post to the database.    Get 前端数据。 Post 传送到数据库。
    */
    /*------------------------------------------------------------------------------------------*/
    /*------------------------------------------------------------------------------------------*/
    // Pass the New Question to the database 将New Question传送到数据库
    public void PostNewQuestionString() {
		char[] pyCharsNewQuestionString = AddQuestionComponent.getNewQuestionString().toCharArray();

        try {
            WAR.creatTxtFileDBData("dbQuestion");
            System.out.println("_______________");
        } catch (IOException w) {
            w.printStackTrace();
        }
        WAR.writeAnswerInTxt(pyCharsNewQuestionString, AddQuestionComponent.getNewQuestionString());
    }

    // Transfer the New Solution to the database 将New Solution传送到数据库
    public void PostNewSolutionString() {
		char[] pyNewSolutionString = AddQuestionComponent.getNewSolutionString().toCharArray();

        try {
            WAR.creatTxtFileDBData("dbSolution");
            System.out.println("_______________");
        } catch (IOException w) {
            w.printStackTrace();
        }
        WAR.writeAnswerInTxt(pyNewSolutionString, AddQuestionComponent.getNewSolutionString());
    }

    // Transfer the New Score Point to the database 将New Score Point传送到数据库
    public void PostNewScorePointString() {
		char[] pyNewScorePointString = AddQuestionComponent.getScorePointString().toCharArray();

        try {
            WAR.creatTxtFileDBData("dbScorePoint");
            System.out.println("_______________");
        } catch (IOException w) {
            w.printStackTrace();
        }
        WAR.writeAnswerInTxt(pyNewScorePointString, AddQuestionComponent.getScorePointString());
    }

    /*------------------------------------------------------------------------------------------*/
    /*------------------------------------------------------------------------------------------*/

}
