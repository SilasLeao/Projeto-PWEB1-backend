package com.example.jponline_backend.models;

import jakarta.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User {

  @Id
  private String id = UUID.randomUUID().toString();


  @Column(nullable = false, unique = true)
  private String username;

  @Column(nullable = false, unique = true)
  private String email;

  @Column(nullable = false)
  private String password;

  @ManyToMany
  @JoinTable(
          name = "liked_posts",
          joinColumns = @JoinColumn(name = "user_id"),
          inverseJoinColumns = @JoinColumn(name = "post_id")
  )
  private List<Post> likedPosts;

  @ManyToMany
  @JoinTable(
          name = "disliked_posts",
          joinColumns = @JoinColumn(name = "user_id"),
          inverseJoinColumns = @JoinColumn(name = "post_id")
  )
  private List<Post> dislikedPosts;

  // Construtores
  public User() {}

  public User(String username, String email, String password, List<Post> likedPosts, List<Post> dislikedPosts) {
    this.username = username;
    this.email = email;
    this.password = password;
    this.likedPosts = likedPosts;
    this.dislikedPosts = dislikedPosts;
  }

  // Getters e Setters
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public List<Post> getLikedPosts() {
    return likedPosts;
  }

  public void setLikedPosts(List<Post> likedPosts) {
    this.likedPosts = likedPosts;
  }

  public List<Post> getDislikedPosts() {
    return dislikedPosts;
  }

  public void setDislikedPosts(List<Post> dislikedPosts) {
    this.dislikedPosts = dislikedPosts;
  }
}
