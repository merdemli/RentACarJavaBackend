package com.etiya.renACar.repository.abstracts;

import com.etiya.renACar.model.entities.concretes.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice,Integer> {

    List<Invoice> getByUser_UserId(int userId); //müşteriye ait faturaların listesi

    //@Query("select i from Invoice i where i.createdAt between :startDate and :endDate")
    List<Invoice> getByCreatedAtBetween(LocalDate startDate, LocalDate endDate);
}

