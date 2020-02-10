package com.kh.siistory.repository;

import javax.servlet.http.HttpSession;

import com.kh.siistory.entity.ReplyDto;

public interface ReplyDao {
	void insert(ReplyDto replyDto, HttpSession session);
}
