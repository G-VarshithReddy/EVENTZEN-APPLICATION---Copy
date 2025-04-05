package com.event.eventManagement.service;

import com.event.eventManagement.entity.Admin;
import com.event.eventManagement.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public boolean registerAdmin(Admin admin) {
        System.out.println("Checking if admin exists: " + admin.getEmail());
        if (adminRepository.existsByEmail(admin.getEmail())) {
            System.out.println("Admin already exists");
            return false;
        }
        adminRepository.save(admin);
        System.out.println("Admin registered successfully");
        return true;
    }
}
