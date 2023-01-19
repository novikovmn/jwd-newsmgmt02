package by.htp.ex.dao;

import by.htp.ex.bean.User;
import by.htp.ex.dao.exception.UserDAOException;

public interface UserDAO {
	
	User logination(String login, String password) throws UserDAOException;
	boolean registration(User user) throws UserDAOException;
	String getRole(User user) throws UserDAOException;

}
