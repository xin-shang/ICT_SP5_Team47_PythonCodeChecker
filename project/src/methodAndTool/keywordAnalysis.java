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
        int score = 0;
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
