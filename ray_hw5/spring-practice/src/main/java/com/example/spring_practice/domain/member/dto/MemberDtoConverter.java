package com.example.spring_practice.domain.member.dto;

import com.example.spring_practice.domain.member.entity.Member;
import com.example.spring_practice.domain.shared.ImageService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;

public class MemberDtoConverter {
    public static ProfileResponseDto toProfileResponseDto(Member member){
        return new ProfileResponseDto(member.getEmail(), member.getNickname(), ImageService.getFullImgUrl(member.getProfileImgUrl()));
    }

    public static DuplicateCheckResponseDto toDuplicateCheckResponseDto(boolean isDuplicate){
        return new DuplicateCheckResponseDto(isDuplicate);
    }

    public static JwtTokenResponseDto toJwtTokenResponseDto(String accessToken){
        return new JwtTokenResponseDto(accessToken);
    }
}
