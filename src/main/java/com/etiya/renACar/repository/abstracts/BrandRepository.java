package com.etiya.renACar.repository.abstracts;

import com.etiya.renACar.model.entities.concretes.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand,Integer> {


    boolean existsBrandByNameIgnoreCase(String name);
}
