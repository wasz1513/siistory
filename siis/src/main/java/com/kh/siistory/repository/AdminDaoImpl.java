package com.kh.siistory.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AdminDaoImpl implements AdminDao{

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public void dormant() {
		sqlSession.update("admin.dormant");
	}

}
