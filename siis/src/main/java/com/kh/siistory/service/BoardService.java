package com.kh.siistory.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

public interface BoardService {
	void Boarduploadimage(List<MultipartFile> sel_files, HttpSession session) throws IllegalStateException, IOException;
}
