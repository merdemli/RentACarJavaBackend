package com.etiya.renACar.repository.abstracts;

import com.etiya.renACar.model.entities.concretes.AdditionalProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdditionalProductRepository extends JpaRepository<AdditionalProduct,Integer> {
}
