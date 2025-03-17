package com.example.jponline_backend.controller;

import com.example.jponline_backend.models.Post;
import com.example.jponline_backend.services.PostServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
@CrossOrigin(origins = "http://localhost:4200") // Permite chamadas do Angular
public class PostController {
    private final PostServices postServices;

    public PostController(PostServices postServices) {
        this.postServices = postServices;
    }

    // Retorna todos os posts
    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {
        return ResponseEntity.ok(postServices.getAllPosts());
    }

    // Retorna um post específico pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable String id) {
        Optional<Post> post = postServices.getPostById(id);
        return post.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Atualiza um post (likes e dislikes)
    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable String id, @RequestBody Post updatedPost) {
        Optional<Post> existingPost = postServices.getPostById(id);
        if (existingPost.isPresent()) {
            Post post = existingPost.get();
            post.setImgUrl(updatedPost.getImgUrl());
            post.setTitle(updatedPost.getTitle());
            post.setTime(updatedPost.getTime());
            post.setInfo(updatedPost.getInfo());
            post.setHiddenText(updatedPost.getHiddenText());
            post.setLikes(updatedPost.getLikes());
            post.setDislikes(updatedPost.getDislikes());
            post.setExpanded(updatedPost.isExpanded());
            return ResponseEntity.ok(postServices.savePost(post));
        }
        return ResponseEntity.notFound().build();
    }
}
