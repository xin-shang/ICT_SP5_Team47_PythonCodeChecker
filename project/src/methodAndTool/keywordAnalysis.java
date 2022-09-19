package methodAndTool;

import java.util.ArrayList;
import java.util.List;

public class keywordAnalysis {

    WriteAndRead WAR = new WriteAndRead();




    public keywordAnalysis(){

      
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
                    String deleteKw = answer.replaceFirst(mk.getKeyword(), "");
                    answer = deleteKw;
                    System.out.println(answer);
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
