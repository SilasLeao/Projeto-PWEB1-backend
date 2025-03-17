package com.example.jponline_backend.repo;

import com.example.jponline_backend.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, String> {
}