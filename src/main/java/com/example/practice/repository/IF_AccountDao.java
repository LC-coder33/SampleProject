package com.example.practice.repository;

import com.example.practice.vo.DateRangeVO;
import com.example.practice.vo.Pagevo;
import com.example.practice.vo.SlipVO;
import com.example.practice.vo.SliprgVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Mapper
@Repository
public interface IF_AccountDao {

//	public RevenueVO selectOne(String revenueCode) throws Exception;
	public List<SlipVO> selectAll(Pagevo pagevo) throws Exception;
	public int totalCountPV() throws Exception;
	public void psInsert(SlipVO slipvo)throws Exception;

	List<SlipVO> selectpvName(Map<String, Object> params);

	// 작성자명으로 검색된 총 개수
	int countpvName(String pvName);

	// 거래처명으로 검색
	List<SlipVO> selectpvCmpy(Map<String, Object> params);

	// 거래처명으로 검색된 총 개수
	int countpvCmpy(String pvCmpy);

	// 타입별 전표 목록 조회
	List<SlipVO> selectByType(Map<String, Object> params);

	// 타입별 전표 개수 조회
	int countByType(String pvslipCode) throws Exception;

	public void delpvSlip(int pvCode) throws Exception;

	public void rgInsert(SlipVO slipvo) throws Exception;

	public List<SliprgVO> selectrgAll(Pagevo pagevo) throws Exception;

	public int totalCountRG() throws Exception;

	List<SliprgVO> selectrgName(Map<String, Object> params) throws Exception;

	int countrgName(String pvName) throws Exception;

	List<SliprgVO> selectrgCmpy(Map<String, Object> params) throws Exception;

	int countrgCmpy(String rgCmpy) throws Exception;

	List<SliprgVO> selectByrgType(Map<String, Object> params);

	int countByrgType(String rgslipCode);

	int productSales(DateRangeVO dvo) throws Exception;

	int serviceSales(DateRangeVO dvo) throws Exception;

	int otherSales(DateRangeVO dvo) throws Exception;

	int interestSales(DateRangeVO dvo) throws Exception;

	int supplyCost(DateRangeVO dvo) throws Exception;

	int shippingCost(DateRangeVO dvo) throws Exception;

	int salaryCost(DateRangeVO dvo) throws Exception;

	int wageCost(DateRangeVO dvo) throws Exception;

	int mechanicalCost(DateRangeVO dvo) throws Exception;

	int inventoryCost(DateRangeVO dvo) throws Exception;

	int marketingCost(DateRangeVO dvo) throws Exception;

	int printCost(DateRangeVO dvo) throws Exception;

	int sellingCost(DateRangeVO dvo) throws Exception;

	int maintainCost(DateRangeVO dvo) throws Exception;

	int otherCost(DateRangeVO dvo) throws Exception;

	int depreciationCost(DateRangeVO dvo) throws Exception;

	int stermDebt(DateRangeVO dvo) throws Exception;

	int ltermDebt(DateRangeVO dvo) throws Exception;

	int payableCost(DateRangeVO dvo) throws Exception;

	int payableWage(DateRangeVO dvo) throws Exception;

//	int SCAL(DateRangeVO dvo) throws Exception;

	int sumupVAT(DateRangeVO dvo) throws Exception;

	int grossProfit(DateRangeVO dvo) throws Exception;

	int operatingIncome(DateRangeVO dvo) throws Exception;

	int netIncome(DateRangeVO dvo) throws Exception;
}
