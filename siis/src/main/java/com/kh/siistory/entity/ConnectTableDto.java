package com.kh.siistory.entity;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConnectTableDto {

	private LocalDate getDate = LocalDate.now();
	private int now_connect;
	private int today_connect_ea;
	private int today_connect_user;
	private int total_connect_ea;
	private int total_connect_user;
	private String DT = getDate.toString();
	
}
