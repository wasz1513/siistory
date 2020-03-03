package com.kh.siistory.service;

import java.util.List;

import com.kh.siistory.vo.AdminChartVo;

public interface AdminChartService {
	
	List<AdminChartVo> day_visit();
	
	List<AdminChartVo> month_visit();
	
	List<AdminChartVo> year_visit();
	
	List<AdminChartVo> day_regist();
	
	List<AdminChartVo> month_regist();
	
	List<AdminChartVo> year_regist();
	
	List<AdminChartVo> day_content();
	
	List<AdminChartVo> month_content();
	
	List<AdminChartVo> year_content();
}
