package mypackage.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tblcontent_questions")
public class ContentQuestion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int question_id;
	private String questions;
	private String option1;
	private String option2;
	private String option3;
	private String option4;
	private int correct_answer_no;
	
	@ManyToOne(fetch = FetchType.LAZY,optional = false)
	@JoinColumn(name = "content_id")
	private TopicContent contentquestion;
	
	@OneToMany(mappedBy = "content_question",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JsonIgnoreProperties("content_question")
	private Set<ExamQuestions> examquestion;
	
	@ManyToOne(fetch = FetchType.LAZY,optional = false)
	@JoinColumn(name = "topic_id")
	private Topic topics;
	
	private int flag=0;

	public ContentQuestion() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ContentQuestion(int question_id, String questions, String option1, String option2, String option3,
			String option4, int correct_answer_no, TopicContent contentquestion, Set<ExamQuestions> examquestion,
			Topic topics, int flag) {
		super();
		this.question_id = question_id;
		this.questions = questions;
		this.option1 = option1;
		this.option2 = option2;
		this.option3 = option3;
		this.option4 = option4;
		this.correct_answer_no = correct_answer_no;
		this.contentquestion = contentquestion;
		this.examquestion = examquestion;
		this.topics = topics;
		this.flag = flag;
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

	public TopicContent getContentquestion() {
		return contentquestion;
	}

	public void setContentquestion(TopicContent contentquestion) {
		this.contentquestion = contentquestion;
	}

	public Set<ExamQuestions> getExamquestion() {
		return examquestion;
	}

	public void setExamquestion(Set<ExamQuestions> examquestion) {
		this.examquestion = examquestion;
	}

	public Topic getTopics() {
		return topics;
	}

	public void setTopics(Topic topics) {
		this.topics = topics;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	
}
