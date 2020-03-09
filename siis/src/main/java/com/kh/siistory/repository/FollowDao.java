package com.kh.siistory.repository;

import java.util.List;

import com.kh.siistory.entity.FollowDto;
import com.kh.siistory.vo.MemberFollowVo;

public interface FollowDao {
	int following(FollowDto followDto);
	
	void follower(FollowDto followDto);

	int unfollowing(FollowDto followDto);
	
	void unfollower(FollowDto followDto);

	List<MemberFollowVo> myfollowing(int member_no);

	List<MemberFollowVo> myfollower(int member_no);
	
	int follower_ok(FollowDto followDto);
	
	void following_ok(FollowDto followDto);
	
	int follower_no(FollowDto followDto);
	
	void following_no(FollowDto followDto);
	
	int check_following(FollowDto followDto);
	
	List<MemberFollowVo> myfriend(int member_no);
	
	List<MemberFollowVo> push_friend (int member_no);
	
	void refuse_following(FollowDto followDto);
	
	int refuse_follower(FollowDto followDto);
}
