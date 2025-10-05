package com.bluetrident.sql;

public class PaymentSql {

	public static String getAllTransactionList(Long userId) {
		String sql = "SELECT id, type, transaction_number, transaction_date, amount, status " + "FROM transactions "
				+ "WHERE user_id = " + userId + " " + "ORDER BY transaction_date DESC";

		return sql;
	}

	public static String getAllTransactionsByUserId(Long userId) {
		String sql = "SELECT " + "PT.ID AS TRANSACTION_ID, P.TYPE," + "PT.TRANSACTION_ID AS EXTERNAL_TRANSACTION_ID, "
				+ "PT.AMOUNT, " + "PT.STATUS AS TRANSACTION_STATUS, " + "PT.PAYMENT_METHOD, " + "PT.GATEWAY_RESPONSE, "
				+ "PT.REMARKS, " + "PT.CREATED_AT, " + "P.ID AS PAYMENT_ID, " + "P.ORDER_ID, "
				+ "P.STATUS AS PAYMENT_STATUS, " + "P.ERROR_DESCRIPTION, " + "UI.ID AS INVESTMENT_ID, "
				+ "UI.STATUS AS INVESTMENT_STATUS, " + "U.ID AS USER_ID, " + "U.FULL_NAME, " + "U.EMAIL "
				+ "FROM PAYMENTS P " + "LEFT JOIN PAYMENT_TRANSACTIONS PT ON PT.PAYMENT_ID = P.ID "
				+ "LEFT JOIN USER_INVESTMENTS UI ON P.INVESTMENT_ID = UI.ID " + "LEFT JOIN USERS U ON P.USER_ID = U.ID "
				+ "WHERE U.ID = " + userId + "\r\n" + "ORDER BY PT.CREATED_AT DESC";

		return sql;
	}
}
