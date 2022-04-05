package com.etiya.renACar.repository.abstracts;

import com.etiya.renACar.core.utilities.results.DataResult;
import com.etiya.renACar.model.entities.concretes.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice,Integer> {

    List<Invoice> getByUser_UserId(int userId);
    List<Invoice> findByCreatedDateBetween(LocalDate startDate, LocalDate endDate);
}
