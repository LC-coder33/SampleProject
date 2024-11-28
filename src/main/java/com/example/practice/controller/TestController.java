package com.example.practice.controller;

import com.example.practice.service.IF_MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
    @Autowired
    IF_MemberService memberservice;

    // 홈 화면
    @GetMapping("/home")
    public String index() throws Exception {
        return "home";
    }

    // 뒤로가기
    @GetMapping("/back")
    public String back() throws Exception {
        return "home";
    }
}
