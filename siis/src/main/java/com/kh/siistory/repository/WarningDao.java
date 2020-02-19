package com.kh.siistory.repository;

import org.springframework.stereotype.Repository;

import com.kh.siistory.entity.WarningDto;


public interface WarningDao {

	
	public void insert(WarningDto warningDto);
	
}
