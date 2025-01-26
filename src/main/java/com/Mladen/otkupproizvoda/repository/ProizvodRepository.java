package com.Mladen.otkupproizvoda.repository;

import com.Mladen.otkupproizvoda.entity.Proizvod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProizvodRepository extends JpaRepository<Proizvod,Integer> {
}
