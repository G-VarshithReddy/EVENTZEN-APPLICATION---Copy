package com.event.eventManagement.controller;

import com.event.eventManagement.entity.Admin;
import com.event.eventManagement.repository.AdminRepository;
import com.event.eventManagement.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})  // Allow both frontend ports
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private AdminRepository adminRepository;

    @PostMapping("/register")
    public ResponseEntity<String> registerAdmin(@RequestBody Admin admin) {
        System.out.println("Received Admin: " + admin.getEmail() + ", " + admin.getPassword());
        boolean isRegistered = adminService.registerAdmin(admin);
        if (isRegistered) {
            return ResponseEntity.ok("Admin registered successfully");
        } else {
            return ResponseEntity.badRequest().body("Admin already exists");
        }
    }

    @PostMapping("/login")  // ✅ Changed from "/signing" to "/login" (matches frontend)
    public ResponseEntity<?> signIn(@RequestBody Admin admin) {
        Optional<Admin> existingAdmin = adminRepository.findByEmailAndPassword(admin.getEmail(), admin.getPassword());
        if (existingAdmin.isPresent()) {
            return ResponseEntity.ok().body("Successfully signed in");
        } else {
            return ResponseEntity.badRequest().body("Incorrect email or password");
        }
    }
}
