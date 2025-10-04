package com.bluetrident.jdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.bluetrident.dto.Charts;
import com.bluetrident.dto.Plan;
import com.bluetrident.dto.Portfolio;
import com.bluetrident.dto.PortfolioResponse;
import com.bluetrident.sql.PortfolioSql;

@Repository
public class PortfolioRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public PortfolioResponse getPortfolioData(Long userId) {

		PortfolioResponse response = new PortfolioResponse();

		// ================= Portfolio =================
		RowMapper<Portfolio> portfolioMapper = new RowMapper<Portfolio>() {
			@Override
			public Portfolio mapRow(ResultSet rs, int rowNum) throws SQLException {
				Portfolio p = new Portfolio();
				p.setTotalInvested(rs.getDouble("total_invested"));
				p.setProfitPercentage(rs.getDouble("profit_percentage"));
				p.setProfitAmount(rs.getDouble("profit_amount"));
				p.setTotalAmount(rs.getDouble("total_amount"));
				p.setBalanceAmount(rs.getDouble("balance_amount"));
				p.setLastUpdated(rs.getString("last_updated"));
				p.setAccountNo(rs.getString("account_no"));
				return p;
			}
		};

		String portfolioSql = PortfolioSql.getPortfolioQuery(userId);
		Portfolio portfolio = jdbcTemplate.queryForObject(portfolioSql, portfolioMapper);
		response.setPortfolio(portfolio);

		// ================= Charts (Dynamic) =================
		RowMapper<Charts> chartsMapper = new RowMapper<Charts>() {
			@Override
			public Charts mapRow(ResultSet rs, int rowNum) throws SQLException {
				Charts chart = new Charts();
				int monthNumber = rs.getInt("month");
				chart.setMonths(Arrays.asList(getMonthName(monthNumber)));
				chart.setMonthlyRevenue(Arrays.asList(rs.getDouble("revenue")));
				chart.setCashIn(Arrays.asList(rs.getDouble("cash_in")));
				chart.setCashOut(Arrays.asList(rs.getDouble("cash_out")));
				return chart;
			}
		};

		List<Charts> chartList = jdbcTemplate.query(PortfolioSql.getMonthlyChartsQuery(userId), chartsMapper);

		// Combine months and values into single Charts object
		Charts charts = new Charts();
		charts.setMonths(chartList.stream().flatMap(c -> c.getMonths().stream()).toList());
		charts.setMonthlyRevenue(chartList.stream().flatMap(c -> c.getMonthlyRevenue().stream()).toList());
		charts.setCashIn(chartList.stream().flatMap(c -> c.getCashIn().stream()).toList());
		charts.setCashOut(chartList.stream().flatMap(c -> c.getCashOut().stream()).toList());
		response.setCharts(charts);

		// ================= Plans =================
		RowMapper<Plan> planMapper = new RowMapper<Plan>() {
			@Override
			public Plan mapRow(ResultSet rs, int rowNum) throws SQLException {
				Plan plan = new Plan();
				plan.setId(rs.getLong("id"));
				plan.setName(rs.getString("name"));
				plan.setRate(rs.getDouble("rate"));
				plan.setDescription(rs.getString("description"));
				plan.setType(rs.getString("type"));
				return plan;
			}
		};

		String planSql = PortfolioSql.getPlansQuery();
		List<Plan> plans = jdbcTemplate.query(planSql, planMapper);
		response.setPlans(plans);

		return response;
	}

	// Helper method to convert month number to month name
	private String getMonthName(int monthNumber) {
		return switch (monthNumber) {
		case 1 -> "Jan";
		case 2 -> "Feb";
		case 3 -> "Mar";
		case 4 -> "Apr";
		case 5 -> "May";
		case 6 -> "Jun";
		case 7 -> "Jul";
		case 8 -> "Aug";
		case 9 -> "Sep";
		case 10 -> "Oct";
		case 11 -> "Nov";
		case 12 -> "Dec";
		default -> "";
		};
	}
}
