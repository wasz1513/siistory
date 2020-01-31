package com.kh.siistory.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.siistory.entity.FriendDto;


@Repository
public class FriendDaoImpl implements FriendDao {

	
	@Autowired
	private SqlSession sqlSession;
	
	
	@Override
	public List<FriendDto> getList() {
		return sqlSession.selectList("friend.getlist");
	}

}
