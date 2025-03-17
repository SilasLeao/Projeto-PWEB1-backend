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


}
