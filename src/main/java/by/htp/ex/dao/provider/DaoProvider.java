package by.htp.ex.dao.provider;

import by.htp.ex.dao.NewsDAO;
import by.htp.ex.dao.UserDAO;
import by.htp.ex.dao.impl.NewsDAOImpl;
import by.htp.ex.dao.impl.UserDAOImpl;

public final class DaoProvider {
	private static final DaoProvider instance = new DaoProvider();

	private final UserDAO userDao = new UserDAOImpl();
	private final NewsDAO newsDAO = new NewsDAOImpl();
	
	
	private DaoProvider() {
	}
	
	
	public UserDAO getUserDao() {
		return userDao;
	}
	
	public NewsDAO getNewsDAO() {
		return newsDAO;
	}

	public static DaoProvider getInstance() {
		return instance;
	}
}
