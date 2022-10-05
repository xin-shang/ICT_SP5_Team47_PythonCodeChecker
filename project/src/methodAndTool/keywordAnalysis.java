package methodAndTool;

import java.util.List;

import Type.markScheme;
import component.StudentWorkingComponent;

public class keywordAnalysis {

    WriteAndRead WAR = new WriteAndRead();
    MessagePrintString MPS = new MessagePrintString();

    public int getKeyWordSocre(String solution, String answer, List<markScheme> mkl) {

        int score = 0;
        //
        MPS.CalculatingMarkToString(StudentWorkingComponent.terminalArea);

        // 返还一个boolean检测是否有syntaxerror;
        boolean c = WAR.student_checkSolutionSytaxError(solution);
        String Correct_answer = WAR.readText("./src/txt/PyCodeAnswer.txt");
        // false = no syntaxerror
        if (c == false) {
            if (answer.equals(Correct_answer)) {
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
        } else {
            //
            MPS.CalculateMarkDoneToString(StudentWorkingComponent.terminalArea);
            return score;
        }

    }

}
