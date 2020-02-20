package com.kh.siistory.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WarningDto {

	private int warning_no;
	private int target_no;
	private int pusher_no;
	private int board_no;
	private String content;
	private String push_date;
	
	
	
	private String content1="";
	private String content2="";
	private String content3="";
	private String content4="";
	private String content5="";
	private String content6="";
	
		
}
