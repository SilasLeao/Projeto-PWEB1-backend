package com.example.jponline_backend.repo;

import com.example.jponline_backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  // Metodo customizado para buscar um usuário pelo email
  Optional<User> findByEmail(String email);

  // Metodo customizado para buscar um usuário pelo id
  Optional<User> findById(String id);
}
