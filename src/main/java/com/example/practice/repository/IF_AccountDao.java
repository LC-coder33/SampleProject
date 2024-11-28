package com.example.practice.repository;

import com.example.practice.vo.Pagevo;
import com.example.practice.vo.RevenueVO;
import com.example.practice.vo.SlipVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Mapper
@Repository
public interface IF_AccountDao {

	public void insertOne(RevenueVO rvo)throws Exception;
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
}
