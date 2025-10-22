package com.example.spring_practice.domain.post.entity;

import com.example.spring_practice.domain.comment.entity.Comment;
import com.example.spring_practice.domain.member.entity.Member;
import com.example.spring_practice.domain.post.dto.PostRequestDto;
import com.example.spring_practice.global.response.CustomException;
import com.example.spring_practice.global.response.ErrorCode;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class Post {
    private Long postId;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private boolean deleted = false;
    private Member member;
    private List<Comment> commentList = new ArrayList<>();
    private List<PostLike> postLikeList = new ArrayList<>();
    private long viewCount = 0;
    private String imgUrl;

    public void setPostId(Long id){
        if( this.postId != null || id == null || id < 0){
            throw new CustomException(ErrorCode.BAD_ID_SET_TRY);
        }

        this.postId = id;
    }
    public void updateTitle(String title){
        this.title = title;
    }
    public void updateContent(String content){
        this.content = content;
    }
    public void updateImageUrl(String imgUrl){
        this.imgUrl = imgUrl;
    }

    public void increaseViewCount(){
        this.viewCount++;
    }

    public void addComment(Comment comment) {
        this.commentList.add(comment);
    }

    public void deleteComment(Comment comment){
        this.commentList.remove(comment);
    }

    public Post(PostRequestDto postRequestDto, Member member){
        this.title = postRequestDto.getTitle();
        this.content = postRequestDto.getContent();
        this.createdAt = LocalDateTime.now();
        this.member = member;
    }
}
