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
public class MessengerData {

	private int member_no;
	private int status;
	int target_no;
	int pusher_no;
	int content_no;
	String content_type;
	String content_play;	
	private String text;
	
	String content_date;
	int room_no;
	
}
