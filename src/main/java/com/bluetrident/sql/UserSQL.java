package com.bluetrident.sql;

public class UserSQL {

	public static String getUserProfileById(Long userId) {
		return "SELECT u.id, u.full_name, u.user_name, u.email, u.role, "
				+ "c.category_name, c.identifier_code, c.description AS category_description, "
				+ "u.phone_number, u.approvals " + "FROM users u " + "LEFT JOIN categories c ON u.category_id = c.id "
				+ "WHERE u.id = '" + userId + "'";
	}
}