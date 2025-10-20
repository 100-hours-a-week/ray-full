package com.example.spring_practice.domain.member.repository;

import com.example.spring_practice.domain.member.entity.Member;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class MemberRepositoryImpl implements MemberRepository {
    private List<Member> memberList = new ArrayList<>();
    @Override
    public Member save(Member entity) {
        Long newId = memberList.isEmpty() ? 1L :
                memberList.stream()
                        .mapToLong(Member::getMemberId)
                        .max()
                        .orElse(0L) + 1;
        entity.setMemberId(newId);
        memberList.add(entity);
        return entity;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return memberList.stream()
                .filter(member -> member.getMemberId().equals(id))
                .findFirst();
    }

    @Override
    public List<Member> findAll() {
        return memberList;
    }

    @Override
    public void delete(Member entity) {
        memberList.remove(entity);
    }

    @Override
    public Optional<Member> findByEmail(String email) {
        return memberList.stream()
                .filter(member -> member.getEmail() != null && member.getEmail().equals(email))
                .findFirst();
    }

    @Override
    public boolean existsByEmail(String email) {
        if (memberList.isEmpty()) return false;
        for (Member member:memberList
             ) {
            if(member.getEmail().equals(email)) return true;
        }
        return false;
    }

    @Override
    public boolean existsByNickname(String nickname) {
        if (memberList.isEmpty()) return false;
        for (Member member:memberList
        ) {
            if(member.getNickname().equals(nickname)) return true;
        }
        return false;
    }
}
