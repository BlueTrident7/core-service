package com.bluetrident.jdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.bluetrident.dto.TransactionsDTO;
import com.bluetrident.sql.PaymentSql;

@Repository
public class PaymentRepositoy {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<TransactionsDTO> getAllTransactionList(Long userId) {

		RowMapper<TransactionsDTO> mapper = new RowMapper<TransactionsDTO>() {
			@Override
			public TransactionsDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
				TransactionsDTO dto = new TransactionsDTO();
				dto.setId(rs.getLong("id"));
				dto.setType(rs.getString("type"));
				dto.setTransactionNumber(rs.getString("transaction_number"));
				dto.setDate(rs.getDate("transaction_date").toLocalDate());
				dto.setAmount(rs.getDouble("amount"));
				dto.setStatus(rs.getString("status"));
				return dto;
			}
		};

		String sql = PaymentSql.getAllTransactionList(userId);
		return jdbcTemplate.query(sql, mapper);
	}

}
