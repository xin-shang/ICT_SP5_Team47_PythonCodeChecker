package text;

public class QuestionText {

	private String ID;
	private String Type;
	private String QuestionStems;
	private String Result;

	public QuestionText(String id, String type, String questionStems, String result) {
		this.ID = id;
		this.Type = type;
		this.QuestionStems = questionStems;
		this.Result = result;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	public String getQuestionStems() {
		return QuestionStems;
	}

	public void setQuestionStems(String questionStems) {
		QuestionStems = questionStems;
	}

	public String getResult() {
		return Result;
	}

	public void setResult(String result) {
		Result = result;
	}

}
