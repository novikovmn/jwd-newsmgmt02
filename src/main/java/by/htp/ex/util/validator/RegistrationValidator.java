package by.htp.ex.util.validator;

public interface RegistrationValidator extends Validator{

	String EMAIL_TEMPLATE_REGEX = "[\\w\\.]+@\\w+\\.\\w+";
	
	boolean checkRegistrationData(String login, String password, String email);
	
}
