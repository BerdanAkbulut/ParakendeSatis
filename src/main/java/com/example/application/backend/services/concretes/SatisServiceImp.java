package com.example.application.backend.services.concretes;


import com.example.application.backend.models.Satis;
import com.example.application.backend.repositories.SatisRepo;
import com.example.application.backend.services.abstracts.SatisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SatisServiceImp implements SatisService {

   private SatisRepo satisRepo;

   @Autowired
    public SatisServiceImp(SatisRepo satisRepo) {
        this.satisRepo = satisRepo;
    }

    @Override
    public void add(Satis satis) {

     satisRepo.save(satis);

    }

    @Override
    public void delete(int id) {
        satisRepo.deleteById(id);
    }

    @Override
    public void update(int id, Satis satis) {
        Satis existSatis = satisRepo.findById(id).get();
        existSatis.setSatisTipi(satis.getSatisTipi());
       // existSatis.setMagaza(satis.getMagaza());
        //existSatis.setStokKart(satis.getStokKart());
        satisRepo.save(existSatis);
    }

    @Override
    public List<Satis> getAll() {

       return satisRepo.findAll();
    }

    @Override
    public Satis getOne(int id) {

       return satisRepo.findById(id).get();
    }

    @Override
    public List<Satis> getByMagaza_Id(int magazaId) {
        return satisRepo.getByMagaza_Id(magazaId);
    }
}
