package com.example.spring_practice.domain.post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class PostSummaryResponseDto {
    private Long postId;
    private String title;
    private LocalDateTime postedTime;
    private String author;
    private long likeCount;
    private long viewCount;
    private long commentCount;
}
