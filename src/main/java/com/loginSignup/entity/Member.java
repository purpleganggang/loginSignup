package com.loginSignup.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Comment;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.loginSignup.constant.Role;
import com.loginSignup.dto.MemberFormDto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="member")
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @Column(name="member_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Comment("회원아이디")
    private Long id;

    @Comment("회원이름")
    private String name;

    @Column(nullable = false,unique = true)
    @Comment("이메일")
    private String email; 
    
    @Comment("비밀번호")
    private String password;

    @Comment("주소")
    private String address;

    @Enumerated(EnumType.STRING)
    private Role role;
    
    
    public void passwordEncode(String password , PasswordEncoder passwordEncoder) {
    	this.password=passwordEncoder.encode(password);
    }
    
    public static Member createMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder, Role state){
    	return Member.builder()
    	.name(memberFormDto.getName())
    	.email(memberFormDto.getEmail())
    	.address(memberFormDto.getAddress())
        .password(passwordEncoder.encode(memberFormDto.getPassword()))
        .role(state)
    	.build();

    }
    

}
