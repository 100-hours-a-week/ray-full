package com.example.spring_practice.domain.post.repository;

import com.example.spring_practice.domain.post.entity.Post;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class PostRepositoryImpl implements PostRepository {
    private final ConcurrentHashMap<Long, Post> posts = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong(1L);

    @Override
    public Post save(Post post) {
        if (post.getPostId() == null) {
            // ID가 없으면 새로 생성 (INSERT)
            Long newId = sequence.getAndIncrement();
            post.setPostId(newId);
            posts.put(newId, post);
        } else {
            // ID가 있으면 업데이트 (UPDATE)
            posts.put(post.getPostId(), post);
        }
        return post;
    }

    @Override
    public Optional<Post> findById(Long id) {
        return Optional.ofNullable(posts.get(id));
    }

    @Override
    public List<Post> findAll() {
        return posts.values().stream()
                .sorted(Comparator.comparing(Post::getCreatedAt).reversed())  // 최신순 정렬
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Post post) {
        posts.remove(post.getPostId());
    }

    @Override
    public void deleteById(Long id) {
        posts.remove(id);
    }
}