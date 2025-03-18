package com.example.jponline_backend.services;
import com.example.jponline_backend.models.Complaint;
import com.example.jponline_backend.repo.ComplaintRepository;
import com.example.jponline_backend.repo.LikedComplaintsRepository;
import com.example.jponline_backend.repo.DislikedComplaintsRepository;
import com.example.jponline_backend.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ComplaintServices {
    @Autowired
    private ComplaintRepository complaintRepository;

    @Autowired
    private DislikedComplaintsRepository dislikedComplaintRepository;

    @Autowired
    private LikedComplaintsRepository likedComplaintRepository;

    @Autowired
    private UserRepository userRepository;

    public ComplaintServices(ComplaintRepository complaintRepository) {
        this.complaintRepository = complaintRepository;
    }

    // Metodo para atualizar a tabela disliked_complaints, removendo relações inválidas
    public void updateDislikedComplaints() {
        // Obter todos os complaints existentes
        List<String> validComplaintIds = complaintRepository.findAll().stream()
                .map(Complaint::getId)
                .collect(Collectors.toList());

        // Iterar sobre todos os registros na tabela disliked_complaints
        dislikedComplaintRepository.findAll().forEach(dislikedComplaint -> {
            // Se o postId (relacionado ao complaint) não existir em um Complaint, deletar essa relação
            if (!validComplaintIds.contains(dislikedComplaint.getPostId())) {
                dislikedComplaintRepository.delete(dislikedComplaint);
            }
        });
    }

    // Metodo para atualizar a tabela liked_complaints, removendo relações inválidas
    public void updateLikedComplaints() {
        // Obter todos os complaints existentes
        List<String> validComplaintIds = complaintRepository.findAll().stream()
                .map(Complaint::getId)
                .collect(Collectors.toList());

        // Iterar sobre todos os registros na tabela liked_complaints
        likedComplaintRepository.findAll().forEach(likedComplaint -> {
            // Se o postId (relacionado ao complaint) não existir em um Complaint, deletar essa relação
            if (!validComplaintIds.contains(likedComplaint.getPostId())) {
                likedComplaintRepository.delete(likedComplaint);
            }
        });
    }

    public List<Complaint> getAllComplaints() {
        return complaintRepository.findAll();
    }

    public Complaint addComplaint(Complaint complaint) {
        return complaintRepository.save(complaint);
    }

    public Complaint updateComplaint(Complaint complaint) {
        return complaintRepository.save(complaint);
    }

    public void deleteComplaint(String id) {
        complaintRepository.deleteById(id);
        updateDislikedComplaints();
        updateLikedComplaints();
    }
}
