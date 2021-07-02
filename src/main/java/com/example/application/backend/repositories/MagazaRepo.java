package com.example.application.backend.repositories;

import com.example.application.backend.models.Magaza;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MagazaRepo extends JpaRepository<Magaza,Integer> {

    Magaza getByEmailAndPassword(String email,String password);
}
