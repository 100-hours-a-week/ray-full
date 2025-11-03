package com.example.spring_practice.domain.member.entity;

import com.example.spring_practice.domain.comment.entity.Comment;
import com.example.spring_practice.domain.post.entity.Post;
import com.example.spring_practice.domain.post.entity.PostLike;
import com.example.spring_practice.global.response.CustomException;
import com.example.spring_practice.global.response.ErrorCode;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    private String email;

    private String password;

    private String nickname;

    private String profileImgUrl;

    @OneToMany(mappedBy = "member")
    private List<Post> postList = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Comment> commentList = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<PostLike> postLikeList = new ArrayList<>();

    public Member(String email, String password, String nickname){
        this.email = email;
        this.password = password;
        this.nickname = nickname;
    }

    public void updateImageUrl(String profileImgUrl) { this.profileImgUrl = profileImgUrl; }

    public void editPassword(String password) {
        String regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&#])[A-Za-z\\d@$!%*?&#]{8,20}$";
        if (password == null || !password.matches(regexp)) {
            throw new CustomException(ErrorCode.INVALID_PASSWORD_FORM);
        }
        this.password = password;
    }

    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }

}
