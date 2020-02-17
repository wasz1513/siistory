package com.kh.siistory.service;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kh.siistory.entity.Member_profile_fileDto;
import com.kh.siistory.repository.FileuploadDao;
import com.kh.siistory.vo.FileVo;
import com.kh.siistory.vo.MemberVo;
import com.kh.siistory.vo.SeqVo;
@Service
public class FileServiceImpl implements FileService{

	@Autowired
	private FileuploadDao fileuploadDao;
	
	@Override
	public void upload(MemberVo memberVo, MultipartFile member_file) throws IllegalStateException, IOException {
		//파일 실제저장
		File dir = new File("D:/upload/kh2f/member");
		dir.mkdirs();
		File target = new File(dir, String.valueOf(memberVo.getMember_no()));
		member_file.transferTo(target);
		
		//파일 DB저장
		SeqVo seqVo = fileuploadDao.createSeq();
		
		Member_profile_fileDto memberfileDto = Member_profile_fileDto.builder()
										.profile_file_no(seqVo.getSeq_no())
										.profile_file_uploadname(member_file.getOriginalFilename())
										.profile_file_savename(String.valueOf(memberVo.getMember_no()))
										.profile_file_size(member_file.getSize())
										.member_no(memberVo.getMember_no())
																	.build();
		
		fileuploadDao.insert(memberfileDto);
	}

	@Override
	public void change(MemberVo memberVo, MultipartFile member_file) throws IllegalStateException, IOException {
		File dir = new File("D:/upload/kh2f/member");
		dir.mkdirs();
		File target = new File(dir, String.valueOf(memberVo.getMember_no()));
		member_file.transferTo(target);
		
		SeqVo seqVo = fileuploadDao.createSeq();
		
		Member_profile_fileDto memberfileDto = Member_profile_fileDto.builder()
										.profile_file_no(seqVo.getSeq_no())
										.profile_file_uploadname(member_file.getOriginalFilename())
										.profile_file_savename(String.valueOf(memberVo.getMember_no()))
										.profile_file_size(member_file.getSize())
										.member_no(memberVo.getMember_no())
																	.build();
		fileuploadDao.update(memberfileDto);
	}


	
	
}
