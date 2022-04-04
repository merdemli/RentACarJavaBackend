package com.etiya.renACar.repository.abstracts;

import com.etiya.renACar.model.entities.concretes.OrderedAdditionalProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderedAdditionalProductRepository extends JpaRepository<OrderedAdditionalProduct,Integer> {
}
