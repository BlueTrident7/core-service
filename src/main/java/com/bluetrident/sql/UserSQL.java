package com.bluetrident.sql;

public class UserSQL {

	public static String getUserProfileById(Long userId) {
		return "SELECT u.id, u.full_name, u.user_name, u.email, u.role, "
				+ "c.category_name, c.identifier_code, "
				+ "u.phone_number, u.approvals " + "FROM users u " 
				+ "WHERE u.id = '" + userId + "'";
	}
}