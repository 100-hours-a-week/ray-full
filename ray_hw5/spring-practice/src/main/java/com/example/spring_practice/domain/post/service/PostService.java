package com.example.spring_practice.domain.post.service;

import com.example.spring_practice.domain.member.entity.Member;
import com.example.spring_practice.domain.post.dto.*;
import com.example.spring_practice.domain.post.entity.Post;
import com.example.spring_practice.domain.post.repository.PostRepository;
import com.example.spring_practice.domain.shared.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final ImageService imageService;

    public List<PostSummaryResponseDto> getPostList() {
        List<Post> posts = postRepository.findAll();

        return posts.stream()
                .map(PostDtoConvertor::toPostSummaryResponseDto)
                .collect(Collectors.toList());
    }

    public PostResponseDto getPostDetail(Long postId, Long currentMemberId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(RuntimeException::new);
        post.increaseViewCount();

        return PostDtoConvertor.toPostResponseDto(post, currentMemberId);
    }

    public PostIdResponseDto createPost(PostRequestDto postRequestDto, Member currentMember) {
        Post post = new Post(postRequestDto, currentMember);
        if(postRequestDto.getPostImage() != null){
            post.setImgUrl(imageService.saveImg(postRequestDto.getPostImage()));
        }
        return PostDtoConvertor.toPostIdResponseDto(postRepository.save(post).getPostId());
    }

    public PostIdResponseDto editPost(Long postId, PostRequestDto postRequestDto, Long currentMemberId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException());
        if(!post.getMember().getMemberId().equals(currentMemberId)) {
            throw new RuntimeException();
        }
        post.setTitle(postRequestDto.getTitle());
        post.setContent(postRequestDto.getContent());
        if(postRequestDto.getPostImage() != null){
            post.setImgUrl(imageService.saveImg(postRequestDto.getPostImage()));
        }
        return PostDtoConvertor.toPostIdResponseDto(post.getPostId());
    }

    public void deletePost(Long postId, Long currentMemberId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException());
        if(!post.getMember().getMemberId().equals(currentMemberId)) {
            throw new RuntimeException();
        }
        postRepository.delete(post);
    }
}
