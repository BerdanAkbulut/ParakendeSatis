package com.example.application.backend.services.abstracts;

import com.example.application.backend.models.Magaza;


import java.util.List;


public interface MagazaService {

    void add(Magaza magaza);
    void delete(int id);
    void update(int id, Magaza magaza);

    List<Magaza> getAll();

    Magaza getOne(int id);

    void deleteMagaza(Magaza magaza);

    Magaza login(String email,String password);
}
