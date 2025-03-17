package com.example.jponline_backend.models;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID) // Gerar ID no formato UUID
  private String id;

  @Column(nullable = false, unique = true)
  private String username;

  @Column(nullable = false, unique = true)
  private String email;

  @Column(nullable = false)
  private String password;

  @ElementCollection
  private List<String> likedPosts;

  @ElementCollection
  private List<String> dislikedPosts;

  // Construtores
  public User() {}

  public User(String username, String email, String password, List<String> likedPosts, List<String> dislikedPosts) {
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

  public List<String> getLikedPosts() {
    return likedPosts;
  }

  public void setLikedPosts(List<String> likedPosts) {
    this.likedPosts = likedPosts;
  }

  public List<String> getDislikedPosts() {
    return dislikedPosts;
  }

  public void setDislikedPosts(List<String> dislikedPosts) {
    this.dislikedPosts = dislikedPosts;
  }
}
