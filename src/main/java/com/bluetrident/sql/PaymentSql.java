package com.bluetrident.sql;

public class PaymentSql {

	public static String getAllTransactionList(Long userId) {
		String sql = "SELECT id, type, transaction_number, transaction_date, amount, status " + "FROM transactions "
				+ "WHERE user_id = " + userId + " " + "ORDER BY transaction_date DESC";

		return sql;
	}
}
