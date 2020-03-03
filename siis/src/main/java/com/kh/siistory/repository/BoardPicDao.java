package com.kh.siistory.repository;

import java.util.List;

import com.kh.siistory.entity.BoardPicDto;
import com.kh.siistory.vo.PhotoVo;

public interface BoardPicDao {
	void uploadimage(List<BoardPicDto> boardpicDto);
	int getpicseq();
	PhotoVo getimage(int boardno);
	PhotoVo getimageall(int pic);
	List<Integer> getphotolist(int boardno);
}
