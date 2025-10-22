package com.example.spring_practice.domain.post.dto;

import com.example.spring_practice.domain.comment.dto.CommentDtoConverter;
import com.example.spring_practice.domain.comment.dto.CommentResponseDto;
import com.example.spring_practice.domain.comment.entity.Comment;
import com.example.spring_practice.domain.post.entity.Post;
import com.example.spring_practice.domain.shared.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class PostDtoConverter {
    private final ImageService imageService;
    private final CommentDtoConverter commentDtoConverter;
    public PostSummaryResponseDto toPostSummaryResponseDto(Post post){
        return new PostSummaryResponseDto(
                post.getPostId(),
                post.getTitle(),
                post.getCreatedAt(),
                post.getMember().getNickname(),
                post.getPostLikeList().size(),
                post.getViewCount(),
                post.getCommentList().size());
    }
    public PostDetailsResponseDto toPostDetailsResponseDto(Post post, Long currentMemberId){
        List<CommentResponseDto> commentResponseDtoList = new ArrayList<>();
        for (Comment comment :post.getCommentList()) {
            commentResponseDtoList.add(commentDtoConverter.toCommentResponseDto(comment, currentMemberId));
        }
        return new PostDetailsResponseDto(
                imageService.getFullImgUrl(post.getImgUrl()),
                post.getContent(),
                post.getMember().getMemberId().equals(currentMemberId),
                commentResponseDtoList
        );
    }
    public PostResponseDto toPostResponseDto(Post post, Long currentMemberId){
        return new PostResponseDto(
                toPostSummaryResponseDto(post),
                toPostDetailsResponseDto(post, currentMemberId)
        );
    }

    public PostIdResponseDto toPostIdResponseDto(Long postId){
        return new PostIdResponseDto(postId);
    }
}
