package by.htp.ex.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.ex.controller.command.Command;
import by.htp.ex.controller.command.provider.CommandProvider;

public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final CommandProvider commandProvider = new CommandProvider();

	public FrontController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doAction(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doAction(request, response);
	}

	
	private static final String COMMAND = "command";
	// processing request and response
	private void doAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String commandName = request.getParameter(COMMAND);
			Command command = commandProvider.getCommand(commandName);
			command.execute(request, response);
		} catch (Exception e) {
			String eName = e.getClass().getSimpleName();
			request.setAttribute("message", "Sorry. Some technical problems(" + eName + "). Please, try again.");
			request.getRequestDispatcher("WEB-INF/pages/tiles/front-controller-exception.jsp").forward(request,
					response);

		}
	}

}
