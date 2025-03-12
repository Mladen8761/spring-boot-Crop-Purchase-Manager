package com.Mladen.otkupproizvoda.service;

import com.Mladen.otkupproizvoda.entity.Operant;
import com.Mladen.otkupproizvoda.entity.Transakcija;
import com.Mladen.otkupproizvoda.repository.TransakcijaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransakcijaServiceImpl implements TransakcijaService{
    TransakcijaRepository transakcijaRepository;
    OperantService operantService;

    @Autowired
    public TransakcijaServiceImpl(TransakcijaRepository transakcijaRepository, OperantService operantService) {
        this.transakcijaRepository = transakcijaRepository;
        this.operantService = operantService;
    }

    @Override
    public void save(Transakcija transakcija) {
        transakcija.setKilaza(Math.round((transakcija.getKilaza()-(transakcija.getBrojKutija()*0.5))*100.0)/100.0);
        Operant tempOperant= operantService.findById(transakcija.getOperantId());
        tempOperant.setBrojZaduzenihKutija(tempOperant.getBrojZaduzenihKutija()-transakcija.getBrojKutija());
        tempOperant.setBrojZaduzenihPosuda(tempOperant.getBrojZaduzenihPosuda()-(transakcija.getBrojKutija()*8));
        operantService.save(tempOperant);
        transakcijaRepository.save(transakcija);
    }
}
