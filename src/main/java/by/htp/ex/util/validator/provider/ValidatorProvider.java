package by.htp.ex.util.validator.provider;

import by.htp.ex.util.validator.impl.LoginationValidatorImpl;
import by.htp.ex.util.validator.impl.RegistrationValidatorImpl;

public final class ValidatorProvider {

	private static final ValidatorProvider instance = new ValidatorProvider();
	
	private LoginationValidatorImpl loginationValidator = new LoginationValidatorImpl();
	private RegistrationValidatorImpl registrationValidator = new RegistrationValidatorImpl();
	
	private ValidatorProvider() {}
	
	public static ValidatorProvider getInstance() {
		return instance;
	}

	public LoginationValidatorImpl getLoginationValidator() {
		return loginationValidator;
	}

	public RegistrationValidatorImpl getRegistrationValidator() {
		return registrationValidator;
	}

}
