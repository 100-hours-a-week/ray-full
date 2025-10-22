package com.example.spring_practice.domain.post.controller;

import com.example.spring_practice.domain.member.entity.Member;
import com.example.spring_practice.domain.member.service.AuthService;
import com.example.spring_practice.domain.member.service.MemberService;
import com.example.spring_practice.domain.post.dto.PostIdResponseDto;
import com.example.spring_practice.domain.post.dto.PostRequestDto;
import com.example.spring_practice.domain.post.dto.PostResponseDto;
import com.example.spring_practice.domain.post.dto.PostSummaryResponseDto;
import com.example.spring_practice.domain.post.service.PostService;
import com.example.spring_practice.global.response.ApiResponse;
import com.example.spring_practice.global.response.Message;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {
    private final AuthService authService;
    private final PostService postService;

    @Operation(summary = "게시글 목록 불러오기", description = "게시글 목록을 불러옵니다.")
    @GetMapping
    public ResponseEntity<ApiResponse<List<PostSummaryResponseDto>>> getPostList() {
        List<PostSummaryResponseDto> posts = postService.getPostList();
        return ResponseEntity.ok(
                ApiResponse.success(Message.GET_POST_LIST_SUCCESS, posts)
        );
    }

    @Operation(summary = "게시글 상세보기", description = "게시글 상세정보를 불러옵니다.")
    @Parameter(name = "postId", description = "게시글 ID", example = "1", required = true)
    @GetMapping("/{postId}")
    public ResponseEntity<ApiResponse<PostResponseDto>> getPostDetail(
            @PathVariable Long postId) {
        Long currentMemberId = authService.getCurrentMember().getMemberId();
        PostResponseDto postDetail = postService.getPostDetail(postId, currentMemberId);
        return ResponseEntity.ok(
                ApiResponse.success(Message.GET_POST_DETAIL_SUCCESS, postDetail)
        );
    }

    @Operation(summary = "게시글 등록", description = "게시글을 등록합니다.")
    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<ApiResponse<PostIdResponseDto>> createPost(
            @Valid @ModelAttribute PostRequestDto postRequestDto) {

        Member currentMember = authService.getCurrentMember();

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(Message.POST_POST_SUCCESS,
                        postService.createPost(postRequestDto, currentMember)));
    }

    @Operation(summary = "게시글 수정", description = "게시글을 수정합니다.")
    @Parameter(name = "postId", description = "게시글 ID", example = "1", required = true)
    @PatchMapping(value = "/{postId}", consumes = "multipart/form-data")
    public ResponseEntity<ApiResponse<PostIdResponseDto>> editPost(
            @PathVariable Long postId,
            @Valid @ModelAttribute PostRequestDto postRequestDto) {

        Long currentMemberId = authService.getCurrentMember().getMemberId();

        return ResponseEntity.ok(
                ApiResponse.success(Message.PATCH_POST_SUCCESS,
                        postService.editPost(postId, postRequestDto, currentMemberId))
        );
    }

    @Operation(summary = "게시글 삭제", description = "게시글을 삭제합니다.")
    @Parameter(name = "postId", description = "게시글 ID", example = "1", required = true)
    @DeleteMapping("/{postId}")
    public ResponseEntity<ApiResponse<Void>> deletePost(@PathVariable Long postId) {
        Long currentMemberId = authService.getCurrentMember().getMemberId();
        postService.deletePost(postId, currentMemberId);
        return ResponseEntity.ok(
                ApiResponse.success(Message.DELETE_POST_SUCCESS)
        );
    }

}
