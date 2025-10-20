package com.example.spring_practice.domain.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DuplicateCheckResponseDto {
    private boolean isDuplicated;
}
