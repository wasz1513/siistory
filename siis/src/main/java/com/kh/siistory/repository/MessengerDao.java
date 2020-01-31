package com.kh.siistory.repository;

import java.util.List;

import com.kh.siistory.entity.MemberDto;

public interface MessengerDao {

	//메신저 친구목록 조회를 위한 리스트 조회 기능
	
	List<MemberDto> ChatList ();
	
}
