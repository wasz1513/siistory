package com.kh.siistory.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlarmData {

	private int pusher_no;
	private int target_no;
	private int content_no;
	private String content_type;
	private String content_play;
	private String text;
	private int status;
	
	
}
