package by.htp.ex.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.ex.bean.User;
import by.htp.ex.controller.command.Command;
import by.htp.ex.service.UserService;
import by.htp.ex.service.exception.UserServiceException;
import by.htp.ex.service.provider.ServiceProvider;

public class DoRegister implements Command {

	private final UserService userService = ServiceProvider.getInstance().getUserService();

	private static final String USER_NAME = "userName";
	private static final String PASSWORD = "password";
	private static final String EMAIL = "email";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String userName = request.getParameter(USER_NAME);
		String password = request.getParameter(PASSWORD);
		String email = request.getParameter(EMAIL);

		User user = new User(userName, password, email);

		try {
			
			boolean isRegistered = userService.registration(user);
			
			if (isRegistered) {
				request.setAttribute("regStatus", "ok");
				request.getRequestDispatcher("WEB-INF/pages/tiles/registration-status.jsp").forward(request, response);
			} else {
				request.setAttribute("regStatus", "notOk");
				request.getRequestDispatcher("WEB-INF/pages/tiles/registration-status.jsp").forward(request, response);
			}

		} catch (UserServiceException e) {
			// put exception to the request
			request.setAttribute("exception", e);
			// also put url (to return user back in case of exception)
			String returnTo = "controller?command=go_to_registration_page";
			request.setAttribute("returnTo", returnTo);
			// forward to exception page
			request.getRequestDispatcher("WEB-INF/pages/tiles/registration-exception.jsp").forward(request, response);
		}

	}

}
