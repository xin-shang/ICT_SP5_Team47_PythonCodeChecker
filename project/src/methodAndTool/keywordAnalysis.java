package methodAndTool;

import java.util.ArrayList;
import java.util.List;

public class keywordAnalysis {
    



    public keywordAnalysis(){

    }


    public int getKeyWordSocre(String answer, List<markScheme>mkl){

        int score = 0;

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

}
