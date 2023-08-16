package mypackage.controller;
import mypackage.model.*;
import mypackage.service.*;
import mypackage.repository.*;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*",methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT},allowedHeaders = "*")
public class TopicContentController {

	@Autowired
	TopicServices services;
	
	@GetMapping("api/topic")
	public List<Topic> GetAllTopic(){
		return services.GetAllTopics();
	}


	@GetMapping("api/topic/{id}")
	public Topic GetTopicById(@PathVariable("id")int id){
		return services.GetTopicById(id);
	}
	
	@PostMapping("api/topic")
	public Topic AddTopic(@RequestBody Topic t) {
		return services.AddTopic(t);
	}
	
	@PutMapping("api/topic")
	public Topic UpdateTopic(@RequestBody Topic t) {
		return services.UpdateTopic(t);
	}
	
	//permanentDelete
//	@DeleteMapping("api/topic/{id}")
//	public Topic DeleteTopic(@PathVariable("id")int id) {
//		return services.DeleteTopic(id);
//	}
	@DeleteMapping("api/topic/{id}")
	public Topic DeleteTopic(@PathVariable("id")int id) {
		return services.DeleteTopicById(id);
	}
//	=============================================================
	
	
	@GetMapping("api/content")
	public List<TopicContent> GetAllContent(){
		return services.GetAllTopicContent();
	}
	
	@PostMapping("api/content")
	public TopicContent AddTopicContent(@RequestBody TopicContent t) {
		return services.AddTopicContent(t);
	}
	
	@GetMapping("api/topicwisecontents/{id}")
	public List<TopicContent> GetAllContent(@PathVariable("id")int id){
		return services.GetTopicWiseContent(id);
	}
	
	@PutMapping("api/content")
	public String UpdateTopicContent(@RequestBody TopicContent t) {
		services.UpdateTopicContent(t);
		return "content update successfully";
	}
	
	@DeleteMapping("api/content/{id}")
	public String DeleteTopicContent(@PathVariable("id")int id) {
	     services.deleteTopicContent(id);
	     return "Content Deleted Successfully";
	}
	
//	@DeleteMapping("api/content/{id}")
//	public TopicContent deleteTopicContent(@PathVariable("id")int id) {
//		return services.deleteTopicContent(id);
//	}
//	===================================================================
	
	@GetMapping("api/content_question")
	public List<ContentQuestion> GetAllQuestion(){
		return services.GetAllContentQuestion();
	}
	
	@GetMapping("api/topicwisequestion/{id}")
	public List<ContentQuestion> getTopicWiseQuestion(@PathVariable("id")int tid) {
		return services.GetTopicWiseQuestion(tid);
	}
	
	@PostMapping("api/content_question")
	public ContentQuestion AddContentQuestion(@RequestBody ContentQuestion t) {
		return services.AddContentQuestion(t);
	}
	
	@PutMapping("api/content_question")
	public String UpdateContentQuestion(@RequestBody ContentQuestion tc) {
		 services.UpdateContentQuestion(tc);
		 return "Questions Update Successfully";
	}
	
	@DeleteMapping("api/content_question/{id}")
	public String DeleteContentQuestion(@PathVariable("id")int id) {
		services.deleteContentQuestion(id);
		return "Question Deleted Successfully";
	}
	
}
