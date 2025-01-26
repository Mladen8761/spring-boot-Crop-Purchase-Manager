package com.Mladen.otkupproizvoda.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
@Table(name="transakcija")
public class Transakcija {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="transakcija_id")
    Integer id;

    @Column(name="operant_id")
    String operantId;

    @Column(name="proizvod_id")
    @NotNull(message = "unesi proizvod")
    Integer proizvodId;

    @Column(name="broj_kutija")
    @NotNull(message = "unesi Broj Kutija")
    Integer brojKutija;

    @Column(name="kilaza")
    @NotNull(message = "unesi kilazu")
    double kilaza;

    @Column(name="datum")
    @NotNull(message = "unesi datum")
    LocalDate datum;

    public Transakcija(){}

    public Transakcija(String operantId, Integer proizvodId, Integer brojKutija, double kilaza, LocalDate datum) {
        this.operantId = operantId;
        this.proizvodId = proizvodId;
        this.brojKutija = brojKutija;
        this.kilaza = kilaza;
        this.datum = datum;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOperantId() {
        return operantId;
    }

    public void setOperantId(String operantId) {
        this.operantId = operantId;
    }

    public Integer getProizvodId() {
        return proizvodId;
    }

    public void setProizvodId(Integer proizvodId) {
        this.proizvodId = proizvodId;
    }

    public Integer getBrojKutija() {
        return brojKutija;
    }

    public void setBrojKutija(Integer brojKutija) {
        this.brojKutija = brojKutija;
    }

    public double getKilaza() {
        return kilaza;
    }

    public void setKilaza(double kilaza) {
        this.kilaza = kilaza;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    @Override
    public String toString() {
        return "Transakcija{" +
                "id=" + id +
                ", operantId='" + operantId + '\'' +
                ", proizvodId=" + proizvodId +
                ", brojKutija=" + brojKutija +
                ", kilaza=" + kilaza +
                ", datum=" + datum +
                '}';
    }
}
