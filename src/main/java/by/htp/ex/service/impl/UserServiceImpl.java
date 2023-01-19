package by.htp.ex.service.impl;

import by.htp.ex.bean.User;
import by.htp.ex.dao.UserDAO;
import by.htp.ex.dao.exception.UserDAOException;
import by.htp.ex.dao.provider.DaoProvider;
import by.htp.ex.service.UserService;
import by.htp.ex.service.exception.UserServiceException;
import by.htp.ex.util.validator.impl.LoginationValidatorImpl;
import by.htp.ex.util.validator.impl.RegistrationValidatorImpl;
import by.htp.ex.util.validator.provider.ValidatorProvider;

public class UserServiceImpl implements UserService {

	private final UserDAO userDAO = DaoProvider.getInstance().getUserDao();

	private final LoginationValidatorImpl loginationValidator = ValidatorProvider.getInstance()
			.getLoginationValidator();

	@Override
	public User signIn(String login, String password) throws UserServiceException {
		User user = null;
		try {
			if (loginationValidator.checkLoginationData(login, password)) {
				user = userDAO.logination(login, password);
			}
		} catch (UserDAOException e) {
			throw new UserServiceException(e);
		}
		return user;
	}

	private final RegistrationValidatorImpl registrationValidator = ValidatorProvider.getInstance()
			.getRegistrationValidator();

	@Override
	public boolean registration(User user) throws UserServiceException {

		String userName = user.getUserName();
		String password = user.getPassword();
		String email = user.getEmail();

		try {
			if (registrationValidator.checkRegistrationData(userName, password, email)) {
				if(userDAO.registration(user)) {
					return true;
				}
			} 
		} catch (UserDAOException e) {
			throw new UserServiceException(e);
		}
		return false;
	}

	@Override
	public String getRole(User user) throws UserServiceException {
		String role = null;
		try {
			role = userDAO.getRole(user);
		} catch (UserDAOException e) {
			throw new UserServiceException(e);
		}
		return role;
	}

}
