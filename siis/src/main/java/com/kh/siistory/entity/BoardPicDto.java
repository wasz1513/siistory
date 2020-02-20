package com.kh.siistory.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardPicDto {
	int board_pic_no;
	String board_pic_store;
	long board_pic_size;
}
