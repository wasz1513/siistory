package com.kh.siistory.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class WarningVo {
	private int start, finish, target_no, pusher_no, board_no, warning_no, w_count;
	private String target_email, target_name, pusher_email, pusher_name, content, state;
	private String target_keyword, pusher_keyword, content_keyword;
								
	private String target_type, pusher_type;
}
