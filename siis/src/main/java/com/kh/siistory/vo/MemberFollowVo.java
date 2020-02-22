package com.kh.siistory.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class MemberFollowVo {
	private int member_no, friend_no, follower, following, friend_state;
	private String email, member_name;
	
	private String connect_state = "미접속";
}
