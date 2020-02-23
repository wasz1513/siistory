package com.kh.siistory.repository;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.kh.siistory.entity.ReplyDto;

public interface ReplyDao {
	ReplyDto insert(ReplyDto replyDto, HttpSession session);
	List<ReplyDto> commentview(Map<String, Integer> obj);
	List<ReplyDto> morereply(Map<String, Integer> obj);
}
