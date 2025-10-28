package com.example.spring_practice.domain.comment.controller;

import com.example.spring_practice.domain.comment.dto.CommentIdResponseDto;
import com.example.spring_practice.domain.comment.dto.CommentRequestDto;
import com.example.spring_practice.domain.comment.entity.Comment;
import com.example.spring_practice.domain.comment.service.CommentService;
import com.example.spring_practice.domain.member.service.AuthService;
import com.example.spring_practice.domain.member.service.MemberService;
import com.example.spring_practice.global.response.ApiResponse;
import com.example.spring_practice.global.response.Message;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts/{postId}/comments")
public class CommentController {
    private final AuthService authService;
    private final CommentService commentService;

    @Operation(summary = "댓글 등록", description = "특정 게시글에 댓글을 등록합니다.")
    @Parameter(name = "postId", description = "게시글ID", example = "1", required = true)
    @PostMapping
    public ResponseEntity<ApiResponse<CommentIdResponseDto>> createComment(
            @PathVariable Long postId,
            @Valid @RequestBody CommentRequestDto commentRequestDto) {

        Long currentMemberId = authService.getCurrentMember().getMemberId();
        CommentIdResponseDto response = commentService.createComment(postId, commentRequestDto, currentMemberId);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(Message.POST_COMMENT_SUCCESS, response));
    }

    @Operation(summary = "댓글 수정", description = "특정 댓글을 수정합니다.")
    @Parameter(name = "postId", description = "게시글ID", example = "1", required = true)
    @Parameter(name = "commentId", description = "댓글ID", example = "1", required = true)
    @PatchMapping("/{commentId}")
    public ResponseEntity<ApiResponse<CommentIdResponseDto>> editComment(
            @PathVariable Long postId,
            @PathVariable Long commentId,
            @Valid @RequestBody CommentRequestDto commentRequestDto) {

        Long currentMemberId = authService.getCurrentMember().getMemberId();
        CommentIdResponseDto response = commentService.updateComment(commentId, commentRequestDto, currentMemberId);

        return ResponseEntity.ok(
                ApiResponse.success(Message.PATCH_COMMENT_SUCCESS, response));
    }

    @Operation(summary = "댓글 삭제", description = "특정 댓글을 삭제합니다.")
    @Parameter(name = "postId", description = "게시글ID", example = "1", required = true)
    @Parameter(name = "commentId", description = "댓글ID", example = "1", required = true)
    @DeleteMapping("/{commentId}")
    public ResponseEntity<ApiResponse<Void>> deleteComment(
            @PathVariable Long postId,
            @PathVariable Long commentId) {

        Long currentMemberId = authService.getCurrentMember().getMemberId();
        commentService.deleteComment(commentId, currentMemberId);

        return ResponseEntity.ok(
                ApiResponse.success(Message.DELETE_COMMENT_SUCCESS));
    }
}