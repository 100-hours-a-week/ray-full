package com.example.spring_practice.domain.member.repository;

import com.example.spring_practice.domain.member.entity.Member;
import com.example.spring_practice.domain.shared.Repository;

import java.util.Optional;

public interface MemberRepository extends Repository<Member> {
    public Optional<Member> findByEmail(String email);
    public boolean existsByEmail(String email);
    public boolean existsByNickname(String nickname);
}
