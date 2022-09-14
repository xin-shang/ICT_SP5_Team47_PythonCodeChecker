package methodAndTool;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import component.AddQuestionComponent;

public class StaffdataIO {
    QnS[] qnsDB;
    public static int dblength;
    public int rowlength;
    static WriteAndRead WAR;

    /**
     * Get database. Post is sent to the front end. Get 数据库。 Post 传送到 前端。
     */
    /*------------------------------------------------------------------------------------------*/
    /*------------------------------------------------------------------------------------------*/

    public StaffdataIO() {
        WAR = new WriteAndRead();
        dblength = getYlength();
        rowlength = getXLength();
        storeDb();
    }

    private void storeDb() {

        if (dblength > 0) {
            qnsDB = new QnS[dblength];
            // 储存二维数组
            for (int i = 0; i < dblength; i++) {
                // 使 id 和 i 对齐，把需要的ID 写进txt 里面 给python 提取
                String rowid = String.valueOf(i);
                WAR.write2TextFileOutStream("./src/dbData/RECEIVE/rowid.txt", rowid);
                // 拿到id 后运行python, python会根据id 刷新txt里面的值，txt文件储存在dbData 的文件夹里面
                WAR.run_python_code("./src/pythonDB/PYDb_getQuestion.py");

                // 运行完python，txt刷新之后暂时储存数据
                String question_id = WAR.readText("./src/dbData/RECEIVE/dbQuestion_ID.txt");
                String question = WAR.readText("./src/dbData/RECEIVE/dbQuestion.txt");
                String solution = WAR.readText("./src/dbData/RECEIVE/dbSolution.txt");
                String answer = WAR.readText("./src/dbData/RECEIVE/dbAnswer.txt");

                // System.out.println(ms.get(0).getMarkPoint());
                QnS qns = new QnS(question_id, question, solution, answer);
                // System.out.println(qns.getMarkList().get(0).getMarkPoint());
                qnsDB[i] = qns;
            }
        } else {
            System.out.println("the db is empty!!!!---------------");
        }

    }

    //
    public int getSelectedMarkSchemeY(String question_id) {
        WAR.write2TextFileOutStream("./src/dbData/POST/questionID_POST.txt", question_id);
        String rowsLength = WAR.getPythonOutPut("./src/pythonDB/PYDb_getSelectedMarkYLength.py");
        try {
            int number = Integer.parseInt(rowsLength);
            return number;
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
            System.out.println("db load fail");
            return 0;
        }
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


    
    public List<markScheme> getSelectedMarkScheme(int length) {
        List<markScheme> mks = new ArrayList<markScheme>();
        markScheme mk;
        if (length > 0) {
            for (int i = 0; i < length; i++) {
                // 使 id 和 i 对齐，把需要的ID 写进txt 里面 给python 提取
                String rowid = String.valueOf(i);
                WAR.write2TextFileOutStream("./src/dbData/POST/markPoint/SeletcedQuestionMarkScheme_rowid.txt", rowid);
                WAR.run_python_code("./src/pythonDB/PYDb_getSelectedMark.py");
                String keywordID = WAR.readText("./src/dbData/RECEIVE/markPoint/keyword_id.txt");
                String keyword = WAR.readText("./src/dbData/RECEIVE/markPoint/keyword.txt");
                String scoreString = WAR.readText("./src/dbData/RECEIVE/markPoint/score.txt");
                int score = StringToInt(scoreString);
                mk = new markScheme(keywordID, keyword, score);
                mks.add(mk);
            }
            return mks;
        } else {
            return null;
        }
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
            return qnsDB[x].getQuestionID();
        } else if (y == 1) {
            return qnsDB[x].getQuestion();
        } else if (y == 2) {
            return qnsDB[x].getSolution();
        } else if (y == 3) {
            return qnsDB[x].getAnswer();
        } else {
            return null;
        }
    }

    // 获取db X 的长度----------- get db X length
    private int getXLength() {
        String rowsLength = WAR.getPythonOutPut("./src/pythonDB/PYDb_getXLength.py");
        try {
            int number = Integer.parseInt(rowsLength);
            return number;
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
            System.out.println("db load fail");
            return 0;
        }
    }

    // 获取db Y 的长度----------- get db Y length
    private int getYlength() {
        String length = WAR.getPythonOutPut("./src/pythonDB/PYDb_getYLength.py");
        try {
            int number = Integer.parseInt(length);
            return number;
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
            System.out.println("db load fail");
            return 0;
        }
    }

    // 长度会在创造 dataio 时候获取一次，获取后存为定值
    // get db y size, the value was quoting from getYlength()
    public static int getDblength() {
        return dblength;
    }

    // get db x size(header size), the value was quoting from getXLength()
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
