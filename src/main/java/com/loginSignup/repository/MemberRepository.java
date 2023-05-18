package com.loginSignup.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.loginSignup.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByEmail(String email);
}
