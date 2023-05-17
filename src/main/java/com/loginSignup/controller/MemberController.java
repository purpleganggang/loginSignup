package com.loginSignup.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import lombok.RequiredArgsConstructor;

@RequestMapping("/members")
@Controller
@RequiredArgsConstructor
public class MemberController {

	
	/**
	 * 회원가입 폼페이지
	 */
    @GetMapping(value = "/join")
    public String memberForm(Model model){
        return "member/memberForm";
    }


    /**
     * 로그인 페이지
     */
    @GetMapping(value = "/login")
    public String loginMember(){
        return "/member/memberLoginForm";
    }


}
