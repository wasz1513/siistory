package com.kh.siistory.vo;

import java.util.List;

import com.kh.siistory.entity.FriendDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FriendListData {
	
	private int member_no;
	private String text;
	private List <FriendDto> flist_data;
	private int status;
	
	
}
