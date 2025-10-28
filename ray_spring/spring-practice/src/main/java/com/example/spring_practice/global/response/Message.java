package com.example.spring_practice.global.response;

public class Message {
    // users
    public static final String SIGNUP_SUCCESS = "회원가입 성공하였습니다.";
    public static final String LOGIN_SUCCESS = "로그인 성공하였습니다.";
    public static final String GET_MY_PROFILE_SUCCESS = "내 프로필 불러오기 성공하였습니다.";
    public static final String EDIT_PROFILE_SUCCESS = "프로필 수정 성공하였습니다.";
    public static final String EDIT_PASSWORD_SUCCESS = "비밀번호 수정 성공하였습니다.";
    public static final String CHECK_DUPLICATE_EMAIL_SUCCESS = "이메일 중복 체크 성공하였습니다.";
    public static final String CHECK_DUPLICATE_NICKNAME_SUCCESS = "닉네임 중복 체크 성공하였습니다.";


    // post
    public static final String  GET_POST_LIST_SUCCESS= "게시글 목록 불러오기 성공하였습니다.";
    public static final String  GET_POST_DETAIL_SUCCESS = "게시글 상세보기 성공하였습니다.";
    public static final String  POST_POST_SUCCESS = "게시글 작성 성공하였습니다.";
    public static final String  PATCH_POST_SUCCESS = "게시글 수정 성공하였습니다.";
    public static final String  DELETE_POST_SUCCESS = "게시글 삭제 성공하였습니다.";
    public static final String POST_LIKE_SUCCESS = "게시글 좋아요 성공하였습니다.";
    public static final String POST_UNLIKE_SUCCESS = "게시글 좋아요 취소 성공하였습니다.";

    // comment
    public static final String  POST_COMMENT_SUCCESS = "댓글 작성 성공하였습니다.";
    public static final String  PATCH_COMMENT_SUCCESS = "댓글 수정 성공하였습니다.";
    public static final String  DELETE_COMMENT_SUCCESS = "댓글 삭제 성공하였습니다.";
}
