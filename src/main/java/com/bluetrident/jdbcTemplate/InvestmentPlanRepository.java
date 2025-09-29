package com.bluetrident.jdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.bluetrident.dto.InvestmentPlanDTO;
import com.bluetrident.sql.InvestmentPlanSQL;

@Repository
public class InvestmentPlanRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<InvestmentPlanDTO> getAllPlans() {
		RowMapper<InvestmentPlanDTO> mapper = new RowMapper<InvestmentPlanDTO>() {
			@Override
			public InvestmentPlanDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
				InvestmentPlanDTO dto = new InvestmentPlanDTO();
				dto.setId(rs.getLong("id"));
				dto.setPlanName(rs.getString("plan_name"));
				dto.setPrice(rs.getDouble("price"));
				dto.setIdentifierCode(rs.getString("identifier_code"));
				dto.setDescription(rs.getString("description"));
				dto.setPlanPolicy(rs.getString("plan_details"));
				return dto;
			}
		};

		String sql = InvestmentPlanSQL.getAllPlansQuery();
		return jdbcTemplate.query(sql, mapper);
	}

}
