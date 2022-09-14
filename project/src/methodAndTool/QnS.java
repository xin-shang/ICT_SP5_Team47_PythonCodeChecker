package methodAndTool;

import java.util.List;

public class QnS {

    String question_id;
    String question;
    String solution;
    String answer;
    List<markScheme> mark;

    public QnS(String question_id, String question, String solution, String answer) {
        this.question_id = question_id;
        this.question = question;
        this.solution = solution;
        this.answer = answer;
    }

    public String getQuestionID() {
        return this.question_id;
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
