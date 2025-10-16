package com.example.spring_practice.domain.member.entity;

import com.example.spring_practice.domain.comment.entity.Comment;
import com.example.spring_practice.domain.post.entity.Post;
import com.example.spring_practice.domain.post.entity.PostLike;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Member {
    private Long memberId;
    private String email;
    private String password;
    private String nickname;
    private String profileImgUrl;

    public Member(String email, String password, String nickname){
        this.email = email;
        this.password = password;
        this.nickname = nickname;
    }

    public void setMemberId(Long id){
        this.memberId = id;
    }
    public void setProfileImgUrl(String profileImgUrl) { this.profileImgUrl = profileImgUrl; }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
