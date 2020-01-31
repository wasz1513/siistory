package com.kh.siistory.repository;

import com.kh.siistory.entity.MemberDto;
import com.kh.siistory.vo.SeqVo;

public interface MemberDao {

	void regist(MemberDto memberDto);

	MemberDto login(MemberDto memberDto);
	
	SeqVo seq_no();
	
	MemberDto getMember(int member_no);
}
