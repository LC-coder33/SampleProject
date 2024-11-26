package com.example.practice.repository;


import com.example.practice.vo.RevenueVO;
import com.example.practice.vo.SlipVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface IF_AccountDao {

	public void insertOne(RevenueVO rvo)throws Exception;
//	public RevenueVO selectOne(String revenueCode) throws Exception;
	public List<SlipVO> selectAll() throws Exception;
	public void psInsert(SlipVO slipvo)throws Exception;
}
