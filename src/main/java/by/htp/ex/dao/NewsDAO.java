package by.htp.ex.dao;

import java.util.List;

import by.htp.ex.bean.News;
import by.htp.ex.dao.exception.NewsDAOException;

public interface NewsDAO {
	
	List<News> getList() throws NewsDAOException;
	List<News> getLatestsList(int count) throws NewsDAOException;
	News fetchById(int id) throws NewsDAOException;
	void editNews(News news) throws NewsDAOException;
	void deleteNews(int id)throws NewsDAOException;
	void deleteNewses(String[] idNewses) throws NewsDAOException;	
	void addNews(News news) throws NewsDAOException;	
	
	
}
