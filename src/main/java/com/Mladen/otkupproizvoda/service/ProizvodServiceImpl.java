package com.Mladen.otkupproizvoda.service;

import com.Mladen.otkupproizvoda.entity.Proizvod;
import com.Mladen.otkupproizvoda.repository.ProizvodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProizvodServiceImpl implements ProizvodService{
    ProizvodRepository proizvodRepository;

    @Autowired
    public ProizvodServiceImpl(ProizvodRepository proizvodRepository) {
        this.proizvodRepository = proizvodRepository;
    }

    @Override
    public Proizvod findById(int id) {
        Optional<Proizvod> result = proizvodRepository.findById(id);
        Proizvod proizvod=null;
        if(result.isPresent())
        {
            proizvod=result.get();
        }
        return proizvod;
    }
}
