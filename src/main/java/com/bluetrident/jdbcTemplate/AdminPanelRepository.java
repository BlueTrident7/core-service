package com.bluetrident.jdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.bluetrident.dto.SettingsDTO;
import com.bluetrident.dto.StatsDTO;
import com.bluetrident.dto.UserDTO;
import com.bluetrident.sql.UserSQL;

@Repository
public class AdminPanelRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public StatsDTO getStats() {
		RowMapper<StatsDTO> mapper = (rs, rowNum) -> {
			StatsDTO dto = new StatsDTO();
			dto.setTotalUsers(rs.getInt("total_users"));
			dto.setActiveSessions(rs.getInt("active_USERS"));
			dto.setTotalTransactions(rs.getInt("total_transactions"));
			return dto;
		};

		return jdbcTemplate.queryForObject(UserSQL.getStats(), mapper);
	}

	public List<UserDTO> getUsers() {
		RowMapper<UserDTO> mapper = new RowMapper<UserDTO>() {
			@Override
			public UserDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
				UserDTO dto = new UserDTO();
				dto.setId(rs.getLong("id"));
				dto.setName(rs.getString("FULL_NAME"));
				dto.setEmail(rs.getString("email"));
				dto.setRole(rs.getString("role"));
				return dto;
			}
		};

		return jdbcTemplate.query(UserSQL.getUsers(), mapper);
	}

	public SettingsDTO getSettings() {
		RowMapper<SettingsDTO> mapper = (rs, rowNum) -> {
			SettingsDTO dto = new SettingsDTO();
			dto.setNotifications(rs.getBoolean("notifications"));
			dto.setTwoFactor(rs.getBoolean("two_factor"));
			dto.setTheme(rs.getString("theme"));
			return dto;
		};

		return jdbcTemplate.queryForObject(UserSQL.getSettings(), mapper);
	}
}
