package com.example.spring_practice.domain.comment.repository;

import com.example.spring_practice.domain.comment.entity.Comment;
import com.example.spring_practice.domain.member.entity.Member;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CommentRepositoryImpl implements CommentRepository {
    private List<Comment> commentList = new ArrayList<>();
    @Override
    public Comment save(Comment entity) {
        Long newId = commentList.isEmpty() ? 1L :
                commentList.stream()
                        .mapToLong(Comment::getCommentId)
                        .max()
                        .orElse(0L) + 1;
        entity.setCommentId(newId);
        commentList.add(entity);
        return entity;
    }

    @Override
    public Optional<Comment> findById(Long id) {
        return commentList.stream()
                .filter(member -> member.getCommentId().equals(id))
                .findFirst();
    }

    @Override
    public List<Comment> findAll() { return commentList; }

    @Override
    public void delete(Comment entity) {
        commentList.remove(entity);
    }
}
