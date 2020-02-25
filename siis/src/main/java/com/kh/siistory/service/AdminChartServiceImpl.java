package com.kh.siistory.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.siistory.vo.AdminChartVo;

@Service
public class AdminChartServiceImpl implements AdminChartService{
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<AdminChartVo> day_visit() {
		return sqlSession.selectList("adminchart.day-visit");
	}

	@Override
	public List<AdminChartVo> month_visit() {
		return sqlSession.selectList("adminchart.month-visit");
	}

	@Override
	public List<AdminChartVo> year_visit() {
		return sqlSession.selectList("adminchart.year-visit");
	}

	@Override
	public List<AdminChartVo> day_regist() {
		return sqlSession.selectList("adminchart.day-regist");
	}

	@Override
	public List<AdminChartVo> month_regist() {
		return sqlSession.selectList("adminchart.month-regist");
	}

	@Override
	public List<AdminChartVo> year_regist() {
		return sqlSession.selectList("adminchart.year-regist");
	}

	@Override
	public List<AdminChartVo> day_content() {
		return sqlSession.selectList("adminchart.day-content");
	}

	@Override
	public List<AdminChartVo> month_content() {
		return sqlSession.selectList("adminchart.month-content");
	}

	@Override
	public List<AdminChartVo> year_content() {
		return sqlSession.selectList("adminchart.year-content");
	}
}
