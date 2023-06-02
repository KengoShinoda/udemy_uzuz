package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.MatchUserDto;

public class ExecuteLogout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ExecuteLogout() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");

		HttpSession session = request.getSession();
		MatchUserDto userInfoOnSession = (MatchUserDto) session.getAttribute("LOGIN_INFO");

		if (userInfoOnSession != null) {

			session.invalidate();
			RequestDispatcher dispatch = request.getRequestDispatcher("/WEB-INF/view/logout.jsp");
			dispatch.forward(request, response);

		} else {
			response.sendRedirect("Login");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
