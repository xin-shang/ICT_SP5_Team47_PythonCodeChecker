package methodAndTool;

import java.util.List;

public class keywordAnalysis {

    WriteAndRead WAR = new WriteAndRead();

    public int getKeyWordSocre(String solution, String answer, List<markScheme> mkl) {

        int score = 0;

        // 返还一个boolean检测是否有syntaxerror;
        boolean c = WAR.checkSolutionSytaxError(solution);
        String Correct_answer = WAR.readText("./src/txt/PyCodeAnswer.txt");
        // false = no syntaxerror
        if (c == false && answer.equals(Correct_answer)) {
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
            return score;
        } else {
            return score;
        }
    }

}
