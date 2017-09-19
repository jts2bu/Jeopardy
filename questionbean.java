package jeopardy;

public class questionbean implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String question;
	public String answer;
	public String row;
	public String col;
	public String points;
	public boolean notAnswered;
	
	public questionbean() {
		 question = "";
		 answer = "";
		 row = "";
		 col = "";
		 points = "";
		 notAnswered = true;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String q) {
		this.question = q;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String a) {
		this.answer = a;
	}

	public String getRow() {
		return row;
	}

	public void setRow(String row) {
		this.row = row;
	}

	public String getCol() {
		return col;
	}

	public void setCol(String col) {
		this.col = col;
	}

	public String getPoints() {
		return points;
	}

	public void setPoints(String points) {
		this.points = points;
	}
	
	public boolean getNotAnswered() {
		return notAnswered;
	}

	public void setNotAnswered(boolean answer) {
		this.notAnswered = answer;
	}
	
	
	
}
