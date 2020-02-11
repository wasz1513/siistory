package com.kh.siistory.repository;

import java.util.List;

import com.kh.siistory.entity.FollowDto;
import com.kh.siistory.vo.MemberFollowVo;

public interface FollowDao {
	int following(FollowDto followDto);

	int unfollowing(FollowDto followDto);

	List<MemberFollowVo> myfollowing(int member_no);
}
