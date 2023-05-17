package com.loginSignup.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@RequestMapping("/admin")
@Controller
@RequiredArgsConstructor
public class AdminController {

	/**
	 * 관리자페이지
	 */
    @GetMapping(value = "")
    public String main(){
        return "admin/index";
    }
    

}
