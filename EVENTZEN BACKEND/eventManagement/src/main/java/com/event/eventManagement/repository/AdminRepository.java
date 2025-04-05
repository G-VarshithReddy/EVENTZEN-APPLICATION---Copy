package com.event.eventManagement.repository;

import com.event.eventManagement.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    boolean existsByEmail(String email);
    Optional<Admin> findByEmailAndPassword(String email, String password);
}