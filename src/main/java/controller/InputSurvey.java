package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.MatchUserDto;

public class InputSurvey extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public InputSurvey() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		MatchUserDto userInfoOnSession = (MatchUserDto) session.getAttribute("LOGIN_INFO");

		if (userInfoOnSession != null) {
			String userName = userInfoOnSession.getUserName();
			int age = userInfoOnSession.getAge();
			int sex = userInfoOnSession.getSex();

			request.setAttribute("USER_NAME", userName);
			request.setAttribute("AGE", age);
			request.setAttribute("SEX", sex);
			RequestDispatcher dispatch = request.getRequestDispatcher("/WEB-INF/view/input.jsp");
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
