package com.kh.siistory.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import com.kh.siistory.vo.MemberVo;

public interface FileService {

	void upload(MemberVo memberVo, MultipartFile member_file) throws IllegalStateException, IOException;
	
	void change(MemberVo memberVo, MultipartFile member_file) throws IllegalStateException, IOException;
	
	Map<String, Object> Boarduploadimage(List<MultipartFile> sel_files, HttpSession session) throws IllegalStateException, IOException;
	
	void getimage(int boardno, HttpServletResponse resp) throws UnsupportedEncodingException, IOException;
}
