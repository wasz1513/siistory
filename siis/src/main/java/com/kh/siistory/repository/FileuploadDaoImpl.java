package com.kh.siistory.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.siistory.entity.Member_profile_fileDto;
import com.kh.siistory.vo.SeqVo;

@Repository
public class FileuploadDaoImpl implements FileuploadDao{

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public SeqVo createSeq() {
		return sqlSession.selectOne("memberfile.seqno");
	}

	@Override
	public void insert(Member_profile_fileDto memberfileDto) {
		sqlSession.insert("memberfile.insert", memberfileDto);
	}

	@Override
	public void update(Member_profile_fileDto memberfileDto) {
		sqlSession.update("memberfile.change", memberfileDto);
	}

	
}
