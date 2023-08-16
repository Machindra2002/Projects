package mypackage.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import mypackage.model.Email;
import mypackage.repository.EmailRepository;

@Service
public class EmailServices implements EmailRepository{
	
	@Autowired JavaMailSender mailsender;
	
	@Value("${spring.mail.username}")
	private String sender;
	
	public String SendEmail(Email edetails) {
		try {
			SimpleMailMessage mailmsg = new SimpleMailMessage();
			mailmsg.setFrom(sender);
			mailmsg.setTo(edetails.getEmail_address());
			mailmsg.setText(edetails.getMessage());
			mailmsg.setSubject(edetails.getSubject());
			mailsender.send(mailmsg);
			return "Mail sent Successfully...";
			
		}
		catch(Exception e) {
			return e.getMessage();	
		}
	}

}

