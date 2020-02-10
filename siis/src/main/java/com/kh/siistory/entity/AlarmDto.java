package com.kh.siistory.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AlarmDto {

	int target_no;
	int pusher_no;
	int content_no;
	String content_type;
	String content_date;
	String content_play;
	
	
}
