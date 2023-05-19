package com.loginSignup.constant;

public enum Role {
 
	/** 스프링부터ㅡ 2.7 이상 ROLE_ 추가하면은 안된다. :  ROLE_ 추가하면은  "ROLE_로 시작할 수 없습니다(자동으로 추가됨) " 오류 
	 * 
	 * 따라서, Role 설정에서는 ROLE_ 생략하지만 SecurityConfig 에서 권한설정할때에는 ROLE_  붙여줘야한다.
	 *  
	 *   */
	GUEST ,    //손님
	USER  ,    //일반사용자
	MANAGER ,  //중간관리자
	ADMIN ;    //촤고관리자
}
