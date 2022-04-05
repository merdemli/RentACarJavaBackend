package com.etiya.renACar.business.concretes;

import com.etiya.renACar.business.abstracts.InvoiceService;
import com.etiya.renACar.business.constants.messages.BusinessMessages;
import com.etiya.renACar.business.model.requests.createRequest.CreateInvoiceRequest;
import com.etiya.renACar.business.model.requests.deleteRequest.DeleteInvoiceRequest;
import com.etiya.renACar.business.model.requests.updateRequest.UpdateInvoiceRequest;
import com.etiya.renACar.business.model.responses.listResponseDto.CarListResponse;
import com.etiya.renACar.business.model.responses.listResponseDto.InvoiceListResponse;
import com.etiya.renACar.core.utilities.mapping.ModelMapperService;
import com.etiya.renACar.core.utilities.results.DataResult;
import com.etiya.renACar.core.utilities.results.Result;
import com.etiya.renACar.core.utilities.results.SuccessDataResult;
import com.etiya.renACar.core.utilities.results.SuccessResult;
import com.etiya.renACar.model.entities.concretes.Car;
import com.etiya.renACar.model.entities.concretes.Invoice;
import com.etiya.renACar.repository.abstracts.InvoiceRepository;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public DataResult<List<Invoice>> findByCreatedDateBetween(LocalDate startDate, LocalDate endDate) {

        return null;
    }

    @Override
    public DataResult<List<InvoiceListResponse>> getAllByUserUserId(int userId) {
        List<Invoice>invoices = this.invoiceRepository.getByUser_UserId(userId);
        return map(invoices);
    }

    private DataResult< List<InvoiceListResponse>> map(List<Invoice> invoices) {
        List<InvoiceListResponse> dtos = invoices.stream()//"stream of car" döner
                .map(invoice -> this.modelMapperService.forDto().map(invoice, InvoiceListResponse.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<List<InvoiceListResponse>>
                (dtos,BusinessMessages.InvoiceMessages.INVOICE_LISTED_SUCCESSFULLY);

    }
}
