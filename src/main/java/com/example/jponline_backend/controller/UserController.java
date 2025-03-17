package com.example.jponline_backend.controller;

import com.example.jponline_backend.services.UserServices;
import com.example.jponline_backend.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.example.jponline_backend.models.User;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/")
public class UserController {
  @Autowired
  private UserServices userServices;

  @GetMapping("/users")
  public List<User> getUsers() {
    return this.userServices.getUsers();
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

}
