package com.example.practice.service;

import com.example.practice.vo.Pagevo;
import com.example.practice.vo.RevenueVO;
import com.example.practice.vo.SlipVO;

import java.util.List;

public interface IF_AccountService {
	//서비스 작업을 메서드로 정의 합니다.
	//컨트롤러가 조인 작업을 요청한다. 이때 매개변수로 객체의 주소를 전달.
	public void join(RevenueVO rvo) throws Exception;
//	public RevenueVO selectOne(String revenueCode) throws Exception;

	List<SlipVO> selectAll(Pagevo pagevo) throws Exception;

	public int totalCountPV() throws Exception;

	public void psInsert(SlipVO slipvo) throws Exception;

}
