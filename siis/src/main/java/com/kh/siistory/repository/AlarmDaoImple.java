package com.kh.siistory.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.siistory.entity.AlarmDto;

@Repository
public class AlarmDaoImple implements AlarmDao{

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public void insert(AlarmDto alarmDto) {
		
		sqlSession.insert("alarm.insert", alarmDto);
		
	}

	@Override
	public void delete(AlarmDto alarmDto) {
				
		sqlSession.delete("alarm.delete", alarmDto);
	}

	@Override
	public List<AlarmDto> getList(int target_no) {
		
		return sqlSession.selectList("alarm.getList", target_no);
	}

	@Override
	public AlarmDto get(int no) {
		
		return sqlSession.selectOne("alarm.get", no);
	}
	
	

}
