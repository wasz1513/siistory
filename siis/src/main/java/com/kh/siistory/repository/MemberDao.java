package com.kh.siistory.repository;

import java.util.List;

import com.kh.siistory.entity.BoardDto;
import com.kh.siistory.entity.CertDto;
import com.kh.siistory.entity.MemberDto;
import com.kh.siistory.vo.MemberFollowVo;
import com.kh.siistory.vo.MemberProfileVo;
import com.kh.siistory.vo.MemberVo;
import com.kh.siistory.vo.SeqVo;

public interface MemberDao {

	void regist(MemberDto memberDto);

	MemberDto login(MemberDto memberDto);
	
	void last_login(MemberDto memberDto);
	
	SeqVo seq_no();
	
	MemberDto getMember(int member_no);
	
	MemberVo getMemberVo_name(String member_name);
	
	MemberVo getMemberVo_no(int member_no);
	
	void regist_profile(MemberDto memberDto);
	
	void update_profile(MemberVo memberVo);

	int idcheck(String email);

	void changePw(MemberDto memberDto);
	
	int changeName(String member_name, int member_no);
	
	List<MemberFollowVo> getMember_Email(MemberDto memberDto);

	List<MemberFollowVo> getMember_Name(MemberDto memberDto);

	MemberProfileVo memberInfo(int my_member_no, int member_no);
	
	void dormant(MemberDto memberDto);
	
	int checkFile(int member_no);

	void withdraw(int member_no);

	int namecheck(String username);

	void me(SeqVo seqVo);

	List<BoardDto> search_tag(String keyword);
}
