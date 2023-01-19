package by.htp.ex.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.ex.bean.News;
import by.htp.ex.controller.command.Command;
import by.htp.ex.service.NewsService;
import by.htp.ex.service.exception.NewsServiceException;
import by.htp.ex.service.provider.ServiceProvider;

public class GoToViewNews implements Command {
	
	private final NewsService newsService = ServiceProvider.getInstance().getNewsService();
	
	private static final String NEWS_ID = "id";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		News news;	

		String id = request.getParameter(NEWS_ID);
		
		try {
			// url-attribute (to redirect when user change locale)
			String path = request.getContextPath() + "/controller?command=go_to_view_news&id=" + id;
			request.getSession(true).setAttribute("url", path);
			// fetch the news by id
			news  = newsService.fetchById(Integer.parseInt(id));
			// put it to the request
			request.setAttribute("news", news);
			// attribute that specify content block on baseLayout-page
			request.setAttribute("presentation", "viewNews");
			// forward the request
			request.getRequestDispatcher("WEB-INF/pages/layouts/baseLayout.jsp").forward(request, response);
		} catch (NewsServiceException e) {
			// put exception to the request
			request.setAttribute("exception", e);
			// also put url (to return user back in case of exception)
			String returnTo = "controller?command=go_to_news_list";
			request.setAttribute("returnTo", returnTo);
			// forward to exception page
			request.getRequestDispatcher("WEB-INF/pages/tiles/news-exception.jsp").forward(request, response);
		}
		
	}

}
