package com.kh.siistory.repository;

public interface ConnectTableDao {

	//새로 생성된 session
	public void true_session();
	
	//이미 생성된 session
	public void false_session();
	
	//삭제되는 시점의 session
	public void exit_session();
	
		
}
