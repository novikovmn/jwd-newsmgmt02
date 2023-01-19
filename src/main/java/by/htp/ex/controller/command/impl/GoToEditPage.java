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

public class GoToEditPage implements Command {
	
	private final NewsService newsService = ServiceProvider.getInstance().getNewsService();

	private final String ID = "id";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		News news = null;
		
		int idNews = Integer.parseInt(request.getParameter(ID));
		
		try {
			// put url-attribute (to redirect when user change locale)
			String path = request.getContextPath() + "/controller?command=go_to_edit_page&id=" + idNews;
			request.getSession(true).setAttribute("url", path);
			// fetch the news by id from db
			news = newsService.fetchById(idNews);
			// put the news to request attribute
			request.setAttribute("news", news);
			// attribute that specify content block on baseLayout-page
			request.setAttribute("presentation", "editNews");
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
