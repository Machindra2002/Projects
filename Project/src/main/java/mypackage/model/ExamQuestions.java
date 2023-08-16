package mypackage.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tblexam_questions")
public class ExamQuestions {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int exam_question_id;
	
	@ManyToOne(fetch = FetchType.LAZY,optional = false)
	@JoinColumn(name = "exam_id")
	private ExamDetails examdetails;
	
	@ManyToOne(fetch = FetchType.LAZY,optional = false)
	@JoinColumn(name = "question_id")
	private ContentQuestion content_question;
	
	private int submitted_option_number;

	public ExamQuestions() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ExamQuestions(int exam_question_id, ExamDetails examdetails, ContentQuestion content_question,
			int submitted_option_number) {
		super();
		this.exam_question_id = exam_question_id;
		this.examdetails = examdetails;
		this.content_question = content_question;
		this.submitted_option_number = submitted_option_number;
	}

	public int getExam_question_id() {
		return exam_question_id;
	}

	public void setExam_question_id(int exam_question_id) {
		this.exam_question_id = exam_question_id;
	}

	public ExamDetails getExamdetails() {
		return examdetails;
	}

	public void setExamdetails(ExamDetails examdetails) {
		this.examdetails = examdetails;
	}

	public ContentQuestion getContent_question() {
		return content_question;
	}

	public void setContent_question(ContentQuestion content_question) {
		this.content_question = content_question;
	}

	public int getSubmitted_option_number() {
		return submitted_option_number;
	}

	public void setSubmitted_option_number(int submitted_option_number) {
		this.submitted_option_number = submitted_option_number;
	}

	
	

}
