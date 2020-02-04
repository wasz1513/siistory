package com.kh.siistory.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class Member_profile_fileDto {
	private int profile_file_no, member_no;
	private String profile_file_uploadname, profile_file_savename;
	private long profile_file_size;
}
