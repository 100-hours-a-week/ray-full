package com.example.spring_practice.domain.comment.controller;

import com.example.spring_practice.domain.comment.dto.CommentIdResponseDto;
import com.example.spring_practice.domain.comment.dto.CommentRequestDto;
import com.example.spring_practice.domain.comment.entity.Comment;
import com.example.spring_practice.domain.comment.service.CommentService;
import com.example.spring_practice.domain.member.service.MemberService;
import com.example.spring_practice.global.response.ApiResponse;
import com.example.spring_practice.global.response.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts/{postId}/comments")
public class CommentController {

    private final CommentService commentService;
    private final MemberService memberService;

    // 댓글 등록
    @PostMapping
    public ResponseEntity<ApiResponse<CommentIdResponseDto>> createComment(
            @PathVariable Long postId,
            @RequestBody CommentRequestDto commentRequestDto) {

        Long currentMemberId = memberService.getCurrentMember().getMemberId();
        CommentIdResponseDto response = commentService.createComment(postId, commentRequestDto, currentMemberId);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(Message.POST_COMMENT_SUCCESS, response));
    }

    // 댓글 수정
    @PatchMapping("/{commentId}")
    public ResponseEntity<ApiResponse<CommentIdResponseDto>> editComment(
            @PathVariable Long postId,
            @PathVariable Long commentId,
            @RequestBody CommentRequestDto commentRequestDto) {

        Long currentMemberId = memberService.getCurrentMember().getMemberId();
        CommentIdResponseDto response = commentService.updateComment(commentId, commentRequestDto, currentMemberId);

        return ResponseEntity.ok(
                ApiResponse.success(Message.PATCH_COMMENT_SUCCESS, response));
    }

    // 댓글 삭제
    @DeleteMapping("/{commentId}")
    public ResponseEntity<ApiResponse<Void>> deleteComment(
            @PathVariable Long postId,
            @PathVariable Long commentId) {

        Long currentMemberId = memberService.getCurrentMember().getMemberId();
        commentService.deleteComment(commentId, currentMemberId);

        return ResponseEntity.ok(
                ApiResponse.success(Message.DELETE_COMMENT_SUCCESS));
    }

    @GetMapping()
    public ResponseEntity<ApiResponse<List<Comment>>> getComments(){
        return ResponseEntity.ok(
                ApiResponse.success("", commentService.getComments()));
    }
}