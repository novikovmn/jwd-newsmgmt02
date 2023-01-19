package by.htp.ex.service;

import by.htp.ex.bean.User;
import by.htp.ex.service.exception.UserServiceException;

public interface UserService {
	
	User signIn(String login, String password) throws UserServiceException;
	boolean registration(User user) throws UserServiceException;
	String getRole(User user) throws UserServiceException;

}
