package mypackage.service;

import mypackage.model.*;
import mypackage.repository.*;
import java.util.ArrayList;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServices {

	@Autowired
	StudentDetailsRepository studdetailrepo;
	@Autowired
	StudentQualificationRepository studqualirepo;
	@Autowired
	EmailRepository eserv;

	public String GenratePassword(int size) {
		String data = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQQRSTUVWXYZ1234567890!@#$%^&";
		String password = "";
		Random r = new Random();
		for (int i = 0; i < size; i++) {
			password += data.charAt(r.nextInt(data.length()));
		}
		return password;
	}

	public String NextStudentCode() {

		String code = "";
		List<StudentDetails> lst = studdetailrepo.findAll();
		int id = 0;
		if (lst.size() > 0) {
			id = lst.size() + 1;
		} else {
			id = 1;
		}

		code = "S";
		if (id < 10) {
			code += "00000000" + id;
		} else if (id >= 10 && id < 99) {
			code += "00000000" + id;
		} else if (id >= 99 && id < 999) {
			code += "00000000" + id;
		} else if (id >= 999 && id < 99999) {
			code += "00000000" + id;
		}
		return code;
	}

	public List<StudentDetails> GetAllStudentDetails() {
		List<StudentDetails> lst = new ArrayList<StudentDetails>();
		for (StudentDetails s : studdetailrepo.findAll()) {
			StudentDetails sd = new StudentDetails(s.getStudent_id(), s.getPassword(), s.getStudent_name(),
					s.getStudent_code(), s.getEmail_address(), s.getMobile_number(), s.getProfile_photo(), s.getCity(),
					null, null, 0);
			lst.add(sd);
		}
		return lst;
	}

	public StudentDetails GetStudentDetailsById(int id) {
		StudentDetails s = studdetailrepo.findById(id).get();
		StudentDetails sd = new StudentDetails(s.getStudent_id(), s.getPassword(), s.getStudent_name(),
				s.getStudent_code(), s.getEmail_address(), s.getMobile_number(), s.getProfile_photo(), s.getCity(),
				null, null, 0);
		return sd;
	}

	public StudentDetails AddStudentDetails(StudentDetails s) {
		String password = GenratePassword(10);
		StudentDetails sd = new StudentDetails(s.getStudent_id(), password, s.getStudent_name(), NextStudentCode(),
				s.getEmail_address(), s.getMobile_number(), s.getProfile_photo(), s.getCity(), null, null, 0);
		System.out.println(sd.getStudent_code());
		StudentDetails studentdetails = studdetailrepo.save(sd);
		String subject = "Student Registration Successfully";
		String message = "Dear " + sd.getStudent_name()
				+ ", Your account has been created successfully. You can access your account using following credentials \n Student Code="
				+ sd.getStudent_code() + " and Password=" + password + "...Thanks";
		Email em = new Email(sd.getEmail_address(), subject, message);
		eserv.SendEmail(em);
		return studentdetails;
	}

	public StudentDetails UpdateStudentDetails(StudentDetails s) {
		StudentDetails student = studdetailrepo.save(s);
		return student;
	}

	public StudentDetails DeleteStudentDetails(int id) {
		StudentDetails student = GetStudentDetailsById(id);
		studdetailrepo.delete(student);
		return student;
	}
//	==================================================================================

	public StudentQualification AddStudentQualification(StudentQualification q) {
		StudentQualification qualification = studqualirepo.save(q);
		return qualification;
	}

	public List<StudentQualification> GetAllQualifcation() {
		List<StudentQualification> lst = new ArrayList<StudentQualification>();
		for (StudentQualification s : studqualirepo.findAll()) {
			StudentQualification q = new StudentQualification(s.getQualification_id(), s.getQualification(),
					s.getUniversity(), s.getPassing_year(), s.getPercentage(), null, 0);
			lst.add(q);
		}
		return lst;
	}

	public StudentQualification GetQualifcationById(int id) {
		StudentQualification s = studqualirepo.findById(id).get();
		StudentQualification q = new StudentQualification(s.getQualification_id(), s.getQualification(),
				s.getUniversity(), s.getPassing_year(), s.getPercentage(), null, 0);
		return q;
	}

	public StudentQualification UpdateQualification(StudentQualification sq) {
		return studqualirepo.save(sq);
	}

	public StudentQualification DeleteQualification(int id) {
		StudentQualification sq = GetQualifcationById(id);
		studqualirepo.delete(sq);
		return sq;
	}

	// login
	public StudentDetails GetStudentLoginDetails(StudentDetails d) {
		StudentDetails sd = null;
		for (StudentDetails s : studdetailrepo.findAll()) {
			if (d.getStudent_code().equals(d.getStudent_code()) && d.getPassword().equals(d.getPassword())) {
				sd = new StudentDetails(s.getStudent_id(), s.getPassword(), s.getStudent_name(), NextStudentCode(),
						s.getEmail_address(), s.getMobile_number(), s.getProfile_photo(), s.getCity(), null, null, 0);
			}
		}
		return sd;
	}

}
