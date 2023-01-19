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

public class AddNews implements Command{
	
	private final NewsService newsService = ServiceProvider.getInstance().getNewsService();
	
	private static final String TITLE = "title";
	private static final String BRIEF = "briefNews";
	private static final String CONTENT = "content";
	private static final String USER_ID = "userId";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (request.getSession() == null) { 
			response.addCookie(new Cookie("warning", "Your session has expired. Please, log in again."));
			response.sendRedirect(request.getContextPath() + "/WEB-INF/pages/tiles/session-timeout-page.jsp");
		}
				
		String title = request.getParameter(TITLE);
		String brief = request.getParameter(BRIEF);
		String content = request.getParameter(CONTENT);
		
		Integer userId = (Integer)request.getSession().getAttribute(USER_ID);		
		
		News news = new News(title, brief, content, userId);
		
		try {
			newsService.addNews(news);
			response.sendRedirect("controller?command=go_to_news_list"); 
		} catch (NewsServiceException e) {
			// put into the request exception
			request.setAttribute("exception", e);
			String returnTo = "controller?command=go_to_add_news_page";
			// also put the url (we need to redirect user in case exception) 
			request.setAttribute("returnTo", returnTo);
			// forward to exception page
			request.getRequestDispatcher("WEB-INF/pages/tiles/news-exception.jsp").forward(request, response);
		}
		
		
	}

}
