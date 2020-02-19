package com.kh.siistory.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.kh.siistory.entity.BoardPicDto;
import com.kh.siistory.repository.BoardDao;
import com.kh.siistory.repository.BoardPicDao;

@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	private BoardDao boardDao;

	@Autowired
	private BoardPicDao boardpicDao;
	
	@Override
	@Transactional
	public void Boarduploadimage(List<MultipartFile> sel_files, HttpSession session) throws IllegalStateException, IOException {
		int no = boardDao.getboardseq();
		session.setAttribute("no", no);
		
		// 디렉토리 생성
		File dir = new File("D:/upload/kh2f/" + String.valueOf(session.getAttribute("member_no")) + "/" + String.valueOf(no));
		dir.mkdirs();

		// 각 파일 정보를 DTO에 저장
		List<BoardPicDto> boardpicDto = new ArrayList<>();
		for (MultipartFile mf : sel_files) {
			boardpicDto.add(BoardPicDto.builder().board_no(no).board_pic_size(mf.getSize())
					.board_pic_store(UUID.randomUUID().toString()).build());
		}
		
		boardpicDao.uploadimage(boardpicDto);

		// 실제 파일 저장
		for (int i = 0; i < boardpicDto.size(); i++) {
			File target = new File(dir, boardpicDto.get(i).getBoard_pic_store());
			sel_files.get(i).transferTo(target);
		}
		
	}
}
