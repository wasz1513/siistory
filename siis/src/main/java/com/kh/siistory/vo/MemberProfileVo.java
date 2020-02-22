package com.kh.siistory.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class MemberProfileVo {
	private int member_no, follower, following, friend_state;
	private String email, member_name, member_pw, member_joindate, member_outdate,
			member_state, member_birth, member_gender, member_phone, member_profile_state,
			member_home, member_school, member_job;
}
