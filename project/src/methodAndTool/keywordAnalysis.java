package methodAndTool;

import java.util.ArrayList;
import java.util.List;

public class keywordAnalysis {

    WriteAndRead WAR = new WriteAndRead();




    public keywordAnalysis(){

        //测试用
        // String answer = "printsss('cat')";


		// markScheme mk = new markScheme("1", "print", 100);
		// List<markScheme> mkl = new ArrayList<markScheme>();
		// mkl.add(mk);

		

		// keywordAnalysis ka = new keywordAnalysis();
		// int score = ka.getKeyWordSocre(answer, mkl);
		// System.out.println(score);
    }


    public int getKeyWordSocre(String answer, List<markScheme>mkl){

        int score = 0;

        //返还一个boolean检测是否有syntaxerror;
        boolean c = WAR.checkSolutionSytaxError(answer);

        //false = no syntaxerror
        if(c==false){
            for(markScheme mk: mkl){
            String keyword = mk.getKeyword();
            boolean bcheck = answer.contains(keyword);
                if(bcheck==true){

                    score += mk.getScore();
                }
                else{
                    score+=0;
                }
                }

                return score;
            }
            else{
                return score;
            }
        }


}
