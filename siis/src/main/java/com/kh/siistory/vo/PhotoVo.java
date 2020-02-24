package com.kh.siistory.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PhotoVo {
	int board_pic_no;
	String board_pic_store;
	long board_pic_size;
	int main;
	String member_no;
}
