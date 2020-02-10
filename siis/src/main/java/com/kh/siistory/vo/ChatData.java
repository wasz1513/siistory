package com.kh.siistory.vo;

import com.kh.siistory.entity.FriendDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatData {

	private int member_no;
	private String text;
	private int status;
	
	int target_no;
	int pusher_no;
	int content_no;
	String content_type;
	String content_date;
	String content_play;	
	
}
