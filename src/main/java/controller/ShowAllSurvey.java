package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.MatchUserDto;
import model.ShowAllSurveyBL;
import model.ShowAllSurveyDto;

public class ShowAllSurvey extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ShowAllSurvey() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");     //文字コードをUTF-8で設定

		HttpSession session = request.getSession();
		MatchUserDto userInfoOnSession = (MatchUserDto) session.getAttribute("LOGIN_INFO");
		
		if (userInfoOnSession != null) {
			
			ShowAllSurveyBL logic = new ShowAllSurveyBL();
			List<ShowAllSurveyDto> selectContentList = logic.executeSelectSurvey();
			request.setAttribute("SELECT_CONTENT", selectContentList);
			RequestDispatcher dispatch = request.getRequestDispatcher("/WEB-INF/view/show_all_survey.jsp");
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
