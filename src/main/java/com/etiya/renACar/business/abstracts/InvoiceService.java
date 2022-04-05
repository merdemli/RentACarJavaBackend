package com.etiya.renACar.business.abstracts;

import com.etiya.renACar.business.model.requests.createRequest.CreateInvoiceRequest;
import com.etiya.renACar.business.model.requests.deleteRequest.DeleteInvoiceRequest;
import com.etiya.renACar.business.model.requests.updateRequest.UpdateInvoiceRequest;
import com.etiya.renACar.business.model.responses.listResponseDto.InvoiceListResponse;
import com.etiya.renACar.core.utilities.results.DataResult;
import com.etiya.renACar.core.utilities.results.Result;
import com.etiya.renACar.model.entities.concretes.Invoice;

import java.time.LocalDate;
import java.util.List;

public interface InvoiceService {

    Result add(CreateInvoiceRequest createInvoiceRequest);
    Result delete(DeleteInvoiceRequest deleteInvoiceRequest);
    Result update(UpdateInvoiceRequest updateInvoiceRequest);

    DataResult<List<Invoice>>findByCreatedDateBetween(LocalDate startDate, LocalDate endDate);

    DataResult<List<InvoiceListResponse>> getAllByUserUserId(int userId);
}
