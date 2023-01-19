package by.htp.ex.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.htp.ex.controller.command.Command;

public class SwitchLocale implements Command{
	
	private static final String LOCALE = "locale";
	private static final String URL = "url";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String theLocale = request.getParameter(LOCALE);
		
		HttpSession session = request.getSession(true);
		session.setAttribute("theLocale", theLocale);		
		
		String redirectTo = (String)session.getAttribute(URL);
		
		response.sendRedirect(redirectTo);
		
		
	}

}
