package mypackage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mypackage.model.Email;
import mypackage.service.EmailServices;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET,RequestMethod.POST},allowedHeaders = "*")
public class EmailController {
	
	@Autowired
	EmailServices eservice;
	
	@PostMapping("api/sendemail")
	public Email SendEmail(@RequestBody Email e) {
		eservice.SendEmail(e);
		return e;
	}
	
//	@GetMapping("api/sendemail")
	
//	public String SendEmails() {
//		Email e = new Email("machindranikat302002@gmail.com", "SampleEmailmsg", "This is test email");
//		String msg = eservice.sendSimpleMail(e);
//		return msg;
//	}
}
