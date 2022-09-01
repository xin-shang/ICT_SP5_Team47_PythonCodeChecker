package methodAndTool;

import java.util.List;

public class QnS {

    String id;
    String question;
    String solution;
    String answer;
    List<markScheme> mark;

    public QnS(String id, String question, String solution, String answer, List<markScheme> mark) {
        this.id = id;
        this.question = question;
        this.solution = solution;
        this.answer = answer;
        this.mark = mark;
    }

    public String getId() {
        return this.id;
    }

    public String getQuestion() {
        return this.question;
    }

    public String getSolution() {
        return this.solution;
    }

    public String getAnswer() {
        return this.answer;
    }

    public List<markScheme> getMarkList() {
        return this.mark;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setMark(List<markScheme> markScheme) {
        this.mark = markScheme;
    }

}
