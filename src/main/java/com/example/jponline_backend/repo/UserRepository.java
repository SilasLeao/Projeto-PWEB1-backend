package com.example.jponline_backend.repo;

import com.example.jponline_backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

  @Query("SELECT u FROM User u where u.email LIKE 'leaosilas@gmail.com'")
  public List<User> getUserTest();

}
