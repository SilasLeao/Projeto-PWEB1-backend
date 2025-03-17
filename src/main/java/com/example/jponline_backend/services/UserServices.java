package com.example.jponline_backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.jponline_backend.models.User;
import com.example.jponline_backend.repo.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserServices {
    @Autowired
    private UserRepository userRepository;

    // Metodo para verificar se o email já está registrado
    public boolean isEmailRegistered(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.isPresent();  // Retorna true se o email já estiver registrado
    }

    // Metodo para registrar um novo usuário
    @Transactional
    public User registerUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getUsers() {
        return this.userRepository.findAll();
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }


}
