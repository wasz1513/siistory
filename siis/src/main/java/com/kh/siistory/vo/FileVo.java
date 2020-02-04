package com.kh.siistory.vo;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class FileVo {
	private int profile_file_no, memeber_no;
	private MultipartFile member_file;
}
