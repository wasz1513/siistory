package com.kh.siistory.repository;

import java.util.List;

import com.kh.siistory.entity.ReplyDto;

public interface ReplyDao {
	List<ReplyDto> replylist(int no);
}
