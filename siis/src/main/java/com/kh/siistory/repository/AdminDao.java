package com.kh.siistory.repository;

import java.util.List;

import com.kh.siistory.vo.AdminBoardVo;
import com.kh.siistory.vo.AdminSearchVo;
import com.kh.siistory.vo.BoardSearchVo;
import com.kh.siistory.vo.MemberProfileVo;
import com.kh.siistory.vo.WarningVo;

public interface AdminDao {

	void dormant();

	List<MemberProfileVo> search_Member(AdminSearchVo adminSearchVo);

	int search_member_count(AdminSearchVo adminSearchVo);
	
	List<MemberProfileVo> search_member_nav(AdminSearchVo adminSearchVo);

	List<WarningVo> warning_list(WarningVo warningVo);
	
	int warning_list_count(WarningVo warningVo);
	
	void warning_count_newreceipt(int member_no);
	
	void warning_count_addreceipt(int member_no);
	
	void warning_receipt(int warning_no);
	
	void warning_hold(int warning_no);
	
	int warning_check(int member_no);
	
	void cancle_suspend(int member_no);
	
	void delete_warning_count(int member_no);

	void suspend_member(int member_no);

	int search_board_count(BoardSearchVo boardSearchVo);

	List<AdminBoardVo> search_board(BoardSearchVo boardSearchVo);

	void change_board_state(int board_no);
}
