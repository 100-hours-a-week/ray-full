package com.example.spring_practice.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ProfileResponseDto {
    private String email;
    private String nickname;
    private String profileImage;
}
