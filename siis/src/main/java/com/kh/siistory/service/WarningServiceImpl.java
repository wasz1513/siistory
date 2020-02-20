package com.kh.siistory.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.siistory.entity.WarningDto;
import com.kh.siistory.repository.WarningDao;

@Repository
public class WarningServiceImpl implements WarningService {

	@Autowired
	private WarningDao warningDao;
	
	@Override
	public void insert(WarningDto warningDto) {
		
	warningDao.insert(warningDto);	
	
	}

}
