package by.htp.ex.service;

import java.util.List;

import by.htp.ex.bean.News;
import by.htp.ex.service.exception.NewsServiceException;

public interface NewsService {  
  
  List<News> getLatestsList(int count)  throws NewsServiceException;
  List<News> getList()  throws NewsServiceException;
  News fetchById(int id) throws NewsServiceException;
  void editNews(News news) throws NewsServiceException;
  void deleteNews(int id) throws NewsServiceException;
  void deleteNewses(String[] idNewses) throws NewsServiceException;
  void addNews(News news) throws NewsServiceException;
  
 
  
}
