package by.htp.ex.controller.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.ex.bean.News;
import by.htp.ex.controller.command.Command;
import by.htp.ex.service.NewsService;
import by.htp.ex.service.exception.NewsServiceException;
import by.htp.ex.service.provider.ServiceProvider;

public class GoToBasePage implements Command{
	
	private final NewsService newsService = ServiceProvider.getInstance().getNewsService();
	
	private static final int NUMBER_OF_LATEST_NEWS = 5;

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<News> latestNews;
		
		try {
			// put url-attribute into the session to redirect to it when user change locale
			String path = request.getContextPath() + "/controller?command=go_to_base_page";
			request.getSession(true).setAttribute("url", path);
			// get latest news from db and put them to the request
			latestNews = newsService.getLatestsList(NUMBER_OF_LATEST_NEWS);
			request.setAttribute("news", latestNews);
			// forward this request
			request.getRequestDispatcher("WEB-INF/pages/layouts/baseLayout.jsp").forward(request, response);
		} catch (NewsServiceException e) {
			// put exception to the request
			request.setAttribute("exception", e);
			// this parameter is a "mark" (means that this exception happend while we turn to the welcome page 
			// - in other words - the first opening of application)
			request.setAttribute("exceptionFromWelcomePage", "ok");
			request.getRequestDispatcher("WEB-INF/pages/tiles/news-exception.jsp").forward(request, response);
		}
		
		
	}

}
