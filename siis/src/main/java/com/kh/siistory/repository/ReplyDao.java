package com.kh.siistory.repository;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.kh.siistory.entity.ReplyDto;

public interface ReplyDao {
	ReplyDto insert(ReplyDto replyDto, HttpSession session);
	List<ReplyDto> replyview(int super_no);
}
