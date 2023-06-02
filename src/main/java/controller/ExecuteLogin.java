package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.BusinessLogicUser;
import model.MatchUserDto;

public class ExecuteLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ExecuteLogin() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		MatchUserDto userInfoOnSession = (MatchUserDto) session.getAttribute("LOGIN_INFO");

		if (userInfoOnSession != null) {
			response.sendRedirect("InputSurvey");

		} else {

			String userId = request.getParameter("USER_ID");
			String passWord = request.getParameter("PASSWORD");
			BusinessLogicUser logic = new BusinessLogicUser();
			MatchUserDto dto = logic.executeSelectUserInfo(userId, passWord);

			if (dto.getUserId() != null) {

				session.setAttribute("LOGIN_INFO", dto);
				response.sendRedirect("InputSurvey");

			} else {

				response.sendRedirect("Login");

			}
		}
	}
}
