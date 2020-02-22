package com.kh.siistory.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.siistory.entity.WarningDto;
@Repository
public class WarningDaoImpl implements WarningDao {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public void insert(WarningDto warningDto) {
		
			sqlSession.insert("warning.insert",warningDto);
	}

}
