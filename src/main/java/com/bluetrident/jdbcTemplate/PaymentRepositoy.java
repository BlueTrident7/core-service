package com.bluetrident.jdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.bluetrident.dto.PaymentTransactionDTO;
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

	public List<PaymentTransactionDTO> getAllTransactionsByUserId(Long userId) {

		RowMapper<PaymentTransactionDTO> mapper = new RowMapper<PaymentTransactionDTO>() {

			@Override
			public PaymentTransactionDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
				PaymentTransactionDTO dto = new PaymentTransactionDTO();
				dto.setTransactionId(rs.getLong("TRANSACTION_ID"));
				dto.setExternalTransactionId(rs.getString("EXTERNAL_TRANSACTION_ID"));
				dto.setAmount(rs.getDouble("AMOUNT"));
				dto.setTransactionStatus(rs.getString("TRANSACTION_STATUS"));
				dto.setPaymentMethod(rs.getString("PAYMENT_METHOD"));
				dto.setGatewayResponse(rs.getString("GATEWAY_RESPONSE"));
				dto.setRemarks(rs.getString("REMARKS"));

				dto.setCreatedAt(rs.getDate("CREATED_AT"));
				dto.setPaymentId(rs.getLong("PAYMENT_ID"));
				dto.setOrderId(rs.getString("ORDER_ID"));
				dto.setPaymentStatus(rs.getString("PAYMENT_STATUS"));
				dto.setErrorDescription(rs.getString("ERROR_DESCRIPTION"));
				dto.setInvestmentId(rs.getLong("INVESTMENT_ID"));
				dto.setInvestmentStatus(rs.getString("INVESTMENT_STATUS"));
				dto.setUserId(rs.getLong("USER_ID"));
				dto.setFullName(rs.getString("FULL_NAME"));
				dto.setEmail(rs.getString("EMAIL"));
				dto.setTransactionType(rs.getString("TYPE"));

				return dto;
			}
		};

		List<PaymentTransactionDTO> paymentTransactionDTO = jdbcTemplate
				.query(PaymentSql.getAllTransactionsByUserId(userId), mapper);

		return paymentTransactionDTO;
	}

}
