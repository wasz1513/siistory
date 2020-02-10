package com.kh.siistory.repository;

import com.kh.siistory.entity.FollowDto;

public interface FollowDao {
	int following(FollowDto followDto);

	int unfollowing(FollowDto followDto);
}
