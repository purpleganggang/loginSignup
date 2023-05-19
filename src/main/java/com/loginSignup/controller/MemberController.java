package com.loginSignup.controller;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.loginSignup.dto.MemberFormDto;
import com.loginSignup.entity.Member;
import com.loginSignup.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;


@RequestMapping("/members")
@Controller
@RequiredArgsConstructor
@Log4j2
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    
    private final ModelMapper modelMapper;
    
    @GetMapping(value = "/join")
    public String memberForm(Model model){
        model.addAttribute("memberFormDto", new MemberFormDto());
        return "member/memberForm";
    }


    @PostMapping(value = "/join")
    public String memberForm(@Valid MemberFormDto memberFormDto, BindingResult bindingResult, Model model , RedirectAttributes rttr){
        
    	log.info("1. fields.hasErrors :  {} " ,bindingResult.getFieldError());
    	/**1.어노테이션 설정에 의한 유효성 체크 */
    	if(bindingResult.hasErrors()) return "member/memberForm";
           
        
    	/**2.커스텀 설정에 따른 유효성 체크 */
        memberFormDto.memberValidate(bindingResult);
        
        log.info("2. fields.hasErrors : " ,bindingResult.getFieldError());
        if(bindingResult.hasErrors())return "member/memberForm";
 
        /**
         * 이메일 컬럼 unique 설정시 중복데이터가 입력될 경우 스프링부트에서 자동으로 
         * binding parameter 처리되어 "이미 가입된 회원입니다." 메시지가 출력 처리되어 진다.
         * thymeleaf 에서 errorMessage  로 출력
         */
        try{
           // Member member=Member.createMember(memberFormDto, passwordEncoder, Role.ROLE_USER);
        	
        	//1.직접 객체 변환처리 방법 :  Member entity 에 데이터 넣기 (MemberFormDto -> Member)
        	//Member member=Member.createMember(memberFormDto, passwordEncoder, memberFormDto.getRole());
        	      	
        	/**
        	 * 2.modelmapper 를 이용해서 Member entity 에 데이터 넣기 (MemberFormDto -> Member)
        	 * 1) modelmapper 라이브러리 등록 (https://mvnrepository.com/artifact/org.modelmapper/modelmapper )
        	 * 2) Application 에서 modelMapper() 를 Bean 등록 한다.
        	 * 3) 다음과 같이 사용한다.
        	 * https://modelmapper.org/getting-started/
        	 */
        	Member member= modelMapper.map(memberFormDto, Member.class);
        	member.passwordEncode(memberFormDto.getPassword(), passwordEncoder);
        	
            memberService.saveMember(member);
        }catch (IllegalStateException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "member/memberForm";
        }
        
        rttr.addFlashAttribute("msg","congrats");
        return "redirect:/members/login";
    }


    
    @GetMapping(value = "/login")
    public String loginMember(){
        return "member/memberLoginForm";
    }

    @GetMapping(value = "/login/error")
    public String loginError(Model model){
        model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해 주세요.");
        return "member/memberLoginForm";
    }

}
