package com.example.spring_practice.domain.post.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PostResponseDto {
    private PostSummaryResponseDto postSummary;
    private PostDetailsResponseDto postDetails;
}
