package com.bluetrident.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StatsDTO {

	private int totalUsers;
	private int activeSessions;
	private int totalTransactions;
}
