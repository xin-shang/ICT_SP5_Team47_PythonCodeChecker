package component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import methodAndTool.WriteAndRead;


public class QuestionKeywordCheck {
    
    WriteAndRead WAR = new WriteAndRead();
    Map<String, Integer> keyWords = new LinkedHashMap<>();


    public QuestionKeywordCheck(){
        keyWords = ReadKeywordsList();
        AddKeywordsList("if", 1);
        AddKeywordsList("while", 1);
        AddKeywordsList("for", 1);



    }   

    private Map<String, Integer> ReadKeywordsList(){
        //从pykeyword读取key word
        String kw = WAR.readText("./src/txt/PyKeyword.txt");
        //把 keywords 装进list
        List<String> kl = Arrays.asList(kw.split("\n"));
        
        Map<String, Integer> _keyWords = new LinkedHashMap<>();

        for(String k : kl){

            // skip "" value
            if(k.equals("")) continue;

            // split key, value pair
            String[] keyValue = k.split(",");

            // add key value to Map
            _keyWords.put(keyValue[0], Integer.parseInt(keyValue[1]));
        }
        return _keyWords;  
    }

    public Map<String, Integer> GetKeywordsList(){
        return keyWords;
    }

    public void AddKeywordsList(String newkeyword, int score){
        //avoid duplicates
        if(keyWords.containsKey(newkeyword)){
            return;
        }

        keyWords.put(newkeyword, score);
        StringBuilder sb = new StringBuilder();
        for(String k : keyWords.keySet()){
            int s = keyWords.get(k);
            sb.append(k + "," + s);
            sb.append("\n");
        }

        WAR.write2TextFileOutStream("./src/txt/PyKeyword.txt", sb.toString().trim());
        //重新读一次
        keyWords = ReadKeywordsList();
              
    }

    public void DeleteKeywordsList(String rmvkeyword){
        // String newkw = "";
        // for(int i= 0; i<keyWords.size();i++) { 
        //     if(i == keyWords.size()-1){
        //         newkw =  newkw + keyWords.get(i);
        //     }   
        //     else{
        //         if(keyWords.get(i) != rmvkeyword){
        //             newkw =  newkw + keyWords.get(i)+',';
        //         }
        //     } 
            
                   
        // }
        // WAR.write2TextFileOutStream("./src/txt/PyKeyword.txt",newkw);
        // keyWords = ReadKeywordsList();
    }
    public void ChangeKeywordsList(){

    }
    public String GetindexKeywordsList(int index){

        return null;

    }

}
