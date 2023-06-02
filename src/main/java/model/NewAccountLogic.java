package model;
public class NewAccountLogic {

	public boolean NewAccountCreate(NewAccountCreateDto dto) {

		boolean   succesInsert = false ;
		SurveyDao dao = new SurveyDao();
		succesInsert = dao.doCreate(dto);

		return succesInsert;
	}

}
