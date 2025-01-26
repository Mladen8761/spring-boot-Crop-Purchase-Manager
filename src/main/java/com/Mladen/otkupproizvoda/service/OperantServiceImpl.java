package com.Mladen.otkupproizvoda.service;

import com.Mladen.otkupproizvoda.entity.Operant;
import com.Mladen.otkupproizvoda.repository.OperantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OperantServiceImpl implements OperantService{

    OperantRepository operantRepository;

    @Autowired
    public OperantServiceImpl(OperantRepository operantRepository) {
        this.operantRepository = operantRepository;
    }

    @Override
    public List<Operant> findAll() {
        return operantRepository.findAll();
    }

    @Override
    public Operant findById(String id) {
        Optional<Operant> result = operantRepository.findById(id);
        Operant operant=null;
        if(result.isPresent())
        {
            operant=result.get();
        }
        return operant;
    }

    @Override
    public void save(Operant operant) {
    operantRepository.save(operant);
    }

}
