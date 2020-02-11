package com.kh.siistory.repository;

import java.util.List;

import com.kh.siistory.entity.FollowDto;

public interface FollowDao {
	int following(FollowDto followDto);

	int unfollowing(FollowDto followDto);

	List<FollowDto> myfollowing(int member_no);
}