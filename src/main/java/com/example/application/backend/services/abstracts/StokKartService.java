package com.example.application.backend.services.abstracts;


import com.example.application.backend.models.StokKart;
import org.springframework.stereotype.Service;

import java.util.List;

public interface StokKartService {

    void add(StokKart stokKart);
    void delete(int id);
    void update(int id, StokKart stokKart);
    void deleteStokKart(StokKart stokKart);
    List<StokKart> getAll();
    List<StokKart> getAll(String filterText);
    StokKart getOne(int stokKod);



}
