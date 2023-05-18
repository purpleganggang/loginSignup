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

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="member")
@Getter
@Setter
@ToString
public class Member {

    @Id
    @Column(name="member_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Comment("회원아이디")
    private Long id;

    @Comment("회원이름")
    private String name;

    @Column(unique = true)
    @Comment("이메일")
    private String email; 
    
    @Comment("비밀번호")
    private String password;

    @Comment("주소")
    private String address;

    @Enumerated(EnumType.STRING)
    private Role role;
    
    public static Member createMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder, Role state){
        Member member=new Member();
        member.setName(memberFormDto.getName());
        member.setEmail(memberFormDto.getEmail());
        member.setAddress(memberFormDto.getAddress());
        String password=passwordEncoder.encode(memberFormDto.getPassword());
        member.setPassword(password);
        member.setRole(state);
        return member;
    }
    




}
