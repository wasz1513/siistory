package com.kh.siistory.vo;

import java.util.List;

import com.kh.siistory.entity.BoardDto;

import lombok.Data;

@Data
public class MyFriendsBoardReplyVO {
	private int member_no; 
	private String email, member_name, member_pw, member_joindate, member_outdate, member_state, last_login;
	
	private List<BoardDto> boardList;
}
