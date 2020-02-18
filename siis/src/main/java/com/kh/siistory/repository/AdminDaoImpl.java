package com.kh.siistory.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.siistory.vo.AdminSearchVo;
import com.kh.siistory.vo.MemberProfileVo;

@Repository
public class AdminDaoImpl implements AdminDao{

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public void dormant() {
		sqlSession.update("admin.dormant");
	}

	@Override
	public List<MemberProfileVo> search_Member(AdminSearchVo adminSearchVo) {
		return sqlSession.selectList("admin.search-member", adminSearchVo);
	}

}
