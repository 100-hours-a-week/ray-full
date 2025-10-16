package com.example.spring_practice.domain.post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@Getter
public class PostRequestDto {
    private String title;
    private String content;
    private MultipartFile postImage;
}
