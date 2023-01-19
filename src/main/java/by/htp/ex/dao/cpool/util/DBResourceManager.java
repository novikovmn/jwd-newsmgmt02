package by.htp.ex.dao.cpool.util;

import java.util.ResourceBundle;

public final class DBResourceManager {
	
	private final static DBResourceManager instance = new DBResourceManager();
	
	private ResourceBundle bundle = ResourceBundle.getBundle("newsdb");
	
	private DBResourceManager() {
		
	}

	public static DBResourceManager getInstance() {
		return instance;
	}
	
	public String getValue(String key) {
		return bundle.getString(key);
	}
	
	

}


