package com.kh.siistory.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.siistory.vo.AdminBoardVo;
import com.kh.siistory.vo.AdminSearchVo;
import com.kh.siistory.vo.BoardSearchVo;
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

	@Override
	public void warning_count_newreceipt(int member_no) {
		sqlSession.insert("admin.warning-count-newreceipt", member_no);
	}

	@Override
	public void warning_count_addreceipt(int member_no) {
		sqlSession.update("admin.warning-count-addreceipt", member_no);
	}

	@Override
	public void warning_receipt(int warning_no) {
		sqlSession.update("admin.warning-receipt", warning_no);
	}

	@Override
	public void warning_hold(int warning_no) {
		sqlSession.update("admin.warning-hold", warning_no);
	}

	@Override
	public int warning_check(int member_no) {
		return sqlSession.selectOne("admin.warning-check", member_no);
	}

	@Override
	public void cancle_suspend(int member_no) {
		sqlSession.update("admin.cancle-suspend", member_no);
	}

	@Override
	public void delete_warning_count(int member_no) {
		sqlSession.delete("admin.delete-warning-count", member_no);
	}

	@Override
	public void suspend_member(int member_no) {
		sqlSession.update("admin.suspend-member", member_no);
	}

	@Override
	public int search_board_count(BoardSearchVo boardSearchVo) {
		return sqlSession.selectOne("admin.search-board-count", boardSearchVo);
	}

	@Override
	public List<AdminBoardVo> search_board(BoardSearchVo boardSearchVo) {
		return sqlSession.selectList("admin.search-board", boardSearchVo);
	}

	@Override
	public void change_board_state(int board_no) {
		sqlSession.update("admin.change-board-state", board_no);
	}

}
