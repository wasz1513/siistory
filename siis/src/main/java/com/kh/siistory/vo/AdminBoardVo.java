package com.kh.siistory.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class AdminBoardVo {
	String board_content, board_wdate, member_no, board_state, board_writer, email;
	int board_no, photo;
}
