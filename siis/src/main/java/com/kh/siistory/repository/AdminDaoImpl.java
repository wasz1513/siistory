package com.kh.siistory.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.siistory.vo.AdminSearchVo;
import com.kh.siistory.vo.MemberProfileVo;
import com.kh.siistory.vo.WarningVo;

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

	@Override
	public int search_member_count(AdminSearchVo adminSearchVo) {
		return sqlSession.selectOne("admin.search-member-count", adminSearchVo);
	}

	@Override
	public List<MemberProfileVo> search_member_nav(AdminSearchVo adminSearchVo) {
		return sqlSession.selectList("admin.search-member-nav", adminSearchVo);
	}

	@Override
	public List<WarningVo> warning_list(WarningVo warningVo) {
		return sqlSession.selectList("admin.warning-list", warningVo);
	}

	@Override
	public int warning_list_count(WarningVo warningVo) {
		return sqlSession.selectOne("admin.warning-list-count", warningVo);
	}

}
