package by.htp.ex.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.ex.controller.command.Command;
import by.htp.ex.service.NewsService;
import by.htp.ex.service.exception.NewsServiceException;
import by.htp.ex.service.provider.ServiceProvider;

public class DeleteNewses implements Command{
	
	private final NewsService newsService = ServiceProvider.getInstance().getNewsService();
	
	private static final String ID_NEWSES = "idNews"; 

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String[] idNewses = request.getParameterValues(ID_NEWSES);
		
		try {
			newsService.deleteNewses(idNewses);
			response.sendRedirect("controller?command=go_to_news_list"); 
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
