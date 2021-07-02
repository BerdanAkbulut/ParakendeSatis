package com.example.application.backend.services.concretes;

import com.example.application.backend.models.Admin;
import com.example.application.backend.models.Magaza;
import com.example.application.backend.repositories.AdminRepo;
import com.example.application.backend.services.abstracts.AdminService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImp implements AdminService {
    private final AdminRepo adminRepo;

    public AdminServiceImp(AdminRepo adminRepo) {
        this.adminRepo = adminRepo;
    }

    @Override
    public void add(Admin admin) {
         adminRepo.save(admin);
    }

    @Override
    public void delete(int id) {
        adminRepo.deleteById(id);
    }


    @Override
    public List<Admin> getAll() {
        return adminRepo.findAll();
    }

    @Override
    public Admin getOne(int id) {
        return adminRepo.findById(id).get();
    }

    @Override
    public Admin login(String email, String password) {
        Admin admin = adminRepo.getByEmailAndPassword(email,password);

        if (admin == null) {
            return new Admin();
        } else {
            return admin;
        }
    }
}
