package com.example.application.backend.services.concretes;


import com.example.application.backend.models.StokKart;
import com.example.application.backend.repositories.StokKartRepo;
import com.example.application.backend.services.abstracts.StokKartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StokKartServiceImp implements StokKartService {

    private final StokKartRepo stokKartRepo;

    @Autowired
    public StokKartServiceImp(StokKartRepo stokKartRepo) {
        this.stokKartRepo = stokKartRepo;
    }

    @Override
    public void add(StokKart stokKart) {
        stokKartRepo.save(stokKart);
    }

    @Override
    public void delete(int id) {
        stokKartRepo.deleteById(id);
    }

    @Override
    public void update(int id, StokKart stokKart) {
        StokKart existStokKart = stokKartRepo.findById(id).get();
        existStokKart.setStokKodu(stokKart.getStokKodu());
        existStokKart.setAciklama(stokKart.getAciklama());
        existStokKart.setFiyat(stokKart.getFiyat());
        existStokKart.setGrupKodu(stokKart.getGrupKodu());
      // existStokKart.setSatis(stokKart.getSatis());
        stokKartRepo.save(existStokKart);
    }

    @Override
    public void deleteStokKart(StokKart stokKart) {
        stokKartRepo.delete(stokKart);
    }

    @Override
    public List<StokKart> getAll() {

        return stokKartRepo.findAll();
    }

    @Override
    public List<StokKart> getAll(String filterText) {
        if (filterText == null || filterText.isEmpty()) {
            return stokKartRepo.findAll();
        } else {
            return stokKartRepo.search(filterText);
        }

    }

    @Override
    public StokKart getOne(int stokKod) {
        return stokKartRepo.findById(stokKod).get();
    }
}
