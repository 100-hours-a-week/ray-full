package com.example.spring_practice.domain.comment.dto;

import com.example.spring_practice.domain.comment.entity.Comment;
import org.springframework.stereotype.Component;

@Component
public class CommentDtoConverter {
    public CommentResponseDto toCommentResponseDto(Comment comment, Long currentMemberId){
        return new CommentResponseDto(
                comment.getCommentId(),
                comment.getMember().getNickname(),
                comment.getCreatedAt(),
                comment.getContent(),
                comment.getMember().getMemberId().equals(currentMemberId));
    }

    public CommentIdResponseDto toCommentIdResponseDto(Long commentId){
        return new CommentIdResponseDto(commentId);
    }
}
