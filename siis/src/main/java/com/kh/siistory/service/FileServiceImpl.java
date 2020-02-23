package com.kh.siistory.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kh.siistory.entity.BoardPicDto;
import com.kh.siistory.entity.Member_profile_fileDto;
import com.kh.siistory.repository.BoardDao;
import com.kh.siistory.repository.BoardPicDao;
import com.kh.siistory.repository.FileuploadDao;
import com.kh.siistory.vo.MemberVo;
import com.kh.siistory.vo.SeqVo;

import lombok.extern.slf4j.Slf4j;

@Slf4j

@Service
public class FileServiceImpl implements FileService {

	@Autowired
	private FileuploadDao fileuploadDao;

	@Autowired
	private BoardPicDao boardpicDao;

	@Autowired
	private BoardDao boardDao;

	// 회원 프로필 사진 업로드
	@Override
	public void upload(MemberVo memberVo, MultipartFile member_file) throws IllegalStateException, IOException {
		// 파일 실제저장
		File dir = new File("D:/upload/kh2f/member");
		dir.mkdirs();
		File target = new File(dir, String.valueOf(memberVo.getMember_no()));
		member_file.transferTo(target);

		// 파일 DB저장
		SeqVo seqVo = fileuploadDao.createSeq();

		Member_profile_fileDto memberfileDto = Member_profile_fileDto.builder().profile_file_no(seqVo.getSeq_no())
				.profile_file_uploadname(member_file.getOriginalFilename())
				.profile_file_savename(String.valueOf(memberVo.getMember_no())).profile_file_size(member_file.getSize())
				.member_no(memberVo.getMember_no()).build();

		fileuploadDao.insert(memberfileDto);
	}

	// 회원 프로필 사진 변경
	@Override
	public void change(MemberVo memberVo, MultipartFile member_file) throws IllegalStateException, IOException {
		File dir = new File("D:/upload/kh2f/member");
		dir.mkdirs();
		File target = new File(dir, String.valueOf(memberVo.getMember_no()));
		member_file.transferTo(target);

		SeqVo seqVo = fileuploadDao.createSeq();

		Member_profile_fileDto memberfileDto = Member_profile_fileDto.builder().profile_file_no(seqVo.getSeq_no())
				.profile_file_uploadname(member_file.getOriginalFilename())
				.profile_file_savename(String.valueOf(memberVo.getMember_no())).profile_file_size(member_file.getSize())
				.member_no(memberVo.getMember_no()).build();
		fileuploadDao.update(memberfileDto);
	}

	// 게시판 사진 업로드
	@Override
	public Map<String, Object> Boarduploadimage(List<MultipartFile> sel_files, HttpSession session)
			throws IllegalStateException, IOException {

		// 디렉토리 생성
		File dir = new File("D:/upload/kh2f/" + "m" + String.valueOf(session.getAttribute("member_no")));
		dir.mkdirs();

		// 각 파일 정보를 DTO에 저장
		List<BoardPicDto> bdto = new ArrayList<>();
		for (MultipartFile mf : sel_files) {
			bdto.add(BoardPicDto.builder().board_pic_no(boardpicDao.getpicseq()).board_pic_size(mf.getSize()).board_pic_store(UUID.randomUUID().toString())
					.build());
		}
		
		// 파일 정보를 DB에 저장
		boardpicDao.uploadimage(bdto);

		// 실제 파일 저장
		for (int i = 0; i < bdto.size(); i++) {
			File target = new File(dir, bdto.get(i).getBoard_pic_store());
			sel_files.get(i).transferTo(target);
		}
		
		// 반환 값 설정(사진 seq, 게시판 seq)
		List<Integer> returnseq = new ArrayList<>();
		for(int i = 0; i < bdto.size(); i++) {
			returnseq.add(bdto.get(i).getBoard_pic_no());
		}
		
		Map<String, Object> map = new HashMap<>();
		map.put("board_pic_no", returnseq);
		
		log.info("returnseq = {}", returnseq);
		
		return map;
	}
}
