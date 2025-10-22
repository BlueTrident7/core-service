package com.bluetrident.sql;

public class UserSQL {

	public static String getUserProfileById(Long userId) {
		String sql = "SELECT \r\n"
				+ "    u.id,\r\n"
				+ "    u.full_name,\r\n"
				+ "    u.username,\r\n"
				+ "    u.email,\r\n"
				+ "    u.role,\r\n"
				+ "    u.phone_number,\r\n"
				+ "    u.approvals,\r\n"
				+ "	sm.name_en as gender,u.age \r\n"
				+ "FROM users u\r\n"
				+ "left join system_master sm on sm.id = u.gender_id \r\n"
			+ " WHERE u.id = '" + userId + "'";

		return sql;
	}

	public static String getStats() {
		String sql = "SELECT " + " (SELECT COUNT(*) FROM users) AS total_users, "
				+ " (SELECT COUNT(*) FROM users) AS active_USERS, "
				+ " (SELECT COUNT(*) FROM payment_transactions) AS total_transactions";
		return sql;
	}

	public static String getUsers() {
		String sql = "SELECT\r\n" + "	U.ID,\r\n" + "	U.FULL_NAME,\r\n" + "	U.USER_NAME,\r\n" + "	U.EMAIL,\r\n"
				+ "	U.ROLE,\r\n" + "	U.PHONE_NUMBER,\r\n" + "	U.APPROVALS\r\n" + "FROM\r\n" + "	USERS U\r\n";
//				+ "WHERE\r\n" + "	U.ID = '1'";
		return sql;
	}

	public static String getSettings() {
		String sql = "SELECT notifications, two_factor, theme FROM settings LIMIT 1";
		return sql;
	}
}