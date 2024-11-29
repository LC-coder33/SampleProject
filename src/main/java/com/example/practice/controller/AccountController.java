package com.example.practice.controller;

import com.example.practice.service.IF_AccountService;
import com.example.practice.vo.Pagevo;
import com.example.practice.vo.SlipVO;
import com.example.practice.vo.SliprgVO;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class AccountController {

    private final IF_AccountService accountservice;


    @PostMapping("/pSlip")
    public ResponseEntity<String> psInsert(@ModelAttribute SlipVO slipvo, HttpServletResponse response) throws Exception {
        accountservice.psInsert(slipvo);
        response.sendRedirect("/");
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
}
