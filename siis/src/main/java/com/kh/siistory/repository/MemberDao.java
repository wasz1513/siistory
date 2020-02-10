package com.kh.siistory.repository;

import com.kh.siistory.entity.CertDto;
import com.kh.siistory.entity.MemberDto;
import com.kh.siistory.vo.MemberVo;
import com.kh.siistory.vo.SeqVo;

public interface MemberDao {

	void regist(MemberDto memberDto);

	MemberDto login(MemberDto memberDto);
	
	SeqVo seq_no();
	
	MemberDto getMember(int member_no);
	
	MemberVo getMemberVo_name(String member_name);
	
	MemberVo getMemberVo_no(int member_no);
	
	void regist_profile(MemberDto memberDto);
	
	void update_profile(MemberVo memberVo);

	int idcheck(String email);

	void changePw(MemberDto memberDto);
	
	int changeName(String member_name, int member_no);
	
}
