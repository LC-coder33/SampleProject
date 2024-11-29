package com.example.practice.service;

import com.example.practice.vo.Pagevo;
import com.example.practice.vo.RevenueVO;
import com.example.practice.vo.SlipVO;
import com.example.practice.vo.SliprgVO;

import java.util.List;
import java.util.Map;

public interface IF_AccountService {
	//서비스 작업을 메서드로 정의 합니다.
	//컨트롤러가 조인 작업을 요청한다. 이때 매개변수로 객체의 주소를 전달.
	public void join(RevenueVO rvo) throws Exception;
//	public RevenueVO selectOne(String revenueCode) throws Exception;

	List<SlipVO> selectAll(Pagevo pagevo) throws Exception;

	int totalCountPV() throws Exception;

	void psInsert(SlipVO slipvo) throws Exception;

	List<SlipVO> selectpvName(Pagevo pagevo, String pvName) throws Exception;
	List<SlipVO> selectpvCmpy(Pagevo pagevo, String pvCmpy) throws Exception;
	int countpvName(String pvName) throws Exception;
	int countpvCmpy(String pvCmpy) throws Exception;

	List<SlipVO> selectByType(Pagevo pagevo, String pvslipCode) throws Exception;
	int countByType(String pvslipCode) throws Exception;

	void delpvSlip(int pvCode) throws Exception;
	void rgInsert(SlipVO slipvo) throws Exception;
	List<SliprgVO> selectrgAll(Pagevo pagevo) throws Exception;
	int totalCountRG() throws Exception;
	List<SliprgVO> selectrgName(Pagevo pagevo, String rgName) throws Exception;
	int countrgName(String rgName) throws Exception;
	List<SliprgVO> selectrgCmpy(Pagevo pagevo, String rgCmpy) throws Exception;
	int countrgCmpy(String rgCmpy) throws Exception;
	List<SliprgVO> selectByrgType(Pagevo pagevo, String rgslipCode);
	int countByrgType(String rgslipCode) throws Exception;
}
