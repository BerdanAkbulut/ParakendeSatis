package com.example.application.backend.repositories;


import com.example.application.backend.models.StokKart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StokKartRepo  extends JpaRepository<StokKart,Integer> {

    @Query("select s from StokKart s "+
            "where lower(s.aciklama) like lower(concat('%', :searchTerm, '%') ) ")
    List<StokKart> search(@Param("searchTerm") String searchTerm);


}
