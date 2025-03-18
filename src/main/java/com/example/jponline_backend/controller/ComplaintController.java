package com.example.jponline_backend.controller;

import com.example.jponline_backend.models.Complaint;
import com.example.jponline_backend.services.ComplaintServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/complaints")
@CrossOrigin(origins = "http://localhost:4200") // Permite comunicação com o frontend Angular
public class ComplaintController {

    private final ComplaintServices complaintServices;

    public ComplaintController(ComplaintServices complaintServices) {
        this.complaintServices = complaintServices;
    }

    @GetMapping
    public ResponseEntity<List<Complaint>> getAllComplaints() {
        return ResponseEntity.ok(complaintServices.getAllComplaints());
    }

    @PostMapping
    public ResponseEntity<Complaint> addComplaint(@RequestBody Complaint complaint) {
        return ResponseEntity.ok(complaintServices.addComplaint(complaint));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Complaint> updateComplaint(@PathVariable String id, @RequestBody Complaint complaint) {
        complaint.setId(id);
        return ResponseEntity.ok(complaintServices.updateComplaint(complaint));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComplaint(@PathVariable String id) {

        complaintServices.deleteComplaint(id);
        return ResponseEntity.noContent().build();
    }
}
