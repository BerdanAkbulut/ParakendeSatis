package com.example.application.backend.repositories;

import com.example.application.backend.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepo extends JpaRepository<Admin,Integer> {

    Admin getByEmailAndPassword(String email,String password);
}
