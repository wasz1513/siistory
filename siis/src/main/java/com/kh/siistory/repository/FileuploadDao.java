package com.kh.siistory.repository;

import com.kh.siistory.entity.Member_profile_fileDto;
import com.kh.siistory.vo.SeqVo;

public interface FileuploadDao {
	SeqVo createSeq();
	
	void insert(Member_profile_fileDto memberfileDto);
	
	void update(Member_profile_fileDto memberfileDto);
}
