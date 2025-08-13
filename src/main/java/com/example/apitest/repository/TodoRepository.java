package com.example.apitest.repository;

import com.example.apitest.model.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    Page<Todo> findByDone(Boolean done, Pageable pageable);
    Page<Todo> findByTitleContainingIgnoreCase(String title, Pageable pageable);
    Page<Todo> findByDoneAndTitleContainingIgnoreCase(Boolean done, String title, Pageable pageable);
}
