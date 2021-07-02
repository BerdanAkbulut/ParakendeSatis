package com.example.application.backend.services.abstracts;




import com.example.application.backend.models.Satis;

import java.util.List;

public interface SatisService {
    void add(Satis satis);
    void delete(int id);
    void update(int id, Satis satis);

    List<Satis> getAll();
    Satis getOne(int id);

    List<Satis> getByMagaza_Id(int magazaId);
}
