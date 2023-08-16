package mypackage.model;

public class QuestionModel {

	private int question_id;
	private String questions;
	private String option1;
	private String option2;
	private String option3;
	private String option4;
	private int correct_answer_no;
	
	private int topic_id;
	private String topic_name;	
	private int content_id;
	private String content_name;
	
	
	public QuestionModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public QuestionModel(int question_id, String questions, String option1, String option2, String option3,
			String option4, int correct_answer_no, int topic_id, String topic_name, int content_id,
			String content_name) {
		super();
		this.question_id = question_id;
		this.questions = questions;
		this.option1 = option1;
		this.option2 = option2;
		this.option3 = option3;
		this.option4 = option4;
		this.correct_answer_no = correct_answer_no;
		this.topic_id = topic_id;
		this.topic_name = topic_name;
		this.content_id = content_id;
		this.content_name = content_name;
	}
	
	public int getQuestion_id() {
		return question_id;
	}
	public void setQuestion_id(int question_id) {
		this.question_id = question_id;
	}
	public String getQuestions() {
		return questions;
	}
	public void setQuestions(String questions) {
		this.questions = questions;
	}
	public String getOption1() {
		return option1;
	}
	public void setOption1(String option1) {
		this.option1 = option1;
	}
	public String getOption2() {
		return option2;
	}
	public void setOption2(String option2) {
		this.option2 = option2;
	}
	public String getOption3() {
		return option3;
	}
	public void setOption3(String option3) {
		this.option3 = option3;
	}
	public String getOption4() {
		return option4;
	}
	public void setOption4(String option4) {
		this.option4 = option4;
	}
	public int getCorrect_answer_no() {
		return correct_answer_no;
	}
	public void setCorrect_answer_no(int correct_answer_no) {
		this.correct_answer_no = correct_answer_no;
	}
	public int getTopic_id() {
		return topic_id;
	}
	public void setTopic_id(int topic_id) {
		this.topic_id = topic_id;
	}
	public String getTopic_name() {
		return topic_name;
	}
	public void setTopic_name(String topic_name) {
		this.topic_name = topic_name;
	}
	public int getContent_id() {
		return content_id;
	}
	public void setContent_id(int content_id) {
		this.content_id = content_id;
	}
	public String getContent_name() {
		return content_name;
	}
	public void setContent_name(String content_name) {
		this.content_name = content_name;
	}
	
}
