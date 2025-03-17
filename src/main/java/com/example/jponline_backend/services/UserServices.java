package com.example.jponline_backend.services;

import com.example.jponline_backend.models.DislikedPost;
import com.example.jponline_backend.models.LikedPost;
import com.example.jponline_backend.repo.DislikedPostsRepository;
import com.example.jponline_backend.repo.LikedPostsRepository;
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

    @Autowired
    private LikedPostsRepository likedPostsRepository;  // Para interagir com a tabela liked_posts

    @Autowired
    private DislikedPostsRepository dislikedPostsRepository;  // Para interagir com a tabela disliked_posts

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



    // Metodo para atualizar os likes e dislikes do usuário
    public User updateUserLikesDislikes(String userId, String postId, boolean isLike) {
        User user = userRepository.findById(userId).orElse(null);

        if (user != null) {
            if (isLike) {
                // Verifica se o post não está na tabela de disliked posts, para evitar conflito
                dislikedPostsRepository.deleteByUserIdAndPostId(userId, postId);

                // Adiciona o post à tabela liked_posts
                likedPostsRepository.save(new LikedPost(userId, postId));
            } else {
                // Verifica se o post não está na tabela de liked posts, para evitar conflito
                likedPostsRepository.deleteByUserIdAndPostId(userId, postId);

                // Adiciona o post à tabela disliked_posts
                dislikedPostsRepository.save(new DislikedPost(userId, postId));
            }
            return user;
        }
        return null;
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
    public Optional<User> findById(String id) {
        return userRepository.findById(id);
    }


}
