package com.kh.siistory.repository;

import java.util.List;
import java.util.Map;

import com.kh.siistory.entity.BoardPicDto;

public interface BoardPicDao {
	void uploadimage(List<BoardPicDto> boardpicDto);
}
