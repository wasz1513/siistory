package com.kh.siistory.repository;

import java.util.List;

import com.kh.siistory.entity.Member_profile_fileDto;
import com.kh.siistory.vo.SeqVo;

public interface FileuploadDao {
	SeqVo createSeq();
	
	void insert(Member_profile_fileDto memberfileDto);
	
	void update(Member_profile_fileDto memberfileDto);
	
	List<Member_profile_fileDto> getFileInfo(int member_no);
}
