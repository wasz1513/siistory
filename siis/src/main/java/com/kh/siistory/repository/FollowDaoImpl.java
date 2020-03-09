package com.kh.siistory.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.siistory.entity.FollowDto;
import com.kh.siistory.vo.MemberFollowVo;

@Repository
public class FollowDaoImpl implements FollowDao{

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int following(FollowDto followDto) {
		return sqlSession.insert("follow.following", followDto);
	}

	@Override
	public void follower(FollowDto followDto) {
		sqlSession.insert("follow.follower", followDto);
	}
	
	@Override
	public int unfollowing(FollowDto followDto) {
		return sqlSession.delete("follow.unfollowing", followDto);
	}

	@Override
	public void unfollower(FollowDto followDto) {
		sqlSession.delete("follow.unfollower", followDto);
	}
	
	@Override
	public List<MemberFollowVo> myfollowing(int member_no) {
		return sqlSession.selectList("follow.myfollowing", member_no);
	}

	@Override
	public List<MemberFollowVo> myfollower(int member_no) {
		return sqlSession.selectList("follow.myfollower", member_no);
	}

	@Override
	public int follower_ok(FollowDto followDto) {
		return sqlSession.update("follow.follower_ok", followDto);
	}

	@Override
	public void following_ok(FollowDto followDto) {
		sqlSession.update("follow.following_ok", followDto);
	}

	@Override
	public int follower_no(FollowDto followDto) {
		return sqlSession.update("follow.follower_no", followDto);
	}

	@Override
	public void following_no(FollowDto followDto) {
		sqlSession.update("follow.following_no", followDto);
	}

	@Override
	public int check_following(FollowDto followDto) {
		return sqlSession.selectOne("follow.check_following", followDto);
	}

	@Override
	public List<MemberFollowVo> myfriend(int member_no) {
		return sqlSession.selectList("follow.myfriend", member_no);
	}

	@Override
	public void refuse_following(FollowDto followDto) {
		sqlSession.delete("follow.refuse-following", followDto);
	}

	@Override
	public int refuse_follower(FollowDto followDto) {
		return sqlSession.delete("follow.refuse-follower", followDto);
	}

	@Override
	public int check_followingcount(int member_no) {
		return sqlSession.selectOne("follow.followingcount", member_no);
	}
	
	




}
