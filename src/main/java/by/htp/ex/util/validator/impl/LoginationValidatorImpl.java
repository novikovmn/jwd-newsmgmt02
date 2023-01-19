package by.htp.ex.util.validator.impl;

import by.htp.ex.util.validator.LoginationValidator;

public class LoginationValidatorImpl implements LoginationValidator {

	@Override
	public boolean checkLoginationData(String login, String password) {

		if (login.matches(LOGIN_TEMPLATE_REGEX) && password.matches(PASSWORD_TEMPLATE_REGEX)) {
			return true;
		} else {
			return false;
		}

	}

}
