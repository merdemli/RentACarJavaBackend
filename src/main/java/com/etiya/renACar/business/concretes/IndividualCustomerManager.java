package com.etiya.renACar.business.concretes;

import com.etiya.renACar.business.abstracts.IndividualCustomerService;
import com.etiya.renACar.business.constants.messages.BusinessMessages;
import com.etiya.renACar.business.model.requests.createRequest.CreateIndividualCustomerRequest;
import com.etiya.renACar.business.model.responses.listResponseDto.BrandListResponseDto;
import com.etiya.renACar.business.model.responses.listResponseDto.IndividualCustomerListResponse;
import com.etiya.renACar.core.utilities.mapping.ModelMapperService;
import com.etiya.renACar.core.utilities.results.DataResult;
import com.etiya.renACar.core.utilities.results.Result;
import com.etiya.renACar.core.utilities.results.SuccessDataResult;
import com.etiya.renACar.core.utilities.results.SuccessResult;
import com.etiya.renACar.model.entities.concretes.Brand;
import com.etiya.renACar.model.entities.concretes.IndividualCustomer;
import com.etiya.renACar.repository.abstracts.IndividualCustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IndividualCustomerManager implements IndividualCustomerService {


    @Autowired
    private IndividualCustomerRepository individualCustomerRepository;
    @Autowired
    private ModelMapperService modelMapperService;


    @Override
    public Result add(CreateIndividualCustomerRequest createIndividualCustomerRequest) {

        IndividualCustomer customer = this.modelMapperService.forRequest()
                .map(createIndividualCustomerRequest, IndividualCustomer.class);
        this.individualCustomerRepository.save(customer);
        return new SuccessResult(BusinessMessages.IndividualCustomerMessages.INDIVIDUAL_CUSTOMER_ADDED_SUCCESSFULLY);

    }

    @Override
    public DataResult<List<IndividualCustomerListResponse>> getAll() {

        List<IndividualCustomer> individualCustomers = this.individualCustomerRepository.findAll();
        List<IndividualCustomerListResponse> response = individualCustomers.stream()
                .map(individualCustomer -> this.modelMapperService.forDto().map(individualCustomer, IndividualCustomerListResponse.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<>(response, BusinessMessages.IndividualCustomerMessages.INDIVIDUAL_CUSTOMER_LISTED_SUCCESSFULLY);
    }


}
