package mypackage.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "tblexam_details")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExamDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int exam_id;
	
	@ManyToOne(fetch = FetchType.LAZY,optional = false)
	@JoinColumn(name = "student_id")
	private StudentDetails student_exam_details;
	
	@Column(name = "exam_data",length = 40)
	private String exam_data;
	@Column(name = "start_time",length = 20)
	private String start_time;
	@Column(name = "end_time")
	private String end_time;
	private int flag=0;
	
	@OneToMany(mappedBy = "examdetails",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JsonIgnoreProperties("examdetails")
	private Set<ExamQuestions> examquestions;
	
	public ExamDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ExamDetails(int exam_id, StudentDetails student_exam_details, String exam_data, String start_time,
			String end_time, int flag, Set<ExamQuestions> examquestions) {
		super();
		this.exam_id = exam_id;
		this.student_exam_details = student_exam_details;
		this.exam_data = exam_data;
		this.start_time = start_time;
		this.end_time = end_time;
		this.flag = flag;
		this.examquestions = examquestions;
	}
	public StudentDetails getStudent_exam_details() {
		return student_exam_details;
	}
	public void setStudent_exam_details(StudentDetails student_exam_details) {
		this.student_exam_details = student_exam_details;
	}
	public Set<ExamQuestions> getExamquestions() {
		return examquestions;
	}
	public void setExamquestions(Set<ExamQuestions> examquestions) {
		this.examquestions = examquestions;
	}
	public int getExam_id() {
		return exam_id;
	}
	public void setExam_id(int exam_id) {
		this.exam_id = exam_id;
	}
	
	public String getExam_data() {
		return exam_data;
	}
	public void setExam_data(String exam_data) {
		this.exam_data = exam_data;
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	
}
