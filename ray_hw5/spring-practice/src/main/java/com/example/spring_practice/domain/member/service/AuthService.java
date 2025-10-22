package com.example.spring_practice.domain.member.service;

import com.example.spring_practice.domain.member.dto.JwtTokenResponseDto;
import com.example.spring_practice.domain.member.dto.LoginRequestDto;
import com.example.spring_practice.domain.member.dto.MemberDtoConverter;
import com.example.spring_practice.domain.member.dto.SignUpRequestDto;
import com.example.spring_practice.domain.member.entity.Member;
import com.example.spring_practice.domain.member.repository.MemberRepository;
import com.example.spring_practice.domain.shared.ImageService;
import com.example.spring_practice.global.security.AuthContext;
import com.example.spring_practice.global.security.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthService {
    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;
    private final ImageService imageService;
    public void signUp(SignUpRequestDto signUpRequestDto) {
        Member member = new Member(signUpRequestDto.getEmail(), signUpRequestDto.getPassword(), signUpRequestDto.getNickname());
        if(signUpRequestDto.getProfileImage()!=null){
            member.setProfileImgUrl(imageService.saveImg(signUpRequestDto.getProfileImage()));
        }
        memberRepository.save(member);
    }

    public JwtTokenResponseDto login(LoginRequestDto loginRequestDto) {
        Optional<Member> member = memberRepository.findByEmail(loginRequestDto.getEmail());

        if (member.isEmpty()) {
            throw new RuntimeException("미회원가입");
        }

        if (!member.get().getPassword().equals(loginRequestDto.getPassword())) {
            throw new RuntimeException("비밀번호 틀림");
        }

        String token = jwtUtil.generateToken(member.get().getEmail());
        return MemberDtoConverter.toJwtTokenResponseDto(token);
    }

    public Member getCurrentMember() {
        String email = AuthContext.getCurrentUserEmail();

        if (email == null) {
            throw new RuntimeException("로그인이 필요합니다");
        }

        Optional<Member> member = memberRepository.findByEmail(email);

        if (member.isEmpty()) {
            throw new RuntimeException("사용자를 찾을 수 없습니다");
        }

        return member.get();
    }

}
