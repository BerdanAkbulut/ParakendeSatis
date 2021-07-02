package com.example.application.backend.repositories;
import com.example.application.backend.models.Satis;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SatisRepo extends JpaRepository<Satis,Integer> {

    List<Satis> getByMagaza_Id(int magazaId);
}
