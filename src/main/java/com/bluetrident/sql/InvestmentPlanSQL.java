package com.bluetrident.sql;

public class InvestmentPlanSQL {

	public static String getAllPlansQuery() {

		return "SELECT id, plan_name, price,plan_type, identifier_code, description, plan_details "
				+ "FROM investment_plans ";
	}

}
