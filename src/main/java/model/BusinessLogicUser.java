package model;

public class BusinessLogicUser {

	public MatchUserDto executeSelectUserInfo(String inputUserId, String inputPassWord) {

		MatchUserDto dto = new MatchUserDto();
		UserInfoDao dao = new UserInfoDao();
		dto = dao.doMatch(inputUserId, inputPassWord);

		return dto;
	}

}
