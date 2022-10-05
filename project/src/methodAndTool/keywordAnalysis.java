package methodAndTool;

import java.util.ArrayList;
import java.util.List;

import Type.markScheme;
import component.StudentWorkingComponent;

public class keywordAnalysis {

    WriteAndRead WAR = new WriteAndRead();
    MessagePrintString MPS = new MessagePrintString();

    public int getKeyWordSocre(String solution, String answer, String correctAnswer,  List<markScheme> mkl) {

        int score = 0;
        //
        MPS.CalculatingMarkToString(StudentWorkingComponent.terminalArea);
        



    // 返还一个boolean检测是否有syntaxerror;
    // false = no syntaxerror
        if (answer.equals(correctAnswer)) {
            score += 40;
        } else {
            score += 0;
        }
        // mkl loop each markscheme(mk)
        for (markScheme mk : mkl) {
            String keyword = mk.getKeyword();
            boolean bcheck = solution.contains(keyword);
            if (bcheck == true) {

                score += mk.getScore() * 0.6;

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

    public ArrayList<String> getPassedKeywordlist(String solution,  List<markScheme> mkl){
        
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
