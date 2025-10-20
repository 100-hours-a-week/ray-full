package com.example.spring_practice.domain.post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PostResponseDto {
    private PostSummaryResponseDto postSummary;
    private PostDetailsResponseDto postDetails;
}
