package com.example.practice.controller;

import com.example.practice.service.IF_AccountService;
import com.example.practice.vo.SlipVO;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class AccountController {

    private final IF_AccountService accountservice;

    @GetMapping
    public ModelAndView index() throws Exception {
        accountservice.selectAll();
        System.out.println(accountservice.selectAll());
//        System.out.println(accountservice.selectOne("1110"));
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }

    @PostMapping("pSlip")
    public void psInsert(@ModelAttribute SlipVO slipvo, HttpServletResponse response) throws Exception {
        System.out.println("ready");
        accountservice.psInsert(slipvo);

        response.sendRedirect("/");
    }

}
