package com.example.spring_practice.domain.member.dto;

import com.example.spring_practice.domain.member.entity.Member;
import com.example.spring_practice.domain.shared.ImageService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MemberDtoConverter {
    private final ImageService imageService;
    public ProfileResponseDto toProfileResponseDto(Member member){
        return new ProfileResponseDto(member.getEmail(), member.getNickname(), imageService.getFullImgUrl(member.getProfileImgUrl()));
    }

    public DuplicateCheckResponseDto toDuplicateCheckResponseDto(boolean isDuplicate){
        return new DuplicateCheckResponseDto(isDuplicate);
    }

    public JwtTokenResponseDto toJwtTokenResponseDto(String accessToken){
        return new JwtTokenResponseDto(accessToken);
    }
}
