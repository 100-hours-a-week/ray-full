package com.example.spring_practice.domain.member.service;

import com.example.spring_practice.domain.member.dto.JwtTokenResponseDto;
import com.example.spring_practice.domain.member.dto.LoginRequestDto;
import com.example.spring_practice.domain.member.dto.MemberDtoConverter;
import com.example.spring_practice.domain.member.dto.SignUpRequestDto;
import com.example.spring_practice.domain.member.entity.Member;
import com.example.spring_practice.domain.member.repository.MemberRepository;
import com.example.spring_practice.domain.shared.ImageService;
import com.example.spring_practice.global.response.CustomException;
import com.example.spring_practice.global.response.ErrorCode;
import com.example.spring_practice.global.security.AuthContext;
import com.example.spring_practice.global.security.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {
    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;
    private final MemberDtoConverter memberDtoConverter;

    public JwtTokenResponseDto login(LoginRequestDto loginRequestDto) {
        Member member = memberRepository.findByEmail(loginRequestDto.getEmail())
                .orElseThrow(()->new CustomException(ErrorCode.MEMBER_NOT_FOUND));

        if (!member.getPassword().equals(loginRequestDto.getPassword())) {
            throw new CustomException(ErrorCode.INVALID_PASSWORD);
        }

        String token = jwtUtil.generateToken(member.getEmail());
        return memberDtoConverter.toJwtTokenResponseDto(token);
    }

    public Member getCurrentMember() {
        String email = AuthContext.getCurrentUserEmail();

        if (email == null) {
            throw new CustomException(ErrorCode.UNAUTHORIZED);
        }

        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
    }

}
