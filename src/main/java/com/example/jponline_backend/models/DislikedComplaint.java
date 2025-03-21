package com.example.jponline_backend.models;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "disliked_complaints")
public class DislikedComplaint {

    @Id
    private String id = UUID.randomUUID().toString();

    private String userId;
    private String postId;

    public DislikedComplaint() {
    }

    public DislikedComplaint(String userId, String postId) {
        this.userId = userId;
        this.postId = postId;
    }

    // Getters e setters
    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

}
