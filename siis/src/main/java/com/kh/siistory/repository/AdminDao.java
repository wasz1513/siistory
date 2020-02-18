package com.kh.siistory.repository;

import java.util.List;

import com.kh.siistory.vo.AdminSearchVo;
import com.kh.siistory.vo.MemberProfileVo;

public interface AdminDao {

	void dormant();

	List<MemberProfileVo> search_Member(AdminSearchVo adminSearchVo);

}
