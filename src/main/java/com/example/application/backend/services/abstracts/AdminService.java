package com.example.application.backend.services.abstracts;

import com.example.application.backend.models.Admin;
import com.example.application.backend.models.Magaza;

import java.util.List;

public interface AdminService {
    void add(Admin admin);
    void delete(int id);

    List<Admin> getAll();

    Admin getOne(int id);

    Admin login(String email,String password);

}
