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

public class GoToNewsList implements Command {
	
	private final NewsService newsService = ServiceProvider.getInstance().getNewsService();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		
		List<News> newsList;
		try {
			// url-attribute (to redirect when user change locale)
			String path = request.getContextPath() + "/controller?command=go_to_news_list";
			request.getSession(true).setAttribute("url", path);
			// get all newses from db
			newsList = newsService.getList();
			// put them to the request
			request.setAttribute("news", newsList);
			// attribute that specify content block on baseLayout-page
			request.setAttribute("presentation", "newsList");
			// forward the request
			request.getRequestDispatcher("WEB-INF/pages/layouts/baseLayout.jsp").forward(request, response);
		} catch (NewsServiceException e) {
			// put exception to the request 
			request.setAttribute("exception", e);
			// "mark" - if not work command "go_to_news_list"
			request.setAttribute("goToNewsList", "ok");
			// forward to exception page
			request.getRequestDispatcher("WEB-INF/pages/tiles/news-exception.jsp").forward(request, response);
		}
		
	}

}
