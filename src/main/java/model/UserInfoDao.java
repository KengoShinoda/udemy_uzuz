package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserInfoDao {

	String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
	String JDBC_URL = "jdbc:mysql://localhost/test_db?characterEncoding=UTF-8&serverTimezone=JST&useSSL=false";
	String USER_ID = "test_user";
	String USER_PASS = "test_pass";

	public MatchUserDto doMatch(String inputUserId, String inputPassWord) {

		try {
			Class.forName(DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		MatchUserDto dto = new MatchUserDto();

		try {

			con = DriverManager.getConnection(JDBC_URL, USER_ID, USER_PASS);

			StringBuffer buf = new StringBuffer();
			buf.append("  SELECT                    ");
			buf.append("     USER_ID  ,             ");
			buf.append("     USER_NAME ,            ");
			buf.append("     PASSWORD ,             ");
			buf.append("      AGE,                  ");
			buf.append("      SEX                   ");
			buf.append("  FROM                      ");
			buf.append("   user_info                ");
			buf.append("  WHERE                     ");
			buf.append("   USER_ID   = ? AND        ");
			buf.append("   PASSWORD = ?             ");

			ps = con.prepareStatement(buf.toString());

			ps.setString(1, inputUserId);
			ps.setString(2, inputPassWord);

			rs = ps.executeQuery();

			if (rs.next()) {
				dto.setUserId(rs.getString("USER_ID"));
				dto.setUserName(rs.getString("USER_NAME"));
				dto.setPassWord(rs.getString("PASSWORD"));
				dto.setAge(rs.getInt("AGE"));
				dto.setSex(rs.getInt("SEX"));
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

		return dto;
	}
}
