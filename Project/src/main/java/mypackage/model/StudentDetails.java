package mypackage.model;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tblstudent_details")
public class StudentDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int student_id;
	private String password;
	private String student_name;
	@Column(name = "student_code",nullable = false,unique = true,length = 100)
	private String student_code;
	@Column(name = "email_address",nullable = false,unique = true,length = 100)
	private String email_address;
	@Column(name = "mobile_number",length = 20)
	private String mobile_number;
	@Column(name = "profile_photo",length = 100)
	private String profile_photo;
	private String city;

	@OneToMany(mappedBy = "student_exam_details",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JsonIgnoreProperties("student_exam_details")
	private Set<ExamDetails> examdetails;
	
	@OneToMany(mappedBy = "qualification_id",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JsonIgnoreProperties("qualification_id")
	private List<StudentQualification> qualification;
	private int flag=0;
	
	

	public StudentDetails(int student_id, String password, String student_name, String student_code,
			String email_address, String mobile_number, String profile_photo, String city, Set<ExamDetails> examdetails,
			List<StudentQualification> qualification, int flag) {
		super();
		this.student_id = student_id;
		this.password = password;
		this.student_name = student_name;
		this.student_code = student_code;
		this.email_address = email_address;
		this.mobile_number = mobile_number;
		this.profile_photo = profile_photo;
		this.city = city;
		this.examdetails = examdetails;
		this.qualification = qualification;
		this.flag = flag;
	}

	public String getMobile_number() {
		return mobile_number;
	}

	public void setMobile_number(String mobile_number) {
		this.mobile_number = mobile_number;
	}

	public List<StudentQualification> getQualification() {
		return qualification;
	}

	public void setQualification(List<StudentQualification> qualification) {
		this.qualification = qualification;
	}

	public StudentDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public int getStudent_id() {
		return student_id;
	}
	public void setStudent_id(int student_id) {
		this.student_id = student_id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getStudent_name() {
		return student_name;
	}
	public void setStudent_name(String student_name) {
		this.student_name = student_name;
	}
	
	public String getEmail_address() {
		return email_address;
	}
	public void setEmail_address(String email_address) {
		this.email_address = email_address;
	}

	public String getProfile_photo() {
		return profile_photo;
	}
	public void setProfile_photo(String profile_photo) {
		this.profile_photo = profile_photo;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}

	public String getStudent_code() {
		return student_code;
	}

	public void setStudent_code(String student_code) {
		this.student_code = student_code;
	}

	public Set<ExamDetails> getExamdetails() {
		return examdetails;
	}

	public void setExamdetails(Set<ExamDetails> examdetails) {
		this.examdetails = examdetails;
	}

	
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
}
