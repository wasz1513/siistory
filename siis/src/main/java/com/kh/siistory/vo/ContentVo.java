package com.kh.siistory.vo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContentVo {
	private String board_content;
	private int board_no;
	private List<Integer> board_pic_no;
}
