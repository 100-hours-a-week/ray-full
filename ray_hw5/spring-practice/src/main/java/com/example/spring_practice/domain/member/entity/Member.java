package com.example.spring_practice.domain.member.entity;

import com.example.spring_practice.global.response.CustomException;
import com.example.spring_practice.global.response.ErrorCode;
import lombok.Getter;

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
        if( this.memberId != null || id == null || id < 0){
            throw new CustomException(ErrorCode.BAD_ID_SET_TRY);
        }
        this.memberId = id;
    }

    public void updateImageUrl(String profileImgUrl) { this.profileImgUrl = profileImgUrl; }

    public void changePassword(String password) {

        this.password = password;
    }

    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }

}
