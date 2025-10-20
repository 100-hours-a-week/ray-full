package com.example.spring_practice.domain.post.entity;

import com.example.spring_practice.domain.comment.entity.Comment;
import com.example.spring_practice.domain.member.entity.Member;
import com.example.spring_practice.domain.post.dto.PostRequestDto;
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
        this.postId = id;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public void setContent(String content){
        this.content = content;
    }
    public void setImgUrl(String imgUrl){
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
