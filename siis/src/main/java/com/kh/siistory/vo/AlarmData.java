package com.kh.siistory.vo;

import java.util.List;

import com.kh.siistory.entity.AlarmDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlarmData {

	private int member_no;
	private String text;
	private int status;
	private List<AlarmDto> alarmList;
	
	
}
