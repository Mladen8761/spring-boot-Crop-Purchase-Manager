package com.Mladen.otkupproizvoda.repository;

import com.Mladen.otkupproizvoda.entity.Transakcija;
import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransakcijaRepository extends JpaRepository<Transakcija,Integer> {
}
