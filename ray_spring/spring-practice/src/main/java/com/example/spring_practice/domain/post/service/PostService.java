package com.example.spring_practice.domain.post.service;

import com.example.spring_practice.domain.member.entity.Member;
import com.example.spring_practice.domain.post.dto.*;
import com.example.spring_practice.domain.post.entity.Post;
import com.example.spring_practice.domain.post.repository.PostRepository;
import com.example.spring_practice.domain.shared.ImageService;
import com.example.spring_practice.global.response.CustomException;
import com.example.spring_practice.global.response.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final ImageService imageService;
    private final PostDtoConverter postDtoConverter;

    public List<PostSummaryResponseDto> getPostList() {
        List<Post> posts = postRepository.findAll();

        return posts.stream()
                .map(postDtoConverter::toPostSummaryResponseDto)
                .collect(Collectors.toList());
    }

    public PostResponseDto getPostDetail(Long postId, Long currentMemberId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));
        post.increaseViewCount();
        return postDtoConverter.toPostResponseDto(post, currentMemberId);
    }

    public PostIdResponseDto createPost(PostRequestDto postRequestDto, Member currentMember) {
        Post post = new Post(postRequestDto, currentMember);
        if(postRequestDto.getPostImage() != null){
            post.updateImageUrl(imageService.saveImg(postRequestDto.getPostImage()));
        }
        return postDtoConverter.toPostIdResponseDto(postRepository.save(post).getPostId());
    }

    public PostIdResponseDto editPost(Long postId, PostRequestDto postRequestDto, Long currentMemberId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));

        // 권한 확인
        checkPostPermission(post, currentMemberId);

        post.updateTitle(postRequestDto.getTitle());
        post.updateContent(postRequestDto.getContent());
        if(postRequestDto.getPostImage() != null){
            post.updateImageUrl(imageService.saveImg(postRequestDto.getPostImage()));
        }
        return postDtoConverter.toPostIdResponseDto(post.getPostId());
    }

    public void deletePost(Long postId, Long currentMemberId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));

        // 권한 확인
        checkPostPermission(post, currentMemberId);

        postRepository.delete(post);
    }

    private void checkPostPermission(Post post, Long currentMemberId) {
        if(!post.getMember().getMemberId().equals(currentMemberId)) {
            throw new CustomException(ErrorCode.NO_PERMISSION);
        }
    }
}
