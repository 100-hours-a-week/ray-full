package com.example.spring_practice.domain.member.repository;

import com.example.spring_practice.domain.member.entity.Member;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class MemberRepositoryImpl implements MemberRepository {
    private final ConcurrentHashMap<Long, Member> members = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong(1L);

    @Override
    public Member save(Member member) {
        if (member.getMemberId() == null) {
            // ID가 없으면 새로 생성 (INSERT)
            Long newId = sequence.getAndIncrement();
            member.setMemberId(newId);
            members.put(newId, member);
        } else {
            // ID가 있으면 업데이트 (UPDATE)
            members.put(member.getMemberId(), member);
        }
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(members.get(id));
    }


    @Override
    public Optional<Member> findByEmail(String email) {
        return members.values().stream()
                .filter(m -> m.getEmail().equals(email))
                .findFirst();
    }

    @Override
    public boolean existsByEmail(String email) {
        return members.values().stream()
                .anyMatch(m -> m.getEmail().equals(email));
    }

    @Override
    public boolean existsByNickname(String nickname) {
        return members.values().stream()
                .anyMatch(m -> m.getNickname().equals(nickname));
    }

    @Override
    public void deleteById(Long id) {
        members.remove(id);
    }

    @Override
    public void delete(Member member) {
        members.remove(member.getMemberId());
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(members.values());
    }

}
