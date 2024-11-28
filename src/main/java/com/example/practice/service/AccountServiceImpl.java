package com.example.practice.service;

import com.example.practice.repository.IF_AccountDao;
import com.example.practice.vo.Pagevo;
import com.example.practice.vo.RevenueVO;
import com.example.practice.vo.SlipVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service    // 해당 클래스를 객체로 만들어라..
@RequiredArgsConstructor
public class AccountServiceImpl implements IF_AccountService{

	private final IF_AccountDao adao;

	@Override
	public void join(RevenueVO rvo) throws Exception {
		System.out.println("join 서비스");
		// 아이디 검사 등 중복체크 할 수 있다.. <생략>
		// dao에게 작업 지시해야 한다... 
		// 실제 데이터를 저장하도록 지시한다...
		adao.insertOne(rvo);
	}

	@Override
	public List<SlipVO> selectAll(Pagevo pagevo) throws Exception {
		List<SlipVO> list = adao.selectAll(pagevo);
		for(SlipVO s : list) {
			String date = s.getPvDate();
			s.setPvDate(date.substring(0,10));
		}
		return list;
	}

	@Override
	public int totalCountPV() throws Exception {
		return adao.totalCountPV();
	}
//	@Override
//	public RevenueVO selectOne(String revenueCode) throws Exception {
//		// TODO Auto-generated method stub
//		return adao.selectOne(revenueCode);
//	}

	@Override
	public void psInsert(SlipVO slipvo) throws Exception {
		System.out.println("성공1");
        switch (slipvo.getPvslipCode()) {
            case "sales" -> slipvo.setPvslipCode("매출");
            case "cost" -> slipvo.setPvslipCode("비용");
            case "asset" -> slipvo.setPvslipCode("자산");
            case "liability" -> slipvo.setPvslipCode("부채");
        }
		switch (slipvo.getPvPay()) {
			case "cash" -> slipvo.setPvPay("현금");
			case "card" -> slipvo.setPvPay("카드");
			case "bank" -> slipvo.setPvPay("계좌이체");
			case "note" -> slipvo.setPvPay("어음");
		}
		adao.psInsert(slipvo);
		System.out.println("성공");
	}
	@Override
	public List<SlipVO> selectpvName(Pagevo pagevo, String pvName) throws Exception {
		Map<String, Object> params = new HashMap<>();
		params.put("startNO", pagevo.getStartNo());
		params.put("endNO", pagevo.getEndNo());
		params.put("pvName", pvName);
		return adao.selectpvName(params);
	}

	@Override
	public List<SlipVO> selectpvCmpy(Pagevo pagevo, String pvCmpy) throws Exception {
		Map<String, Object> params = new HashMap<>();
		params.put("startNO", pagevo.getStartNo());
		params.put("endNO", pagevo.getEndNo());
		params.put("pvCmpy", pvCmpy);
		return adao.selectpvCmpy(params);
	}

	@Override
	public int countpvName(String pvName) throws Exception {
		return adao.countpvName(pvName);
	}

	@Override
	public int countpvCmpy(String pvCmpy) throws Exception {
		return adao.countpvCmpy(pvCmpy);
	}

	@Override
	public List<SlipVO> selectByType(Pagevo pagevo, String pvslipCode) throws Exception {
        switch (pvslipCode) {
            case "sales" -> pvslipCode = "매출";
            case "cost" -> pvslipCode = "비용";
            case "asset" -> pvslipCode = "자산";
            case "liability" -> pvslipCode = "부채";
        }
		Map<String, Object> params = new HashMap<>();
		params.put("startNO", pagevo.getStartNo());
		params.put("endNO", pagevo.getEndNo());
		params.put("pvslipCode", pvslipCode);
		return adao.selectByType(params);
	}

	@Override
	public int countByType(String pvslipCode) throws Exception {
		return adao.countByType(pvslipCode);
	}

}
