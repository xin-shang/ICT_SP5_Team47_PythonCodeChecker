package methodAndTool;

public class markScheme {

    String keywordID;

    String keyword;
    int Score;

    public markScheme(String keywordID, String keyword, int Score) {
        this.keywordID = keywordID;
        this.keyword = keyword;
        this.Score = Score;
    }

    public String getkeywordID() {
        return this.keywordID;
    }

    public String getKeyword() {
        return this.keyword;
    }

    public int getScore() {
        return this.Score;
    }

}
