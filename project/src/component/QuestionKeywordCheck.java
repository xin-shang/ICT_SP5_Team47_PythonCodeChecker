package component;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import methodAndTool.WriteAndRead;

public class QuestionKeywordCheck {

    WriteAndRead WAR = new WriteAndRead();
    Map<String, Integer> keyWords = new LinkedHashMap<>();

    public QuestionKeywordCheck() {
        keyWords = ReadKeywordsList();
        AddKeywordsList("False", 1);
        AddKeywordsList("None", 1);
        AddKeywordsList("True", 1);
        AddKeywordsList("and", 1);
        AddKeywordsList("as", 1);
        AddKeywordsList("assert", 1);
        AddKeywordsList("async", 1);
        AddKeywordsList("await", 1);
        AddKeywordsList("break", 1);
        AddKeywordsList("class", 1);
        AddKeywordsList("continue", 1);
        AddKeywordsList("def", 1);
        AddKeywordsList("del", 1);
        AddKeywordsList("elif", 1);
        AddKeywordsList("else", 1);
        AddKeywordsList("except", 1);
        AddKeywordsList("finally", 1);
        AddKeywordsList("for", 1);
        AddKeywordsList("from", 1);
        AddKeywordsList("global", 1);
        AddKeywordsList("if", 1);
        AddKeywordsList("import", 1);
        AddKeywordsList("in", 1);
        AddKeywordsList("is", 1);
        AddKeywordsList("lambda", 1);
        AddKeywordsList("nonlocal", 1);
        AddKeywordsList("not", 1);
        AddKeywordsList("or", 1);
        AddKeywordsList("pass", 1);
        AddKeywordsList("raise", 1);
        AddKeywordsList("return", 1);
        AddKeywordsList("try", 1);
        AddKeywordsList("while", 1);
        AddKeywordsList("with", 1);
        AddKeywordsList("yield", 1);
        AddKeywordsList("print", 1);
    }

    private Map<String, Integer> ReadKeywordsList() {
        // 从pykeyword读取key word
        String kw = WAR.readText("./src/txt/PyKeyword.txt");
        // 把 keywords 装进list
        List<String> kl = Arrays.asList(kw.split("\n"));

        Map<String, Integer> _keyWords = new LinkedHashMap<>();

        for (String k : kl) {

            // skip "" value
            if (k.equals(""))
                continue;

            // split key, value pair
            String[] keyValue = k.split(",");

            // add key value to Map
            _keyWords.put(keyValue[0], Integer.parseInt(keyValue[1]));
        }
        return _keyWords;
    }

    public Map<String, Integer> GetKeywordsList() {
        return keyWords;
    }

    public void AddKeywordsList(String newkeyword, int score) {
        // avoid duplicates
        if (keyWords.containsKey(newkeyword)) {
            return;
        }

        keyWords.put(newkeyword, score);
        StringBuilder sb = new StringBuilder();
        for (String k : keyWords.keySet()) {
            int s = keyWords.get(k);
            sb.append(k + "," + s);
            sb.append("\n");
        }

        WAR.write2TextFileOutStream("./src/txt/PyKeyword.txt", sb.toString().trim());
        // 重新读一次
        keyWords = ReadKeywordsList();

    }

    public void DeleteKeywordsList(String rmvkeyword) {
        // String newkw = "";
        // for(int i= 0; i<keyWords.size();i++) {
        // if(i == keyWords.size()-1){
        // newkw = newkw + keyWords.get(i);
        // }
        // else{
        // if(keyWords.get(i) != rmvkeyword){
        // newkw = newkw + keyWords.get(i)+',';
        // }
        // }

        // }
        // WAR.write2TextFileOutStream("./src/txt/PyKeyword.txt",newkw);
        // keyWords = ReadKeywordsList();
    }

    public void ChangeKeywordsList() {

    }

    public String GetindexKeywordsList(int index) {

        return null;

    }

}
