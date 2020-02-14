package com.kh.siistory.repository;

import com.kh.siistory.entity.ReplyLikeDto;

public interface ReplyLikeDao {

	
	public void insert(ReplyLikeDto replylikeDto);
	
	public void delete(ReplyLikeDto replylikeDto);
}
