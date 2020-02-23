package com.kh.siistory.repository;

import com.kh.siistory.entity.ConnectTableDto;

public interface ConnectTableDao {

	//새로 생성된 session
	public void true_session(ConnectTableDto connecttableDto);
	
	//이미 생성된 session
	public void false_session(ConnectTableDto connecttableDto);
	
	//삭제되는 시점의 session
	public void exit_session(ConnectTableDto connecttableDto);
	
		
}
