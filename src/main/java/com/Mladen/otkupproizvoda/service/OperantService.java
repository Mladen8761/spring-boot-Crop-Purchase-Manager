package com.Mladen.otkupproizvoda.service;

import com.Mladen.otkupproizvoda.entity.Operant;

import java.util.List;

public interface OperantService {
    List<Operant> findAll();
    Operant findById(String id);
    void save(Operant operant);


}
