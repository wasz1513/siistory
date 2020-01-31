package com.kh.siistory.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.siistory.entity.MemberDto;
import com.kh.siistory.vo.MemberVo;
import com.kh.siistory.vo.SeqVo;

@Repository
public class MemberDaoImpl implements MemberDao{

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public void regist(MemberDto memberDto) {
		sqlSession.insert("member.regist", memberDto);
	}

	@Override
	public MemberDto login(MemberDto memberDto) {
		return sqlSession.selectOne("member.login", memberDto);
	}

	@Override
	public SeqVo seq_no() {
		return sqlSession.selectOne("member.seqno");
	}

	@Override
	public MemberDto getMember(int member_no) {
		return sqlSession.selectOne("member.getMember", member_no);
	}

	@Override
	public MemberVo getMemberVo_name(String member_name) {
		return sqlSession.selectOne("member.getMemberVo_name", member_name);
	}

	@Override
	public MemberVo getMemberVo_no(int member_no) {
		return sqlSession.selectOne("member.getMemberVo_no", member_no);
	}

	@Override
	public void regist_profile(MemberDto memberDto) {
		sqlSession.insert("member.regist_profile", memberDto);
	}

}
