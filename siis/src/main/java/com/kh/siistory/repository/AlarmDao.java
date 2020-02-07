package com.kh.siistory.repository;

import java.util.List;

import com.kh.siistory.entity.AlarmDto;

public interface AlarmDao {

	public void insert(AlarmDto alarmDto);
	
	public void delete(AlarmDto alarmDto);
	
	public AlarmDto get(int no);
	
	public List<AlarmDto> getList(AlarmDto alarmDto);
	
}
