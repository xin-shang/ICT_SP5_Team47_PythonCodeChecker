package methodAndTool;

import java.util.ArrayList;
import java.util.List;

import Type.markScheme;
import component.StudentWorkingComponent;

public class keywordAnalysis {

    WriteAndRead WAR = new WriteAndRead();
    MessagePrintString MPS = new MessagePrintString();

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

    public int getAnswerScore(String answer, String correctAnswer, int answerScore) {

        String answer_save = answer;
        int score = 0;
        if (correctAnswer.contains(",")) {

            String[] correctAnswerStrList = correctAnswer.split(",");
            for (String s : correctAnswerStrList) {
                if (s.contains(" ")) {
                    s.replace(" ", "");
                }
            }

            String[] AnswerStrList = answer.split(",");

            for (String s : AnswerStrList) {
                if (s.contains(" ")) {
                    s.replace(" ", "");
                }
            }

            int correctAnswer_l = correctAnswerStrList.length;

            // output的占分/正确答案的length，得出每一个output的word的分数
            int eachWordScore = answerScore / correctAnswer_l;

            // 用的容器，用i来按顺序循环AnswerStrList里面的量
            for (String i : AnswerStrList) {
                if (answer_save.contains(i)) {
                    System.out.println(i);
                    score += eachWordScore;
                    String deleteKw = answer_save.replaceFirst(i, "");
                    answer_save = deleteKw;

                }

            }
            System.out.println(answer_save);

            return score;

        } else if (correctAnswer.contains(" ")) {
            System.out.println("your answer has blank");
            String[] correctAnswerStrList = correctAnswer.split(" ");
            String[] AnswerStrList = answer.split(" ");

            int correctAnswer_l = correctAnswerStrList.length;

            // output的占分/正确答案的length，得出每一个output的word的分数
            int eachWordScore = answerScore / correctAnswer_l;

            int count = 0;

            // 用的容器，用i来按顺序循环AnswerStrList里面的量
            for (String i : AnswerStrList) {

                for (String j : correctAnswerStrList) {
                    if (i.equals(j)) {
                        count++;
                    }
                }
                if (count > 0) {
                    score += eachWordScore;
                }
                count = 0;
            }

            return score;

        } else {
            // 返还一个boolean检测是否有syntaxerror;
            // false = no syntaxerror
            if (answer.equals(correctAnswer)) {
                score += answerScore;
                System.out.println("your answer is correct");
            } else if (answer.replace(" ", "").equals(correctAnswer.replace(" ", ""))) {
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
