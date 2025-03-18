package com.example.jponline_backend.services;

import com.example.jponline_backend.models.*;
import com.example.jponline_backend.repo.DislikedPostsRepository;
import com.example.jponline_backend.repo.LikedPostsRepository;
import com.example.jponline_backend.repo.DislikedComplaintsRepository;
import com.example.jponline_backend.repo.LikedComplaintsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.jponline_backend.repo.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserServices {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LikedPostsRepository likedPostsRepository;  // Para interagir com a tabela liked_posts

    @Autowired
    private DislikedPostsRepository dislikedPostsRepository;  // Para interagir com a tabela disliked_posts

    @Autowired
    private LikedComplaintsRepository likedComplaintsRepository;  // Para interagir com a tabela liked_posts

    @Autowired
    private DislikedComplaintsRepository dislikedComplaintsRepository;  // Para interagir com a tabela disliked_posts

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


    @Transactional
    public void updateLikedPosts(String userId, List<String> likedPosts) {
        // Limpar likes anteriores
        likedPostsRepository.deleteByUserId(userId);

        // Adicionar os novos likes
        for (String postId : likedPosts) {
            likedPostsRepository.save(new LikedPost(userId, postId));
        }
    }

    @Transactional
    public void updateDislikedPosts(String userId, List<String> dislikedPosts) {
        // Limpar dislikes anteriores
        dislikedPostsRepository.deleteByUserId(userId);

        // Adicionar os novos dislikes
        for (String postId : dislikedPosts) {
            dislikedPostsRepository.save(new DislikedPost(userId, postId));
        }
    }


    @Transactional
    public void updateLikedComplaints(String userId, List<String> likedComplaints) {
        // Limpar likes anteriores
        likedComplaintsRepository.deleteByUserId(userId);

        // Adicionar os novos likes
        for (String postId : likedComplaints) {
            likedComplaintsRepository.save(new LikedComplaint(userId, postId));
        }
    }

    @Transactional
    public void updateDislikedComplaints(String userId, List<String> dislikedComplaints) {
        // Limpar dislikes anteriores
        dislikedComplaintsRepository.deleteByUserId(userId);

        // Adicionar os novos dislikes
        for (String postId : dislikedComplaints) {
            dislikedComplaintsRepository.save(new DislikedComplaint(userId, postId));
        }
    }

    public Optional<User> findById(String id) {
        return userRepository.findById(id);
    }


}
