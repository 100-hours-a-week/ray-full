package com.example.spring_practice.domain.member.dto;

import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class EditProfileRequestDto {
    private String nickname;
    private MultipartFile profileImage;
}
