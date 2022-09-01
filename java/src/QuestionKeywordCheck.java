package component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import methodAndTool.WriteAndRead;


public class QuestionKeywordCheck {
    
    WriteAndRead WAR = new WriteAndRead();
    List<String> keyWords = new ArrayList<>();


    public QuestionKeywordCheck(){
        keyWords = ReadKeywordsList();
    }   

    private List<String> ReadKeywordsList(){
        //从pykeyword读取key word
        String kw = WAR.readText("./src/txt/PyKeyword.txt");
        //ba keywords 装进list
        List<String> kl = Arrays.asList(kw.split(","));
        return kl;
        
    }

    public List<String> GetKeywordsList(){
        return keyWords;
    }

    public void AddKeywordsList(String newkeyword){
        String newkw = "";
        
        for(int i= 0; i<keyWords.size();i++) { 
            newkw =  newkw + keyWords.get(i)+',';
        }
        newkw = newkw + newkeyword;
        WAR.write2TextFileOutStream("./src/txt/PyKeyword.txt",newkw);
        //重新读一次
        keyWords = ReadKeywordsList();
              
    }

    public void DeleteKeywordsList(){

    }
    public void ChangeKeywordsList(){

    }








}
