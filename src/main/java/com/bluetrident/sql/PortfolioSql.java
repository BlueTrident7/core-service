package com.bluetrident.sql;

public class PortfolioSql {

	public static String getPortfolioQuery(Long userId) {
		return "SELECT "
				+ "COALESCE(SUM(CASE WHEN PT.STATUS='SUCCESS' THEN PT.AMOUNT ELSE 0 END),0) AS total_invested, "
				+ "COALESCE(SUM(CASE WHEN PT.STATUS='SUCCESS' THEN PT.AMOUNT ELSE 0 END),0) AS invested_amount, "
				+ "COALESCE(SUM(CASE WHEN PT.STATUS='SUCCESS' THEN PT.AMOUNT ELSE 0 END) - "
				+ "SUM(CASE WHEN PT.STATUS='FAILED' THEN PT.AMOUNT ELSE 0 END),0) AS profit_amount, "
				+ "CASE WHEN SUM(PT.AMOUNT)=0 THEN 0 ELSE "
				+ "(SUM(CASE WHEN PT.STATUS='SUCCESS' THEN PT.AMOUNT ELSE 0 END) - "
				+ "SUM(CASE WHEN PT.STATUS='FAILED' THEN PT.AMOUNT ELSE 0 END))/SUM(PT.AMOUNT)*100 END AS profit_percentage, "
				+ "COALESCE(SUM(PT.AMOUNT),0) AS total_amount, "
				+ "COALESCE(SUM(CASE WHEN PT.STATUS='PENDING' THEN PT.AMOUNT ELSE 0 END),0) AS balance_amount, "
				+ "COALESCE(MAX(PT.CREATED_AT), CURRENT_TIMESTAMP) AS last_updated, " + "U.ID AS account_no "
				+ "FROM PAYMENT_TRANSACTIONS PT " + "JOIN USER_INVESTMENTS UI ON PT.INVESTMENT_ID = UI.ID "
				+ "JOIN USERS U ON UI.USER_ID = U.ID " + "WHERE U.ID = " + userId + " " + "GROUP BY U.ID";
	}

	public static String getPlansQuery() {
		return "SELECT id, name, rate, description, type FROM plans";
	}

	public static String getMonthlyChartsQuery(Long userId) {
		return "SELECT EXTRACT(MONTH FROM PT.CREATED_AT) AS month, "
				+ "SUM(CASE WHEN PT.STATUS='SUCCESS' THEN PT.AMOUNT ELSE 0 END) AS revenue, "
				+ "SUM(CASE WHEN PT.STATUS='SUCCESS' THEN PT.AMOUNT ELSE 0 END) AS cash_in, "
				+ "SUM(CASE WHEN PT.STATUS='FAILED' THEN PT.AMOUNT ELSE 0 END) AS cash_out "
				+ "FROM PAYMENT_TRANSACTIONS PT " + "JOIN USER_INVESTMENTS UI ON PT.INVESTMENT_ID = UI.ID "
				+ "WHERE UI.USER_ID = " + userId + " " + "GROUP BY EXTRACT(MONTH FROM PT.CREATED_AT) "
				+ "ORDER BY month";
	}
}
