package by.htp.ex.service.impl;

import java.util.List;

import by.htp.ex.bean.News;
import by.htp.ex.dao.NewsDAO;
import by.htp.ex.dao.exception.NewsDAOException;
import by.htp.ex.dao.provider.DaoProvider;
import by.htp.ex.service.NewsService;
import by.htp.ex.service.exception.NewsServiceException;

public class NewsServiceImpl implements NewsService{

	private final NewsDAO newsDAO = DaoProvider.getInstance().getNewsDAO();	

	@Override
	public List<News> getLatestsList(int count) throws NewsServiceException {
		
		try {
			return newsDAO.getLatestsList(count);
		} catch (NewsDAOException e) {
			throw new NewsServiceException(e);
		}
	}

	@Override
	public List<News> getList() throws NewsServiceException {
		try {
			return newsDAO.getList();
		} catch (NewsDAOException e) {
			throw new NewsServiceException(e); 
		}
	}

	@Override
	public News fetchById(int id) throws NewsServiceException {
		try {
			return newsDAO.fetchById(id);
		} catch (NewsDAOException e) {
			throw new NewsServiceException(e);
		}
	}
	
	@Override
	public void editNews(News news) throws NewsServiceException {		
		try {
			newsDAO.editNews(news);
		} catch (NewsDAOException e) {
			throw new NewsServiceException(e);
		}
		
	}
	
	@Override
	public void deleteNews(int id) throws NewsServiceException {
		try {
			newsDAO.deleteNews(id);
		} catch (NewsDAOException e) {
			throw new NewsServiceException(e);
		}		
	}
	
	@Override
	public void deleteNewses(String[] idNewses) throws NewsServiceException {
		try {
			newsDAO.deleteNewses(idNewses);
		} catch (NewsDAOException e) {
			throw new NewsServiceException(e);
		}
		
	}

	
	@Override
	public void addNews(News news) throws NewsServiceException {
		try {
			newsDAO.addNews(news);
		} catch (NewsDAOException e) {
			throw new NewsServiceException(e);
		}
		
	}

	

	
	

}
