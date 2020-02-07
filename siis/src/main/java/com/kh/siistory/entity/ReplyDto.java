package com.kh.siistory.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReplyDto {
	int reply_no, board_no, writer_no, super_no, group_no, depth, reply_count;
	String reply_wdate, reply_content, reply_writer;
}
