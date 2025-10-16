package com.example.spring_practice.domain.comment.service;

import com.example.spring_practice.domain.comment.dto.CommentIdResponseDto;
import com.example.spring_practice.domain.comment.dto.CommentRequestDto;
import com.example.spring_practice.domain.comment.entity.Comment;
import com.example.spring_practice.domain.comment.repository.CommentRepository;
import com.example.spring_practice.domain.member.entity.Member;
import com.example.spring_practice.domain.member.repository.MemberRepository;
import com.example.spring_practice.domain.post.entity.Post;
import com.example.spring_practice.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    public CommentIdResponseDto createComment(Long postId, CommentRequestDto commentRequestDto, Long currentMemberId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("post not found"));

        Member member = memberRepository.findById(currentMemberId)
                .orElseThrow(() -> new RuntimeException("member not found"));


        Comment comment = new Comment(commentRequestDto, member, post);
        Comment savedComment = commentRepository.save(comment);
        post.addComment(savedComment);

        return new CommentIdResponseDto(savedComment.getCommentId());
    }

    public CommentIdResponseDto updateComment(Long commentId, CommentRequestDto dto, Long currentMemberId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("comment not found"));

        if (!comment.getMember().getMemberId().equals(currentMemberId)) {
            throw new RuntimeException("unauthorized user");
        }

        comment.setContent(dto.getContent());

        return new CommentIdResponseDto(comment.getCommentId());
    }

    public void deleteComment(Long commentId, Long currentMemberId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("comment not found"));

        if (!comment.getMember().getMemberId().equals(currentMemberId)) {
            throw new RuntimeException("unauthorized user");
        }

        Post post = comment.getPost();
        post.deleteComment(comment);

        commentRepository.delete(comment);
    }

    public List<Comment> getComments() {
        return commentRepository.findAll();
    }
}
