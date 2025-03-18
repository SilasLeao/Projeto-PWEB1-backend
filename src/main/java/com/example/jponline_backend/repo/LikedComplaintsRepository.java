package com.example.jponline_backend.repo;

import com.example.jponline_backend.models.LikedComplaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikedComplaintsRepository extends JpaRepository<LikedComplaint, Long> {
    void deleteByUserId(String userId);
    // Metodo para deletar um registro baseado no userId e postId
    void deleteByUserIdAndPostId(String userId, String postId);
}
