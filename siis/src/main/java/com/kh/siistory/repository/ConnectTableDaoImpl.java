package com.kh.siistory.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.siistory.entity.ConnectTableDto;

@Repository
public class ConnectTableDaoImpl implements ConnectTableDao {

	
	@Autowired
	private SqlSession sqlSession;
	
	@Autowired
	private ConnectTableDto dto;
	
	@Override
	public void true_session(ConnectTableDto connecttableDto) {
						
		sqlSession.update("connect.newsession", dto);
		
		
	}

	@Override
	public void false_session(ConnectTableDto connecttableDto) {
		sqlSession.update("connect.oldsession", dto);
	}

	@Override
	public void exit_session(ConnectTableDto connecttableDto) {
		sqlSession.update("connect.exitsession", dto);
	}
	
	


	
}
