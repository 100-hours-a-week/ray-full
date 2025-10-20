package com.example.spring_practice.domain.member.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class JwtTokenResponseDto {
    @Schema(description = "액세스 토큰", example = "ewoienvoweot...")
    private String accessToken;
}
