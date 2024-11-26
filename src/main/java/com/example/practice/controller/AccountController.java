package com.example.practice.controller;

import com.example.practice.service.IF_AccountService;
import com.example.practice.vo.SlipVO;
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
        System.out.println("success");
        response.sendRedirect("/");
        return ResponseEntity.ok("successful");
    }
    @GetMapping("/slistView")
    public ModelAndView showSlipList(Model model) throws Exception {
        List<SlipVO> slipList = accountservice.selectAll();  // 전표 목록 가져오기
        ModelAndView mv = new ModelAndView("sliplist");  // sliplist.html 파일을 반환
        model.addAttribute("slips", slipList);  // 전표 목록을 "slips"라는 이름으로 모델에 추가
        System.out.println(slipList);
        return mv;
    }
}
