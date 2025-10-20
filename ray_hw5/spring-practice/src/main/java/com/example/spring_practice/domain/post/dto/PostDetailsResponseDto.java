package com.example.spring_practice.domain.post.dto;

import com.example.spring_practice.domain.comment.dto.CommentResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class PostDetailsResponseDto {
    private String imgUrl;
    private String content;
    private boolean isMine;
    private List<CommentResponseDto> comments;
}
