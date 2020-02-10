package com.kh.siistory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

import com.kh.siistory.repository.AlarmDao;

@Repository
public class ScheduleServiceImpl implements ScheduleService{

	@Autowired
	private AlarmDao alarmDao;
	
	
	
	@Override
	@Scheduled(cron = " * * * */3 * *") //3일마다
	public void alarm_Cycle() {
//		alarmDao.delete(alarmDto);
	}

	@Scheduled(cron = " */5 * * * * *") //5초 마다 
	@Override
	public void alarm_Test() {
//		alarmDao.delete(alarmDto);
		
	}
	
	
		
	

}
