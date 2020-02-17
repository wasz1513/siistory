package com.kh.siistory.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.kh.siistory.entity.Member_profile_fileDto;
import com.kh.siistory.vo.FileVo;
import com.kh.siistory.vo.MemberVo;

public interface FileService {

	void upload(MemberVo memberVo, MultipartFile member_file) throws IllegalStateException, IOException;
	
	void change(MemberVo memberVo, MultipartFile member_file) throws IllegalStateException, IOException;

	
}
