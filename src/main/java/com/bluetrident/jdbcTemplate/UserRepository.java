package com.bluetrident.jdbcTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.bluetrident.dto.UserProfileDTO;
import com.bluetrident.sql.UserSQL;

@Repository
public class UserRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public UserProfileDTO getUserProfileById(Long id) {
		RowMapper<UserProfileDTO> mapper = (rs, rowNum) -> {
			UserProfileDTO dto = new UserProfileDTO();
			dto.setId(rs.getLong("id"));
			dto.setFullName(rs.getString("full_name"));
			dto.setUserName(rs.getString("user_name"));
			dto.setEmail(rs.getString("email"));
			dto.setRole(rs.getString("role"));
			dto.setPhoneNumber(rs.getString("phone_number"));
			dto.setApprovals(rs.getBoolean("approvals"));
			return dto;
		};

		String sql = UserSQL.getUserProfileById(id);
		return jdbcTemplate.queryForObject(sql, mapper);
	}
}
