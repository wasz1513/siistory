package com.kh.siistory.repository;

import com.kh.siistory.entity.BoardLikeDto;

public interface BoardLikeDao {

	public void insert(BoardLikeDto boardlikeDto);
	
	public void delete(BoardLikeDto boardlikeDto);

}
