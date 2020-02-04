package com.kh.siistory.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardDto {
	private int board_no, member_no, board_like_count, board_read_count, board_reply_count, board_state;
	private String board_content, board_wdate;
	private List<ReplyDto> replylist;
}
