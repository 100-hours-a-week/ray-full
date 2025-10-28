package com.example.spring_practice.domain.shared;

import java.util.List;
import java.util.Optional;

public interface Repository <T> {
    T save(T entity);
    Optional<T> findById(Long id);
    List<T> findAll();
    void delete(T entity);
    void deleteById(Long id);
}