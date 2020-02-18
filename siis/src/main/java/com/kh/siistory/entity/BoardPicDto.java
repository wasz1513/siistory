package com.kh.siistory.entity;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardPicDto {
	int board_pic_no, board_no, board_pic_size;
	String board_pic_store;
	MultipartFile file;
}
