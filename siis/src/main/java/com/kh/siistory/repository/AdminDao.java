package com.kh.siistory.repository;

import java.util.List;

import com.kh.siistory.vo.AdminSearchVo;
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
	
}
