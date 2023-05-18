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
        return "member/memberLoginForm";
    }

    
    
    /**
     * 로그인 실패시 이동할 URL 을 설정
     */
    @GetMapping(value = "/login/error")
    public String loginError(Model model){
        model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해 주세요.");
        return "member/memberLoginForm";
    }


}
