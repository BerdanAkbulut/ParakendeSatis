package com.example.application.backend.services.concretes;


import com.example.application.backend.models.Magaza;
import com.example.application.backend.repositories.MagazaRepo;
import com.example.application.backend.services.abstracts.MagazaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MagazaServiceImp implements MagazaService {


    private final MagazaRepo magazaRepo;
    @Autowired
    public MagazaServiceImp(MagazaRepo magazaRepo) {
        this.magazaRepo = magazaRepo;
    }

    @Override
    public void add(Magaza magaza) {
        magazaRepo.save(magaza);
    }

    @Override
    public void delete(int id) {
        magazaRepo.deleteById(id);
    }

    @Override
    public void update(int id, Magaza magaza) {
       Magaza existMagaza = magazaRepo.findById(id).get();
       existMagaza.setMagazaAd(magaza.getMagazaAd());
       existMagaza.setEmail(magaza.getEmail());
       existMagaza.setPassword(magaza.getPassword());
       magazaRepo.save(existMagaza);

    }

    @Override
    public List<Magaza> getAll() {

        return magazaRepo.findAll();
    }

    @Override
    public Magaza getOne(int id) {
        return magazaRepo.findById(id).get();

    }

    @Override
    public void deleteMagaza(Magaza magaza) {
        magazaRepo.delete(magaza);
    }

    @Override
    public Magaza login(String email, String password) {
        Magaza magaza = magazaRepo.getByEmailAndPassword(email,password);
        if (magaza == null) {
            return new Magaza();
        } else {
            return magaza;
        }
    }
}
