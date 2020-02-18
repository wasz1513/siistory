package com.kh.siistory.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class AdminSearchVo {
	private String type, keyword, member_state, member_gender, member_phone, member_birth;
	
}
