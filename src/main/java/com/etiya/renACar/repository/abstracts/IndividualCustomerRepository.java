package com.etiya.renACar.repository.abstracts;

import com.etiya.renACar.model.entities.concretes.IndividualCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndividualCustomerRepository extends JpaRepository<IndividualCustomer,Integer>{
}
