package mypackage.repository;


import mypackage.model.Email;

public interface EmailRepository{

	public String SendEmail(Email email);
}
