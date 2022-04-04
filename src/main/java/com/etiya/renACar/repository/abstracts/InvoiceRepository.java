package com.etiya.renACar.repository.abstracts;

import com.etiya.renACar.model.entities.concretes.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice,Integer> {
}
