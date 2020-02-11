package com.kh.siistory.repository;

import java.util.List;

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
	public int unfollowing(FollowDto followDto) {
		return sqlSession.delete("follow.unfollowing", followDto);
	}

	@Override
	public List<MemberFollowVo> myfollowing(int member_no) {
		return sqlSession.selectList("follow.myfollowing", member_no);
	}


}
