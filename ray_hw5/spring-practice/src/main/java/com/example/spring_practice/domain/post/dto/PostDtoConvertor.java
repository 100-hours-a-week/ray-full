package com.example.spring_practice.domain.post.dto;

import com.example.spring_practice.domain.comment.dto.CommentDtoConvertor;
import com.example.spring_practice.domain.comment.dto.CommentResponseDto;
import com.example.spring_practice.domain.comment.entity.Comment;
import com.example.spring_practice.domain.post.entity.Post;
import com.example.spring_practice.domain.shared.ImageService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PostDtoConvertor {
    public static PostSummaryResponseDto toPostSummaryResponseDto(Post post){
        return new PostSummaryResponseDto(
                post.getPostId(),
                post.getTitle(),
                post.getCreatedAt(),
                post.getMember().getNickname(),
                post.getPostLikeList().size(),
                post.getViewCount(),
                post.getCommentList().size());
    }
    public static PostDetailsResponseDto toPostDetailsResponseDto(Post post, Long currentMemberId){
        List<CommentResponseDto> commentResponseDtoList = new ArrayList<>();
        for (Comment comment :post.getCommentList()) {
            commentResponseDtoList.add(CommentDtoConvertor.toCommentResponseDto(comment, currentMemberId));
        }
        return new PostDetailsResponseDto(
                ImageService.getFullImgUrl(post.getImgUrl()),
                post.getContent(),
                post.getMember().getMemberId().equals(currentMemberId),
                commentResponseDtoList
        );
    }
    public static PostResponseDto toPostResponseDto(Post post, Long currentMemberId){
        return new PostResponseDto(
                toPostSummaryResponseDto(post),
                toPostDetailsResponseDto(post, currentMemberId)
        );
    }

    public static PostIdResponseDto toPostIdResponseDto(Long postId){
        return new PostIdResponseDto(postId);
    }
}
