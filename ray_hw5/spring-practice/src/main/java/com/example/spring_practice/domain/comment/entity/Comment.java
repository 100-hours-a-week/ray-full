package com.example.spring_practice.domain.comment.entity;

import com.example.spring_practice.domain.comment.dto.CommentRequestDto;
import com.example.spring_practice.domain.member.entity.Member;
import com.example.spring_practice.domain.post.entity.Post;
import com.example.spring_practice.global.response.CustomException;
import com.example.spring_practice.global.response.ErrorCode;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Comment {

    private Long commentId;
    private String content;
    private LocalDateTime createdAt;
    private boolean deleted = false;
    private Member member;
    private Post post;

    public Comment(CommentRequestDto commentRequestDto, Member member, Post post){
        this.content = commentRequestDto.getContent();
        this.createdAt = LocalDateTime.now();
        this.member = member;
        this.post = post;
    }
    public void updateContent(String content){
        this.content = content;
    }
    public void setCommentId(Long id){
        if( this.commentId != null || id == null || id < 0){
            throw new CustomException(ErrorCode.BAD_ID_SET_TRY);
        }
        this.commentId = id;
    }
}
