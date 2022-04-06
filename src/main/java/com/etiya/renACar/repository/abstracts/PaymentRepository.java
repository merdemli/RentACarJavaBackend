package com.etiya.renACar.repository.abstracts;

import com.etiya.renACar.model.entities.concretes.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Integer> {
}
