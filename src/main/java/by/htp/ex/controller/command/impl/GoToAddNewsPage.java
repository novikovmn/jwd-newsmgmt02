package by.htp.ex.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.ex.controller.command.Command;

public class GoToAddNewsPage implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// put url-attribute to the session (to redirect when user change locale)
		String path = request.getContextPath() + "/controller?command=go_to_add_news_page";
		request.getSession(true).setAttribute("url", path);
		// special attribute that specify content block on baseLayout-page
		request.setAttribute("presentation", "addNews");
		// forward the request
		request.getRequestDispatcher("/WEB-INF/pages/layouts/baseLayout.jsp").forward(request, response);
		
	}

}
