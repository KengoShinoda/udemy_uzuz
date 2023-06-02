package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SurveyDao {

	String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
	String JDBC_URL = "jdbc:mysql://localhost/test_db?characterEncoding=UTF-8&serverTimezone=JST&useSSL=false";
	String USER_ID = "test_user";
	String USER_PASS = "test_pass";

	public boolean doInsert(SaveSurveyDto dto) {

		try {
			Class.forName(DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection con = null;
		PreparedStatement ps = null;
		boolean isSuccess = true;

		try {

			con = DriverManager.getConnection(JDBC_URL, USER_ID, USER_PASS);

			con.setAutoCommit(false);
			StringBuffer buf = new StringBuffer();
			buf.append(" INSERT INTO user_survey2 ( ");
			buf.append("  USER_NAME,                ");
			buf.append("  SATISFACTION_LEVEL,       ");
			buf.append("  MESSAGE,                  ");
			buf.append("  TIME                      ");
			buf.append(" ) VALUES (                 ");
			buf.append("  ? ,                        ");
			buf.append("  ? ,                        ");
			buf.append("  ? ,                        ");
			buf.append("  ?                         ");
			buf.append(" )                          ");

			ps = con.prepareStatement(buf.toString());

			ps.setString(1, dto.getName());
			ps.setInt(2, dto.getSatisfactionLevel());
			ps.setString(3, dto.getMessage());
			ps.setTimestamp(4, dto.getTime());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();

			isSuccess = false;

		} finally {
			if (isSuccess) {
				try {
					con.commit();
				} catch (SQLException e) {
					e.printStackTrace();
				}

			} else {
				try {
					con.rollback();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
		return isSuccess;
	}

	public List<ShowAllSurveyDto> doSelectList() {

		try {
			Class.forName(DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<ShowAllSurveyDto> dtoList = new ArrayList<ShowAllSurveyDto>();

		try {
			con = DriverManager.getConnection(JDBC_URL, USER_ID, USER_PASS);
			StringBuffer buf = new StringBuffer();

			buf.append("  SELECT                                            ");
			buf.append("      ui.USER_NAME AS name ,                        ");
			buf.append("      ui.AGE AS age ,                               ");
			buf.append("      ui.SEX AS sex ,                               ");
			buf.append("      us.SATISFACTION_LEVEL AS satisfaction_level , ");
			buf.append("      us.MESSAGE AS message ,                       ");
			buf.append("      us.TIME AS time                               ");
			buf.append("  FROM                                              ");
			buf.append("  user_info ui,                                     ");
			buf.append("  user_survey2 us                                   ");
			buf.append("  WHERE                                             ");
			buf.append("  ui.USER_NAME = us.USER_NAME                       ");
			buf.append("  ORDER BY                                          ");
			buf.append("  ui.USER_NAME ,                                    ");
			buf.append("  us.TIME                                           ");

			ps = con.prepareStatement(buf.toString());
			rs = ps.executeQuery();

			while (rs.next()) {
				ShowAllSurveyDto dto = new ShowAllSurveyDto();
				dto.setName(rs.getString("name"));
				dto.setAge(rs.getInt("age"));
				dto.setSex(rs.getInt("sex"));
				dto.setSatisfactionLevel(rs.getInt("satisfaction_level"));
				dto.setMessage(rs.getString("message"));
				dto.setTime(rs.getTimestamp("time"));
				dtoList.add(dto);
			}

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return dtoList;
	}

	public boolean doCreate(NewAccountCreateDto dto) {
		try {
			Class.forName(DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection con = null;
		PreparedStatement ps = null;

		boolean isSuccess = true;

		try {
			con = DriverManager.getConnection(JDBC_URL, USER_ID, USER_PASS);

			con.setAutoCommit(false);

			StringBuffer buf = new StringBuffer();
			buf.append("INSERT INTO USER_INFO (  ");
			buf.append("  USER_ID,            ");
			buf.append("  USER_NAME,          ");
			buf.append("  PASSWORD,           ");
			buf.append("  AGE,                ");
			buf.append("  SEX                 ");
			buf.append(") VALUES (            ");
			buf.append("  ?,                  ");
			buf.append("  ?,                  ");
			buf.append("  ?,                  ");
			buf.append("  ?,                  ");
			buf.append("  ?                   ");
			buf.append(")                     ");

			ps = con.prepareStatement(buf.toString());

			ps.setString(1, dto.getId());
			ps.setString(2, dto.getUserName());
			ps.setString(3, dto.getPass());
			ps.setInt(4, dto.getAge());
			ps.setInt(5, dto.getSex());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();

			isSuccess = false;

		} finally {
			if (isSuccess) {
				try {
					con.commit();
				} catch (SQLException e) {
					e.printStackTrace();
				}

			} else {
				try {
					con.rollback();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
		return isSuccess;
	}
}
