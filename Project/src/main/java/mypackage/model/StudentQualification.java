package mypackage.model;

import java.util.List;

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
@Table(name = "tblstudent_qualification")
public class StudentQualification {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int qualification_id;
	private String qualification;
	private String university;
	private int passing_year;
	private float percentage;
	
	@ManyToOne(fetch = FetchType.LAZY,optional = false)
	@JoinColumn(name = "student_id")
	private StudentDetails studentdetail;
	private int flag=0;
	

	public StudentQualification(int qualification_id, String qualification, String university, int passing_year,
			float percentage, StudentDetails studentdetail, int flag) {
		super();
		this.qualification_id = qualification_id;
		this.qualification = qualification;
		this.university = university;
		this.passing_year = passing_year;
		this.percentage = percentage;
		this.studentdetail = studentdetail;
		this.flag = flag;
	}

	public StudentDetails getStudentdetail() {
		return studentdetail;
	}

	public void setStudentdetail(StudentDetails studentdetail) {
		this.studentdetail = studentdetail;
	}

	public StudentQualification() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public int getQualification_id() {
		return qualification_id;
	}
	public void setQualification_id(int qualification_id) {
		this.qualification_id = qualification_id;
	}
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	public String getUniversity() {
		return university;
	}
	public void setUniversity(String university) {
		this.university = university;
	}
	public int getPassing_year() {
		return passing_year;
	}
	public void setPassing_year(int passing_year) {
		this.passing_year = passing_year;
	}
	public float getPercentage() {
		return percentage;
	}
	public void setPercentage(float percentage) {
		this.percentage = percentage;
	}
	
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}

}
