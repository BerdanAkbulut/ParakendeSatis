package com.example.application.backend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class StokKart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int stokKodu;

    @NotNull
    @NotBlank
    private String aciklama;

    @NotNull
    @NotBlank
    private double fiyat;

    @NotNull
    @NotBlank
    private int grupKodu;

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    public String getFiyatt() {
        return Double.toString(fiyat);
    }

    public void setFiyatt(String fiyat) {

       this.fiyat = Double.parseDouble(fiyat);

    }

    public String getGrupKoduu() {
        return Integer.toString(grupKodu);
    }

    public void setGrupKoduu(String grupKodu) {
       this.grupKodu = Integer.parseInt(grupKodu);
    }

    public String getStokKoduu() {
        return Integer.toString(stokKodu);
    }

    public void setStokKoduu(String stokKodu) {
        String newS = Integer.toString(this.stokKodu);
        newS = stokKodu;
    }


}
