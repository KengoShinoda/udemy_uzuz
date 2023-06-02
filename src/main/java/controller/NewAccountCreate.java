package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.BusinessLogicUser;
import model.MatchUserDto;
import model.NewAccountCreateDto;
import model.NewAccountLogic;

public class NewAccountCreate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public NewAccountCreate() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8"); //文字コードをUTF-8で設定
		request.setCharacterEncoding("utf-8"); //文字コードをUTF-8で設定

		HttpSession session = request.getSession();

		String id = request.getParameter("ID");
		String name = request.getParameter("NAME");
		String age_txt = request.getParameter("AGE");
		int sex = Integer.parseInt(request.getParameter("SEX"));
		String pass = request.getParameter("PASSWORD");

		boolean succesInsert = true;

		if (name == null || name.matches(".*\\s.*") || age_txt == "" || Integer.parseInt(age_txt) < 0 ||
				sex > 2 && sex < 1) {
			succesInsert = false;
		} else {

			int age = Integer.parseInt(request.getParameter("AGE"));
			NewAccountCreateDto dto = new NewAccountCreateDto();

			dto.setId(id);
			dto.setUserName(name);
			dto.setAge(age);
			dto.setSex(sex);
			dto.setPass(pass);

			NewAccountLogic logic = new NewAccountLogic();

			succesInsert = logic.NewAccountCreate(dto);
		}
		if (succesInsert) {

			BusinessLogicUser logic = new BusinessLogicUser();
			MatchUserDto mDto = logic.executeSelectUserInfo(id, pass);
			if (mDto.getUserId() != null) {
				session.setAttribute("LOGIN_INFO", mDto);
				response.sendRedirect("InputSurvey");
			} else {
				response.sendRedirect("Login");
			}
		} else {
			response.sendRedirect("htmls/noncreate.html");
		}
	}
}
