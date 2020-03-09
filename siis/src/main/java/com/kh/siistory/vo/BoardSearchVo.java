package com.kh.siistory.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class BoardSearchVo {
	private String type, keyword,  board_content, board_wdate;
	private int start, finish, photo, board_state;
}
