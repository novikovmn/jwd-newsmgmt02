package by.htp.ex.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.ex.bean.News;
import by.htp.ex.controller.command.Command;
import by.htp.ex.service.NewsService;
import by.htp.ex.service.exception.NewsServiceException;
import by.htp.ex.service.provider.ServiceProvider;

public class SaveEditedNews implements Command{
	
	private final NewsService newsService = ServiceProvider.getInstance().getNewsService();
	
	private static final String ID_NEWS = "idNews";
	private static final String TITLE = "title";
	private static final String BRIEF_NEWS = "briefNews";
	private static final String CONTENT = "content";	

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (request.getSession() == null) { 
			response.addCookie(new Cookie("warning", "Your session has expired. Please, log in again."));
			response.sendRedirect(request.getContextPath() + "/WEB-INF/pages/tiles/session-timeout-page.jsp");
		}		
		
		int idNews = Integer.parseInt(request.getParameter(ID_NEWS));
		String title = request.getParameter(TITLE);
		String briefNews = request.getParameter(BRIEF_NEWS);
		String content = request.getParameter(CONTENT);
		
		News news = new News(idNews, title, briefNews, content); 
		
		try {
			newsService.editNews(news);
			response.sendRedirect("controller?command=go_to_news_list"); 
		} catch (NewsServiceException e) {		
			// put exception to the request
			request.setAttribute("exception", e);
			// also put url (to return user back in case of exception)
			String returnTo = "controller?command=go_to_edit_page&id=" + idNews;
			request.setAttribute("returnTo", returnTo);
			// forward to exception page
			request.getRequestDispatcher("WEB-INF/pages/tiles/news-exception.jsp").forward(request, response);			
		}
		
	}

}
