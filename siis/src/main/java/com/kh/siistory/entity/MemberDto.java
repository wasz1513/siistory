package com.kh.siistory.entity;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class MemberDto {
	private int member_no; 
	private String email, member_name, member_pw, member_joindate, member_outdate, member_state;
	private List<FollowDto> followList;
}
