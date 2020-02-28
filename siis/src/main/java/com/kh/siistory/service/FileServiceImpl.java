package com.kh.siistory.service;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kh.siistory.entity.BoardPicDto;
import com.kh.siistory.entity.Member_profile_fileDto;
import com.kh.siistory.repository.BoardPicDao;
import com.kh.siistory.repository.FileuploadDao;
import com.kh.siistory.vo.MemberVo;
import com.kh.siistory.vo.PhotoVo;
import com.kh.siistory.vo.SeqVo;

import lombok.extern.slf4j.Slf4j;

@Slf4j

@Service
public class FileServiceImpl implements FileService {

	@Autowired
	private FileuploadDao fileuploadDao;

	@Autowired
	private BoardPicDao boardpicDao;

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
		for (int i = 0; i < sel_files.size(); i++) {
			MultipartFile mf = sel_files.get(i);
			if(i == 0) {
				bdto.add(BoardPicDto.builder().board_pic_no(boardpicDao.getpicseq()).board_pic_size(mf.getSize())
						.board_pic_store(UUID.randomUUID().toString()).main(0).build());
			} else {
				bdto.add(BoardPicDto.builder().board_pic_no(boardpicDao.getpicseq()).board_pic_size(mf.getSize())
						.board_pic_store(UUID.randomUUID().toString()).main(1).build());
			}
		}

		// 파일 정보를 DB에 저장
		boardpicDao.uploadimage(bdto);

		// 실제 파일 저장
		for (int i = 0; i < bdto.size(); i++) {
			File target = new File(dir, bdto.get(i).getBoard_pic_store());
			sel_files.get(i).transferTo(target);
		}

		// 반환 값 설정(사진 seq)
		List<Integer> returnseq = new ArrayList<>();
		for (int i = 0; i < bdto.size(); i++) {
			returnseq.add(bdto.get(i).getBoard_pic_no());
		}

		Map<String, Object> map = new HashMap<>();
		map.put("board_pic_no", returnseq);

		log.info("returnseq = {}", returnseq);

		return map;
	}

	// 게시판 리스트 불러올때 대표 사진 불러오기
	@Override
	public void getimage(int boardno, HttpServletResponse resp) throws UnsupportedEncodingException, IOException {
		PhotoVo photoVo = boardpicDao.getimage(boardno);
		
		File target = new File("D:/upload/kh2f/" + "m" + photoVo.getMember_no(), photoVo.getBoard_pic_store());
		
		resp.setHeader("Content-Type", "application/octet=stream; charset=UTF-8");
		resp.setHeader("Content-Disposition", "attachment; filename=\""+URLEncoder.encode(photoVo.getBoard_pic_store(), "UTF-8")+"\"");
		resp.setHeader("Content-Length", String.valueOf(photoVo.getBoard_pic_size()));
		
		byte[] data = FileUtils.readFileToByteArray(target);
		

		resp.getOutputStream().write(data);
	}

}
