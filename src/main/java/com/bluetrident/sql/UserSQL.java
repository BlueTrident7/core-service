package com.bluetrident.sql;

public class UserSQL {

	public static String getUserProfileById(Long userId) {
		String sql = "SELECT u.id, u.full_name, u.user_name, u.email, u.role, " + " " + "u.phone_number, u.approvals "
				+ "FROM users u " + "WHERE u.id = '" + userId + "'";

		return sql;
	}
}