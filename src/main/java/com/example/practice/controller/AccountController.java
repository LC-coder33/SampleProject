package com.example.practice.controller;

import com.example.practice.service.IF_AccountService;
import com.example.practice.vo.*;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class AccountController {

    private final IF_AccountService accountservice;


    @PostMapping("/pSlip")
    public ResponseEntity<String> psInsert(@ModelAttribute SlipVO slipvo) throws Exception {
        accountservice.psInsert(slipvo);
        return ResponseEntity.ok("successful");
    }
    @GetMapping("/slistView")
    public ModelAndView showSlipList(Model model, @ModelAttribute Pagevo pagevo) throws Exception {
        if(pagevo.getPage()==null) {
            pagevo.setPage(1);
        }

        pagevo.setTotalCount(accountservice.totalCountPV());

        List<SlipVO> slipList = accountservice.selectAll(pagevo);  // 전표 목록 가져오기
        ModelAndView mv = new ModelAndView("sliplist");  // sliplist.html 파일을 반환
        model.addAttribute("slips", slipList);  // 전표 목록을 "slips"라는 이름으로 모델에 추가
//        System.out.println(model.addAttribute("slips", slipList));
//        System.out.println("slipList size: " + slipList.size());
//        System.out.println("slipList content: " + slipList);
        return mv;
    }
    @GetMapping("/sliprgList")
    public ModelAndView showrgList(Model model, @ModelAttribute Pagevo pagevo) throws Exception {
        if(pagevo.getPage()==null) {
            pagevo.setPage(1);
        }
        pagevo.setTotalCount(accountservice.totalCountRG());

        List<SliprgVO> sliprgList = accountservice.selectrgAll(pagevo);
        ModelAndView mv = new ModelAndView("sliprglist");
        model.addAttribute("slips", sliprgList);
        return mv;
    }
    @GetMapping("/incomeStatement")
    public ModelAndView showIncomeStatement() throws Exception {
        ModelAndView mv = new ModelAndView("incomeStatement");
        return mv;
    }
    @GetMapping("/search")
    public ModelAndView search(@RequestParam String searchKeyword,
                               @RequestParam String pvtext,
                               @RequestParam(defaultValue = "1") Integer page,  // 페이지 파라미터 추가
                               Model model) throws Exception {

        Pagevo pagevo = new Pagevo();
        pagevo.setPage(page);  // 전달받은 페이지 번호 설정

        List<SlipVO> slipList;
        ModelAndView mv = new ModelAndView("sliplist");

        if(searchKeyword.equals("name")) {
            pagevo.setTotalCount(accountservice.countpvName(pvtext));
            slipList = accountservice.selectpvName(pagevo, pvtext);
        } else if(searchKeyword.equals("cmpy")) {
            pagevo.setTotalCount(accountservice.countpvCmpy(pvtext));
            slipList = accountservice.selectpvCmpy(pagevo, pvtext);
        } else {
            pagevo.setTotalCount(accountservice.totalCountPV());
            slipList = accountservice.selectAll(pagevo);
        }

        model.addAttribute("slips", slipList);
        model.addAttribute("pagevo", pagevo);
        // 검색 파라미터들도 뷰에 전달하여 페이지 이동 시 검색 조건 유지
        model.addAttribute("searchKeyword", searchKeyword);
        model.addAttribute("pvtext", pvtext);
        return mv;
    }

    @GetMapping("/slips")
    public ModelAndView filterByType(@RequestParam String pvslipCode,
                                     @RequestParam(defaultValue = "1") Integer page,
                                     Model model) throws Exception {
        Pagevo pagevo = new Pagevo();
        pagevo.setPage(page);  // 전달받은 페이지 번호 설정

        List<SlipVO> slipList;
        ModelAndView mv = new ModelAndView("sliplist");

        if(pvslipCode != null && !pvslipCode.isEmpty()) {
            pagevo.setTotalCount(accountservice.countByType(pvslipCode));
            slipList = accountservice.selectByType(pagevo, pvslipCode);
        } else {
            pagevo.setTotalCount(accountservice.totalCountPV());
            slipList = accountservice.selectAll(pagevo);
        }

        model.addAttribute("slips", slipList);
        model.addAttribute("pagevo", pagevo);
        model.addAttribute("pvslipCode", pvslipCode);  // 필터 조건 유지
        return mv;
    }

    @PostMapping(value="delpvSlip")
    public ResponseEntity<String> delpvSlip(@RequestParam("pvCode") int pvCode, HttpServletResponse response) throws Exception {
        accountservice.delpvSlip(pvCode);
        response.sendRedirect("slistView");
        return ResponseEntity.ok("Delete successful");
    }

    @PostMapping(value="rgInsert")
    public ResponseEntity<String> rgInsert(@ModelAttribute SlipVO slipvo, @RequestParam("pvCode") int pvCode, HttpServletResponse response) throws Exception {
        accountservice.rgInsert(slipvo);
        accountservice.delpvSlip(pvCode);
        response.sendRedirect("slistView");
        return ResponseEntity.ok("Insert successful");
    }

    @GetMapping("/searchrg")
    public ModelAndView searchrg(@RequestParam String searchKeyword,
                               @RequestParam String rgtext,
                               @RequestParam(defaultValue = "1") Integer page,  // 페이지 파라미터 추가
                               Model model) throws Exception {

        Pagevo pagevo = new Pagevo();
        pagevo.setPage(page);  // 전달받은 페이지 번호 설정

        List<SliprgVO> sliprgList;
        ModelAndView mv = new ModelAndView("sliprglist");

        if(searchKeyword.equals("name")) {
            pagevo.setTotalCount(accountservice.countrgName(rgtext));
            sliprgList = accountservice.selectrgName(pagevo, rgtext);
        } else if(searchKeyword.equals("cmpy")) {
            pagevo.setTotalCount(accountservice.countrgCmpy(rgtext));
            sliprgList = accountservice.selectrgCmpy(pagevo, rgtext);
        } else {
            pagevo.setTotalCount(accountservice.totalCountRG());
            sliprgList = accountservice.selectrgAll(pagevo);
        }

        model.addAttribute("slips", sliprgList);
        model.addAttribute("pagevo", pagevo);
        // 검색 파라미터들도 뷰에 전달하여 페이지 이동 시 검색 조건 유지
        model.addAttribute("searchKeyword", searchKeyword);
        model.addAttribute("rgtext", rgtext);
        return mv;
    }

    @GetMapping("/rgslips")
    public ModelAndView filterByrgType(@RequestParam String rgslipCode,
                                     @RequestParam(defaultValue = "1") Integer page,
                                     Model model) throws Exception {
        Pagevo pagevo = new Pagevo();
        pagevo.setPage(page);  // 전달받은 페이지 번호 설정

        List<SliprgVO> sliprgList;
        ModelAndView mv = new ModelAndView("sliprglist");

        if(rgslipCode != null && !rgslipCode.isEmpty()) {
            pagevo.setTotalCount(accountservice.countByrgType(rgslipCode));
            sliprgList = accountservice.selectByrgType(pagevo, rgslipCode);
        } else {
            pagevo.setTotalCount(accountservice.totalCountRG());
            sliprgList = accountservice.selectrgAll(pagevo);
        }

        model.addAttribute("slips", sliprgList);
        model.addAttribute("pagevo", pagevo);
        model.addAttribute("rgCode", rgslipCode);  // 필터 조건 유지
        return mv;
    }
//    @GetMapping("/getincomevalues")
//    public ModelAndView getIncomeStatement(@RequestParam(value = "startDate", defaultValue = "0000-01-01") String startDate,
//                                     @RequestParam(value = "endDate", defaultValue = "9999-12-31") String endDate, Model model) throws Exception {
//        DateRangeVO dvo = new DateRangeVO();
//        // String을 LocalDate로 parsing
//        LocalDate startLocalDate = LocalDate.parse(startDate);
//        LocalDate endLocalDate = LocalDate.parse(endDate);
//
//        // LocalDate를 java.sql.Date로 변환
//        dvo.setStartDate(Date.valueOf(startLocalDate));
//        dvo.setEndDate(Date.valueOf(endLocalDate));
//
//        ModelAndView mv = new ModelAndView("incomeStatement");
//
//        model.addAttribute("productSales", accountservice.productSales(dvo));
//        model.addAttribute("serviceSales", accountservice.serviceSales(dvo));
//        model.addAttribute("otherSales", accountservice.otherSales(dvo));
//        model.addAttribute("interestSales", accountservice.interestSales(dvo));
//        model.addAttribute("supplyCost", accountservice.supplyCost(dvo));
//        model.addAttribute("shippingCost", accountservice.shippingCost(dvo));
//        model.addAttribute("salaryCost", accountservice.salaryCost(dvo));
//        model.addAttribute("wageCost", accountservice.wageCost(dvo));
//        model.addAttribute("mechanicalCost", accountservice.mechanicalCost(dvo));
//        model.addAttribute("inventoryCost", accountservice.inventoryCost(dvo));
//        model.addAttribute("grossProfit", accountservice.grossProfit(dvo));
//        model.addAttribute("marketingCost", accountservice.marketingCost(dvo));
//        model.addAttribute("printCost", accountservice.printCost(dvo));
//        model.addAttribute("sellingCost", accountservice.sellingCost(dvo));
//        model.addAttribute("maintainCost", accountservice.maintainCost(dvo));
//        model.addAttribute("otherCost", accountservice.otherCost(dvo));
//        model.addAttribute("depreciationCost", accountservice.depreciationCost(dvo));
//        model.addAttribute("operatingIncome", accountservice.operatingIncome(dvo));
//        model.addAttribute("stermDebt", accountservice.stermDebt(dvo));
//        model.addAttribute("ltermDebt", accountservice.ltermDebt(dvo));
//        model.addAttribute("payableCost", accountservice.payableCost(dvo));
//        model.addAttribute("payableWage", accountservice.payableWage(dvo));
//        model.addAttribute("sumupVAT", accountservice.sumupVAT(dvo));
//        model.addAttribute("netIncome", accountservice.netIncome(dvo));
//
//        return mv;
//    }
@GetMapping("/getincomevalues")
public ResponseEntity<Map<String, Object>> getIncomeStatement(
        @RequestParam(value = "startDate", defaultValue = "0000-01-01") String startDate,
        @RequestParam(value = "endDate", defaultValue = "9999-12-31") String endDate) throws Exception {

    LocalDate startLocalDate = LocalDate.parse(startDate);
    LocalDate endLocalDate = LocalDate.parse(endDate);

    DateRangeVO dvo = new DateRangeVO();
    dvo.setStartDate(Date.valueOf(startLocalDate));
    dvo.setEndDate(Date.valueOf(endLocalDate));

    // Call service methods to get income statement data
    Map<String, Object> result = new HashMap<>();
    result.put("productSales", accountservice.productSales(dvo));
    result.put("serviceSales", accountservice.serviceSales(dvo));
    result.put("otherSales", accountservice.otherSales(dvo));
    result.put("interestSales", accountservice.interestSales(dvo));
    result.put("supplyCost", accountservice.supplyCost(dvo));
    result.put("shippingCost", accountservice.shippingCost(dvo));
    result.put("salaryCost", accountservice.salaryCost(dvo));
    result.put("wageCost", accountservice.wageCost(dvo));
    result.put("mechanicalCost", accountservice.mechanicalCost(dvo));
    result.put("inventoryCost", accountservice.inventoryCost(dvo));
    result.put("grossProfit", accountservice.grossProfit(dvo));
    result.put("marketingCost", accountservice.marketingCost(dvo));
    result.put("printCost", accountservice.printCost(dvo));
    result.put("sellingCost", accountservice.sellingCost(dvo));
    result.put("maintainCost", accountservice.maintainCost(dvo));
    result.put("otherCost", accountservice.otherCost(dvo));
    result.put("depreciationCost", accountservice.depreciationCost(dvo));
    result.put("operatingIncome", accountservice.operatingIncome(dvo));
    result.put("stermDebt", accountservice.stermDebt(dvo));
    result.put("ltermDebt", accountservice.ltermDebt(dvo));
    result.put("payableCost", accountservice.payableCost(dvo));
    result.put("payableWage", accountservice.payableWage(dvo));
    result.put("sumupVAT", accountservice.sumupVAT(dvo));
    result.put("netIncome", accountservice.netIncome(dvo));

    // Return the data as JSON response
    return ResponseEntity.ok(result);
}



}
