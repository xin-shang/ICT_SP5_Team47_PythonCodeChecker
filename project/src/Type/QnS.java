package Type;

public class QnS {

    String question_id;
    String question;
    String solution;
    String answer;
    int answerSocre;

    public QnS(String question_id, String question, String solution, String answer, int answerSocre) {
        this.question_id = question_id;
        this.question = question;
        this.solution = solution;
        this.answer = answer;
        this.answerSocre = answerSocre;
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

    public int getAnswerScore() {
        return this.answerSocre;
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

    public void setAnswerScore(int answerScore) {
        this.answerSocre = answerScore;
    }

}
