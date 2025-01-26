package com.Mladen.otkupproizvoda.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="operant")
public class Operant {

    @Id
    @Column(name = "operant_id")
    @NotNull(message = "unesi Id")
    @Size(min=1,message = "unesi Id")
    String id;

    @Column(name = "full_name")
    @NotNull(message = "unesi Ime i Prezime")
    @Size(min=1,message = "unesi Ime i Prezime")
    String fullName;

    @Column(name = "broj_zaduzenih_kutija")
    @NotNull(message = "unesi broj zaduzenih kutija")
    Integer brojZaduzenihKutija=0;

    @Column(name = "broj_zaduzenih_posuda")
    @NotNull(message = "unesi broj zaduzenih posuda")
    Integer brojZaduzenihPosuda=0;

    public Operant(){}

    public Operant(String id, String fullName, Integer brojZaduzenihKutija, Integer brojZaduzenihPosuda) {
        this.id = id;
        this.fullName = fullName;
        this.brojZaduzenihKutija = brojZaduzenihKutija;
        this.brojZaduzenihPosuda = brojZaduzenihPosuda;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getBrojZaduzenihKutija() {
        return brojZaduzenihKutija;
    }

    public void setBrojZaduzenihKutija(Integer brojZaduzenihKutija) {
        this.brojZaduzenihKutija = brojZaduzenihKutija;
    }

    public Integer getBrojZaduzenihPosuda() {
        return brojZaduzenihPosuda;
    }

    public void setBrojZaduzenihPosuda(Integer brojZaduzenihPosuda) {
        this.brojZaduzenihPosuda = brojZaduzenihPosuda;
    }

    @Override
    public String toString() {
        return "Operant{" +
                "id='" + id + '\'' +
                ", fullName='" + fullName + '\'' +
                ", brojZaduzenihKutija=" + brojZaduzenihKutija +
                ", brojZaduzenihPosuda=" + brojZaduzenihPosuda +
                '}';
    }
}
