package com.example.spring_practice.domain.post.controller;

import com.example.spring_practice.domain.member.entity.Member;
import com.example.spring_practice.domain.member.service.MemberService;
import com.example.spring_practice.domain.post.dto.PostIdResponseDto;
import com.example.spring_practice.domain.post.dto.PostRequestDto;
import com.example.spring_practice.domain.post.dto.PostResponseDto;
import com.example.spring_practice.domain.post.dto.PostSummaryResponseDto;
import com.example.spring_practice.domain.post.service.PostService;
import com.example.spring_practice.global.response.ApiResponse;
import com.example.spring_practice.global.response.Message;
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
    private final PostService postService;
    private final MemberService memberService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<PostSummaryResponseDto>>> getPostList() {
        List<PostSummaryResponseDto> posts = postService.getPostList();
        return ResponseEntity.ok(
                ApiResponse.success(Message.GET_POST_LIST_SUCCESS, posts)
        );
    }

    @GetMapping("/{postId}")
    public ResponseEntity<ApiResponse<PostResponseDto>> getPostDetail(
            @PathVariable Long postId) {
        Long currentMemberId = memberService.getCurrentMember().getMemberId();
        PostResponseDto postDetail = postService.getPostDetail(postId, currentMemberId);
        return ResponseEntity.ok(
                ApiResponse.success(Message.GET_POST_DETAIL_SUCCESS, postDetail)
        );
    }

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<ApiResponse<PostIdResponseDto>> createPost(
            @ModelAttribute PostRequestDto postRequestDto) {

        Member currentMember = memberService.getCurrentMember();

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(Message.POST_POST_SUCCESS,
                        postService.createPost(postRequestDto, currentMember)));
    }

    @PatchMapping(value = "/{postId}", consumes = "multipart/form-data")
    public ResponseEntity<ApiResponse<PostIdResponseDto>> editPost(
            @PathVariable Long postId,
            @ModelAttribute PostRequestDto postRequestDto) {

        Long currentMemberId = memberService.getCurrentMember().getMemberId();

        return ResponseEntity.ok(
                ApiResponse.success(Message.PATCH_POST_SUCCESS,
                        postService.editPost(postId, postRequestDto, currentMemberId))
        );
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<ApiResponse<Void>> deletePost(@PathVariable Long postId) {
        Long currentMemberId = memberService.getCurrentMember().getMemberId();
        postService.deletePost(postId, currentMemberId);
        return ResponseEntity.ok(
                ApiResponse.success(Message.DELETE_POST_SUCCESS)
        );
    }

}
