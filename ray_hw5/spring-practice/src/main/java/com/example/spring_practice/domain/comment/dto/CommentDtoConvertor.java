package com.example.spring_practice.domain.comment.dto;

import com.example.spring_practice.domain.comment.dto.CommentResponseDto;
import com.example.spring_practice.domain.comment.entity.Comment;

public class CommentDtoConvertor {
    public static CommentResponseDto toCommentResponseDto(Comment comment, Long currentMemberId){
        return new CommentResponseDto(
                comment.getCommentId(),
                comment.getMember().getNickname(),
                comment.getCreatedAt(),
                comment.getContent(),
                comment.getMember().getMemberId().equals(currentMemberId));
    }

    public static CommentIdResponseDto toCommentIdResponseDto(Long commentId){
        return new CommentIdResponseDto(commentId);
    }
}
