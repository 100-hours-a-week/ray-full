package com.example.spring_practice.domain.post.repository;

import com.example.spring_practice.domain.member.entity.Member;
import com.example.spring_practice.domain.post.entity.Post;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PostRepositoryImpl implements PostRepository {
    private List<Post> postList = new ArrayList<>();

    @Override
    public Post save(Post entity) {
        Long newId = postList.isEmpty() ? 1L :
                postList.stream()
                        .mapToLong(Post::getPostId)
                        .max()
                        .orElse(0L) + 1;
        entity.setPostId(newId);
        postList.add(entity);
        return entity;
    }

    @Override
    public Optional<Post> findById(Long id) {
        return postList.stream()
                .filter(post -> post.getPostId().equals(id))
                .findFirst();
    }

    @Override
    public List<Post> findAll() {
        return postList;
    }

    @Override
    public void delete(Post entity) {
        postList.remove(entity);
    }
}
