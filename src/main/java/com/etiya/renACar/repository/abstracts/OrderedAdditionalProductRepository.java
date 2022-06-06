package com.etiya.renACar.repository.abstracts;

import com.etiya.renACar.model.entities.concretes.OrderedAdditionalProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderedAdditionalProductRepository extends JpaRepository<OrderedAdditionalProduct,Integer> {

    List<OrderedAdditionalProduct> getAllByRental_Id(int rentalId);
    boolean existsByRental_Id(int rentalId);
}
