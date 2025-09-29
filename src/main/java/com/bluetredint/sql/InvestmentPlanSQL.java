package com.bluetredint.sql;

public class InvestmentPlanSQL {

	public static String getAllPlansQuery() {

		return "SELECT id, plan_name, price, identifier_code, description, plan_details " + "FROM plans ";
	}

}
