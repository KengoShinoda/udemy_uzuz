package model;

import java.util.List;

public class ShowAllSurveyBL {

	public List<ShowAllSurveyDto> executeSelectSurvey() {

		SurveyDao dao = new SurveyDao();
		List<ShowAllSurveyDto> selectContentList = dao.doSelectList();

		return selectContentList;
	}

}
