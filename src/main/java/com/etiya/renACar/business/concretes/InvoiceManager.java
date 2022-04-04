package com.etiya.renACar.business.concretes;

import com.etiya.renACar.business.abstracts.InvoiceService;
import com.etiya.renACar.business.constants.messages.BusinessMessages;
import com.etiya.renACar.business.model.requests.createRequest.CreateInvoiceRequest;
import com.etiya.renACar.business.model.requests.deleteRequest.DeleteInvoiceRequest;
import com.etiya.renACar.business.model.requests.updateRequest.UpdateInvoiceRequest;
import com.etiya.renACar.core.utilities.mapping.ModelMapperService;
import com.etiya.renACar.core.utilities.results.Result;
import com.etiya.renACar.core.utilities.results.SuccessResult;
import com.etiya.renACar.model.entities.concretes.Invoice;
import com.etiya.renACar.repository.abstracts.InvoiceRepository;
import org.springframework.stereotype.Service;

@Service
public class InvoiceManager implements InvoiceService {

    private InvoiceRepository invoiceRepository;
    private ModelMapperService modelMapperService;

    public InvoiceManager(InvoiceRepository invoiceRepository, ModelMapperService modelMapperService) {
        this.invoiceRepository = invoiceRepository;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public Result add(CreateInvoiceRequest createInvoiceRequest) {
        Invoice invoice = this.modelMapperService.forRequest().map(createInvoiceRequest,Invoice.class);
        this.invoiceRepository.save(invoice);
        return new SuccessResult(BusinessMessages.InvoiceMessages.INVOICE_ADDED_SUCCESSFULLY);
    }
    @Override
    public Result delete(DeleteInvoiceRequest deleteInvoiceRequest) {
        Invoice invoice = this.modelMapperService.forRequest().map(deleteInvoiceRequest,Invoice.class);
        this.invoiceRepository.delete(invoice);
        return new SuccessResult(BusinessMessages.InvoiceMessages.INVOICE_DELETED_SUCCESSFULLY);
    }

    @Override
    public Result update(UpdateInvoiceRequest updateInvoiceRequest) {
        Invoice invoice = this.modelMapperService.forRequest().map(updateInvoiceRequest,Invoice.class);
        this.invoiceRepository.save(invoice);
        return new SuccessResult(BusinessMessages.InvoiceMessages.INVOICE_UPDATED_SUCCESSFULLY);
    }
}
