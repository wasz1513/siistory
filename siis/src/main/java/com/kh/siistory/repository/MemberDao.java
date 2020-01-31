package com.kh.siistory.repository;

import com.kh.siistory.entity.MemberDto;

public interface MemberDao {

	void regist(MemberDto memberDto);

	MemberDto login(MemberDto memberDto);
}
