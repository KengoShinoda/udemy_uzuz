package controller;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.MatchUserDto;
import model.SaveSurveyBL;
import model.SaveSurveyDto;

public class SaveSurvey extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SaveSurvey() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");

		HttpSession session = request.getSession();
		MatchUserDto userInfoOnSession = (MatchUserDto) session.getAttribute("LOGIN_INFO");

		if (userInfoOnSession != null) {

			String name = request.getParameter("NAME");
			//String age_txt = request.getParameter("AGE");
			int sex = Integer.parseInt(request.getParameter("SEX"));
			int satisfactionLevel = Integer.parseInt(request.getParameter("SATISFACTION_LEVEL"));
			String message = request.getParameter("MESSAGE");

			boolean succesInsert = false;

			succesInsert = false;
			if (name == null || name.matches(".*\\s.*") ||
					satisfactionLevel > 5 && satisfactionLevel < 1 ||
					message == "" || message == "") {
				succesInsert = false;
			} else {

				int age = Integer.parseInt(request.getParameter("AGE"));

				SaveSurveyDto dto = new SaveSurveyDto();
				dto.setName(name);
				dto.setAge(age);
				dto.setSex(sex);
				dto.setSatisfactionLevel(satisfactionLevel);
				dto.setMessage(message);
				dto.setTime(new Timestamp(System.currentTimeMillis()));

				SaveSurveyBL logic = new SaveSurveyBL();
				succesInsert = logic.executeInsertSurvey(dto);
			}
			if (succesInsert) {
				response.sendRedirect("htmls/finish.html");

			} else {
				response.sendRedirect("htmls/error.html");
			}
		} else {
			response.sendRedirect("Login");
		}
	}
}
