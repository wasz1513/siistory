package com.kh.siistory.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ConnectTableDaoImpl implements ConnectTableDao {

	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public void true_session() {
		
		sqlSession.update("connect.newsession");
		
		
	}

	@Override
	public void false_session() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exit_session() {
		// TODO Auto-generated method stub
		
	}

	
	
}
