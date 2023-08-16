package mypackage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mypackage.model.ExamDetails;
import mypackage.model.ExamQuestions;
import mypackage.model.QuestionModel;
import mypackage.service.ExamService;

@RestController
@CrossOrigin(origins =  "*", methods =  { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE }, allowedHeaders = "*")
public class ExamController {
	
	@Autowired
	ExamService eservice;
	
	
	@PostMapping("api/examdetails")
	public ExamDetails AddExamDetails(@RequestBody ExamDetails e) {
		return eservice.addNewExamDetails(e);
	}

	@GetMapping("api/examdetails")
	public List<ExamDetails> GetAllExamDetails(){
		return eservice.getAllExamDetails();
	}
	
	@GetMapping("api/examdetails/{id}")
	public ExamDetails GetAllExamDetailsById(@PathVariable("id")int id){
		return eservice.getExamDetailsById(id);
	}
	
	@GetMapping("api/examdetailsbystudentid/{id}")
	public List<ExamDetails> getExamDetailsByStudentId(@PathVariable("id")int sid) {
		return eservice.getExamDetaileByStudentId(sid);
	}
	
	@PutMapping("api/examdetails")
	public ExamDetails UpdateExamDetails(@RequestBody ExamDetails e) {
		return eservice.updateExamDetails(e);
	}
	
	@DeleteMapping("api/examdetails/{id}")
	public ExamDetails DeleteExamDetails(@PathVariable("id")int id) {
		return eservice.deleteExamDetails(id);
	}
	
//	============================Exam Question==================================================
	
//	@GetMapping("api/examquestion/{id}")
//    public List<ExamQuestions> GetExamQuestion(@PathVariable("id")int id){
//		return eservice.GetAllExamQuestion(id);
//	}
	
	@PostMapping("api/examquestion")
	public ExamQuestions AddExamQuestion(@RequestBody ExamQuestions e) {
		return eservice.AddExamQuestion(e);
	}
//	
//	@GetMapping("api/examquestion/{id}")
//	public List<ExamQuestions> GetTopicWiseQuestion(@PathVariable("id")int id) {
//		return eservice.GetTopicWiseExamQuestion(id);
//	}
	
	
//	============================OR == Exam Question==================================================

//	@GetMapping("api/examquestion")
//	public List<QuestionModel> GetExamQuestion(){
//		return eservice.getAllExamQuestions();
//	}
//	
//	@GetMapping("api/examquestion/{id}")
//	public List<QuestionModel>GetExamQuestion(@PathVariable("id")int id){
//		return eservice.GetTopicWiseQuestion(id);
//	}

}
