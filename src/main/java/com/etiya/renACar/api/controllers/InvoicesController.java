package com.etiya.renACar.api.controllers;


import com.etiya.renACar.business.abstracts.InvoiceService;
import com.etiya.renACar.business.model.requests.createRequest.CreateAdditionalProductRequest;
import com.etiya.renACar.business.model.requests.createRequest.CreateInvoiceRequest;
import com.etiya.renACar.business.model.requests.deleteRequest.DeleteAdditionalProductRequest;
import com.etiya.renACar.business.model.requests.deleteRequest.DeleteInvoiceRequest;
import com.etiya.renACar.business.model.requests.updateRequest.UpdateAdditionalProductRequest;
import com.etiya.renACar.business.model.requests.updateRequest.UpdateInvoiceRequest;
import com.etiya.renACar.business.model.responses.listResponseDto.InvoiceListResponse;
import com.etiya.renACar.core.utilities.results.DataResult;
import com.etiya.renACar.core.utilities.results.Result;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/invoices")
public class InvoicesController {

    private InvoiceService invoiceService;

    public InvoicesController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @PostMapping("/add")
    public Result add(@RequestBody @Valid CreateInvoiceRequest createInvoiceRequest) {
        return this.invoiceService.add(createInvoiceRequest);
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestBody @Valid DeleteInvoiceRequest deleteInvoiceRequest) {
        return this.invoiceService.delete(deleteInvoiceRequest);
    }

    @PutMapping("/update")
    public Result update(@RequestBody @Valid UpdateInvoiceRequest updateInvoiceRequest) {
        return this.invoiceService.update(updateInvoiceRequest);
    }

    @GetMapping("/getallbyuser")
    public DataResult<List<InvoiceListResponse>> getAllByUser( @RequestParam int userId){
        return this.invoiceService.getAllByUserUserId(userId);

    }

    @GetMapping("/getbycreateatbetween")
    public DataResult<List<InvoiceListResponse>>getByCreateAtBetween(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate
            , @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate){
        return this.invoiceService.getByCreateAtBetween(startDate,endDate);
    }

}
