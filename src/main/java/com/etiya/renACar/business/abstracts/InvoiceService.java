package com.etiya.renACar.business.abstracts;

import com.etiya.renACar.business.model.requests.createRequest.CreateInvoiceRequest;
import com.etiya.renACar.business.model.requests.deleteRequest.DeleteInvoiceRequest;
import com.etiya.renACar.business.model.requests.updateRequest.UpdateInvoiceRequest;
import com.etiya.renACar.core.utilities.results.Result;

public interface InvoiceService {

    Result add(CreateInvoiceRequest createInvoiceRequest);
    Result delete(DeleteInvoiceRequest deleteInvoiceRequest);
    Result update(UpdateInvoiceRequest updateInvoiceRequest);
}
