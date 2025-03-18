package com.example.jponline_backend.controller;

import com.example.jponline_backend.services.UserServices;
import com.example.jponline_backend.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.example.jponline_backend.models.User;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/")
public class UserController {
  @Autowired
  private UserServices userServices;
  @Autowired
  private UserRepository userRepository;

  @GetMapping("/users")
  public List<User> getUsers() {
    return this.userServices.getUsers();
  }

  @GetMapping("/users/{userId}")
  public Optional<User> getUserById(@PathVariable String userId) {
    return this.userServices.findById(userId);
  }

  // Endpoint para verificar se o email já está registrado
  @GetMapping("/checkEmail")
  public boolean checkEmail(@RequestParam String email) {
    return userServices.isEmailRegistered(email);
  }

  // Endpoint para registrar um novo usuário
  @PostMapping("/users")
  public User registerUser(@RequestBody User user) {
    return userServices.registerUser(user);
  }

  @PostMapping("/login")
  public ResponseEntity<?> loginUser(@RequestBody User loginUser) {
    User user = userServices.findByEmail(loginUser.getEmail());

    if (user != null && user.getPassword().equals(loginUser.getPassword())) {
      return ResponseEntity.ok(user);
    } else {
      return ResponseEntity.status(401).body("Email ou senha inválidos");
    }
  }

  // Endpoint para atualizar os likes e dislikes do usuário
  @PatchMapping("/users/{userId}/news")
  public ResponseEntity<User> updateUserLikesDislikesPosts(@PathVariable String userId, @RequestBody Map<String, List<String>> updateData) {
    User user = userRepository.findById(userId).orElse(null);
    if (user == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    System.out.println(updateData);

    List<String> likedPosts = updateData.get("likedPosts");
    List<String> dislikedPosts = updateData.get("dislikedPosts");


    // Atualiza as tabelas de relacionamento liked_posts e disliked_posts
    if (likedPosts != null) {
      userServices.updateLikedPosts(userId, likedPosts);
    }
    if (dislikedPosts != null) {
      userServices.updateDislikedPosts(userId, dislikedPosts);
    }

    // Retorna o usuário atualizado
    return ResponseEntity.ok(user);
  }


  // Endpoint para atualizar os likes e dislikes do usuário
  @PatchMapping("/users/{userId}/complaints")
  public ResponseEntity<User> updateUserLikesDislikesComplaints(@PathVariable String userId, @RequestBody Map<String, List<String>> updateData) {
    User user = userRepository.findById(userId).orElse(null);
    if (user == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    System.out.println(updateData);

    List<String> likedComplaints = updateData.get("likedComplaints");
    List<String> dislikedComplaints = updateData.get("dislikedComplaints");


    // Atualiza as tabelas de relacionamento liked_posts e disliked_posts
    if (likedComplaints != null) {
      userServices.updateLikedComplaints(userId, likedComplaints);
    }
    if (dislikedComplaints != null) {
      userServices.updateDislikedComplaints(userId, dislikedComplaints);
    }

    // Retorna o usuário atualizado
    return ResponseEntity.ok(user);
  }

}
