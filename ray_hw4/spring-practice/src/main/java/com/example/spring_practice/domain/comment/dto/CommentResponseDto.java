package com.example.spring_practice.domain.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CommentResponseDto {
    private Long commentId;
    private String author;
    private LocalDateTime postedTime;
    private String content;
    private boolean isMine;
}
