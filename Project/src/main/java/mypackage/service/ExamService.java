package mypackage.service;

import mypackage.model.*;
import mypackage.repository.*;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExamService {

	@Autowired
	ExamDetailsRepository examdetailsrepo;

	@Autowired
	ExamQuestionsRepository examquestionrepo;

	@Autowired
	ContentQuestionRepository contentqusetionrepo;

	public List<ExamDetails> getAllExamDetails() {
		List<ExamDetails> lst = new ArrayList<ExamDetails>();
		for (ExamDetails e : examdetailsrepo.findAll()) {
			StudentDetails sd = new StudentDetails(e.getStudent_exam_details().getStudent_id(),
					e.getStudent_exam_details().getPassword(), e.getStudent_exam_details().getStudent_name(),
					e.getStudent_exam_details().getStudent_code(), e.getStudent_exam_details().getEmail_address(),
					e.getStudent_exam_details().getMobile_number(), e.getStudent_exam_details().getProfile_photo(),
					e.getStudent_exam_details().getCity(), null, null, e.getStudent_exam_details().getFlag());
			ExamDetails ed = new ExamDetails(0, sd, e.getExam_data(), e.getStart_time(), e.getEnd_time(), e.getFlag(),
					e.getExamquestions());
			lst.add(ed);
		}
		return lst;
	}

	public ExamDetails getExamDetailsById(int id) {
		ExamDetails e = examdetailsrepo.findById(id).get();
		StudentDetails sd = new StudentDetails(e.getStudent_exam_details().getStudent_id(),
				e.getStudent_exam_details().getPassword(), e.getStudent_exam_details().getStudent_name(),
				e.getStudent_exam_details().getStudent_code(), e.getStudent_exam_details().getEmail_address(),
				e.getStudent_exam_details().getMobile_number(), e.getStudent_exam_details().getProfile_photo(),
				e.getStudent_exam_details().getCity(), null, null, e.getStudent_exam_details().getFlag());
		ExamDetails ed = new ExamDetails(0, sd, e.getExam_data(), e.getStart_time(), e.getEnd_time(), e.getFlag(),
				e.getExamquestions());
		return ed;
	}

	public List<ExamDetails> getExamDetaileByStudentId(int sid) {
		List<ExamDetails> lst = new ArrayList<ExamDetails>();
		for (ExamDetails e : examdetailsrepo.findAll()) {
			if (e.getStudent_exam_details().getStudent_id() == sid) {
				StudentDetails sd = new StudentDetails(e.getStudent_exam_details().getStudent_id(),
						e.getStudent_exam_details().getPassword(), e.getStudent_exam_details().getStudent_name(),
						e.getStudent_exam_details().getStudent_code(), e.getStudent_exam_details().getEmail_address(),
						e.getStudent_exam_details().getMobile_number(), e.getStudent_exam_details().getProfile_photo(),
						e.getStudent_exam_details().getCity(), null, null, e.getStudent_exam_details().getFlag());
				ExamDetails ed = new ExamDetails(0, sd, e.getExam_data(), e.getStart_time(), e.getEnd_time(),
						e.getFlag(), e.getExamquestions());
				lst.add(ed);
			}
		}
		return lst;
	}

	public ExamDetails addNewExamDetails(ExamDetails e) {
		ExamDetails ed = new ExamDetails(0, e.getStudent_exam_details(), e.getExam_data(), e.getStart_time(),
				e.getEnd_time(), e.getFlag(), null);
		ExamDetails exam = examdetailsrepo.save(ed);
		for (ExamQuestions eq : e.getExamquestions()) {
			ExamQuestions examq = new ExamQuestions(0, exam, eq.getContent_question(), eq.getSubmitted_option_number());
			examquestionrepo.save(examq);
		}
		return exam;
	}

	public ExamDetails updateExamDetails(ExamDetails e) {
		ExamDetails ed = examdetailsrepo.save(e);
		return ed;
	}

	public ExamDetails deleteExamDetails(int id) {
		ExamDetails e = getExamDetailsById(id);
		examdetailsrepo.delete(e);
		return e;
	}
//	============================================================================================================

	public ExamQuestions AddExamQuestion(ExamQuestions eq) {
		return examquestionrepo.save(eq);
	}

	public List<ExamQuestions> GetAllExamQuestion(int eid) {
		List<ExamQuestions> lst = new ArrayList<ExamQuestions>();
		for (ExamQuestions eq : examquestionrepo.findAll()) {
			if (eq.getExamdetails().getExam_id() == eid) {
				ExamDetails ed = new ExamDetails(eq.getExamdetails().getExam_id(), null,
						eq.getExamdetails().getExam_data(), eq.getExamdetails().getStart_time(),
						eq.getExamdetails().getEnd_time(), eq.getExamdetails().getFlag(), null);
				Topic t = new Topic(eq.getContent_question().getTopics().getTopic_id(),
						eq.getContent_question().getTopics().getTopic_name(), null, null,
						eq.getContent_question().getTopics().getFlag());
				ContentQuestion q = new ContentQuestion(eq.getContent_question().getQuestion_id(),
						eq.getContent_question().getQuestions(), eq.getContent_question().getOption1(),
						eq.getContent_question().getOption2(), eq.getContent_question().getOption3(),
						eq.getContent_question().getOption4(), eq.getContent_question().getCorrect_answer_no(), null,
						null, t, eq.getContent_question().getFlag());
				ExamQuestions e = new ExamQuestions(eq.getExam_question_id(), ed, q, eq.getSubmitted_option_number());
				lst.add(e);
			}
		}
		return lst;
	}

//====================================OR Different type==============================================================
//	public List<QuestionModel> GetTopicWiseQuestion(int topic_id){
//		 List<QuestionModel> lst = new ArrayList<QuestionModel>();
//		 for(QuestionModel qm : getAllExamQuestions()) {
//			 if(qm.getTopic_id()==topic_id) {
//				 lst.add(qm);
//			 }
//		 }
//		 return lst;
//	}
//	
//	
//	public List<QuestionModel> getAllExamQuestions() {
//		List<QuestionModel>lst = new ArrayList<QuestionModel>();
//		for(ContentQuestion q : contentqusetionrepo.findAll()) {
//			int topic_id = q.getContent().getTopic().getTopic_id();
//			String topic_name = q.getContent().getTopic().getTopic_name();
//			int content_id = q.getContent().getContent_id();
//			String content_name = q.getContent().getContent_name();
//			QuestionModel qm = new QuestionModel(q.getQuestion_id(),q.getQuestions(),q.getOption1(),q.getOption2(), q.getOption3(), q.getOption4(), q.getCorrect_answer_no(), topic_id, topic_name, content_id, content_name);
//			lst.add(qm);
//		}
//		return lst;
//	}

}
