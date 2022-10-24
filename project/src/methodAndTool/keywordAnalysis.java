package methodAndTool;

import java.util.ArrayList;
import java.util.List;

import Type.markScheme;
import component.StudentWorkingComponent;

public class keywordAnalysis {

    WriteAndRead WAR = new WriteAndRead();
    MessagePrintString MPS = new MessagePrintString();

    // total text modified by students - solution; output - anwer; correct answer;
    // answer score;
    public int getKeyWordSocre(String solution, String answer, String correctAnswer, int answerScore,
            List<markScheme> mkl) {

        int score = 0;
        score += getAnswerScore(answer, correctAnswer, answerScore);
        MPS.CalculatingMarkToString(StudentWorkingComponent.terminalArea);

        // mkl loop each markscheme(mk)
        for (markScheme mk : mkl) {
            String keyword = mk.getKeyword();
            boolean bcheck = solution.contains(keyword);
            if (bcheck == true) {

                score += mk.getScore();

                // delete keyword after checked
                String deleteKw = solution.replaceFirst(mk.getKeyword(), "");

                solution = deleteKw;
            } else {
                score += 0;
            }
        }
        //
        MPS.CalculateMarkDoneToString(StudentWorkingComponent.terminalArea);
        return score;

    }

    private String removeNewLine(String string) {
        String finalString;
        // 从textarea拿来的string默认结尾有一个'\n', 需要删掉
        if (string.charAt(string.length() - 1) == '\n') {
            finalString = string.substring(0, string.length() - 1);
        } else {
            finalString = string;
        }
        return finalString;
    }

    public int getAnswerScore(String answer, String correctAnswer, int answerScore) {

        // 两个都用removeNewLine处理过后进行对比
        String f_answer = removeNewLine(answer);
        String f_correctAnswer = removeNewLine(correctAnswer);

        int score = 0;
        if (f_answer.equals(f_correctAnswer)) {
            score += answerScore;
            // System.out.println("your answer is correct");
            return score;
        }

        else if (f_correctAnswer.contains(",")) {

            String[] correctAnswerStrList = f_correctAnswer.replace(" ", "").split(",");
            int correctAnswer_l = correctAnswerStrList.length;
            String[] AnswerStrList = f_answer.split(",");

            for (String s : AnswerStrList) {
                if (s.contains(" ")) {
                    s.replace(" ", "");
                }
            }

            // output的占分/正确答案的length，得出每一个output的word的分数
            int eachWordScore = answerScore / correctAnswer_l;

            // 修改部分：
            for (int i = 0; i < AnswerStrList.length; i++) {
                for (int a = 0; a < correctAnswerStrList.length; a++) {
                    if (AnswerStrList[i].equals(correctAnswerStrList[a]) && a == i) {
                        System.out.println("correct:" + AnswerStrList[i]);
                        score += eachWordScore;
                        correctAnswerStrList[a] = "";

                    } else if (AnswerStrList[i].equals(correctAnswerStrList[a]) && a != i) {
                        System.out.println("correct but not exactly:" + AnswerStrList[i]);
                        score += eachWordScore / 2;
                        correctAnswerStrList[a] = "";

                    }
                }

            }

            return score;

        }
        // else if (f_correctAnswer.contains(" ")) {
        // System.out.println("your answer has blank");
        // String[] correctAnswerStrList = f_correctAnswer.split(" ");
        // String[] AnswerStrList = f_answer.split(" ");

        // int correctAnswer_l = correctAnswerStrList.length;

        // // output的占分/正确答案的length，得出每一个output的word的分数
        // int eachWordScore = answerScore / correctAnswer_l;

        // int count = 0;

        // // 用的容器，用i来按顺序循环AnswerStrList里面的量
        // for (String i : AnswerStrList) {

        // for (String j : correctAnswerStrList) {
        // if (i.equals(j)) {
        // count++;
        // }
        // }
        // if (count > 0) {
        // score += eachWordScore;
        // }
        // count = 0;
        // }

        // return score;

        // }
        else {
            if (f_answer.replace(" ", "").equals(f_correctAnswer.replace(" ", ""))) {
                score += answerScore / 2;
                System.out.println("your answer is right but please check the format of it");
            } else {
                score += 0;
            }
            return score;

        }

    }

    public ArrayList<String> getPassedKeywordlist(String solution, List<markScheme> mkl) {

        ArrayList<String> PassedKeywordList = new ArrayList<>();

        // mkl loop each markscheme(mk)
        for (markScheme mk : mkl) {
            String keyword = mk.getKeyword();
            boolean bcheck = solution.contains(keyword);
            if (bcheck == true) {

                PassedKeywordList.add(mk.getKeyword());

                // delete keyword after checked
                String deleteKw = solution.replaceFirst(mk.getKeyword(), "");

                solution = deleteKw;
            }
        }
        return PassedKeywordList;

    }

}
