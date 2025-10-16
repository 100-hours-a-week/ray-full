package com.example.spring_practice.domain.member.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class SignUpRequestDto {
    private String email;
    private String password;
    private String nickname;
    private MultipartFile profileImage;
}
