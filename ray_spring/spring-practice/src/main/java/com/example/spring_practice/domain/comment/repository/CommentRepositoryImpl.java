package com.example.spring_practice.domain.comment.repository;

import com.example.spring_practice.domain.comment.entity.Comment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class CommentRepositoryImpl implements CommentRepository {
    private final ConcurrentHashMap<Long, Comment> comments = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong(1L);

    @Override
    public Comment save(Comment comment) {
        if (comment.getCommentId() == null) {
            // INSERT: ID 생성 후 저장
            Long newId = sequence.getAndIncrement();
            comment.setCommentId(newId);
            comments.put(newId, comment);
        } else {
            // UPDATE: 기존 ID로 저장
            comments.put(comment.getCommentId(), comment);
        }
        return comment;
    }

    @Override
    public Optional<Comment> findById(Long id) {
        return Optional.ofNullable(comments.get(id));
    }

    @Override
    public List<Comment> findAll() {
        return new ArrayList<>(comments.values());
    }

    @Override
    public void delete(Comment comment) {
        comments.remove(comment.getCommentId());
    }

    @Override
    public void deleteById(Long id) {
        comments.remove(id);
    }
}