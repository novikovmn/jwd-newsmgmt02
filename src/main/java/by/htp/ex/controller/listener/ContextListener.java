package by.htp.ex.controller.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import by.htp.ex.dao.cpool.ConnectionPool;
import by.htp.ex.dao.cpool.exception.ConnectionPoolException;

public class ContextListener implements ServletContextListener{
	
	/*
	 * create and destroy connection pool
	 * 
	 * */
	
	private static final ConnectionPool connectionPool = ConnectionPool.getInstance();

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		try {
			connectionPool.initPoolData();
		} catch (ClassNotFoundException | ConnectionPoolException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		connectionPool.dispose();
	}
	
	
}
