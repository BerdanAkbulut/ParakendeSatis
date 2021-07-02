package com.example.application.backend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Satis {


    public enum SatisTipi {
        NAKIT,KREDI_KARTI


    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    @NotEmpty
    @NotBlank
    private Satis.SatisTipi satisTipi;


    @ManyToOne
    private Magaza magaza;

    /*
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "stok_kart_id",referencedColumnName = "stokKodu")
    private StokKart stokKart;    */

    private int StokKodu;



}
