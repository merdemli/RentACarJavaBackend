package com.etiya.renACar.business.concretes;

import com.etiya.renACar.business.abstracts.IndividualCustomerService;
import com.etiya.renACar.business.constants.messages.BusinessMessages;
import com.etiya.renACar.business.model.requests.createRequest.CreateIndividualCustomerRequest;
import com.etiya.renACar.core.utilities.mapping.ModelMapperService;
import com.etiya.renACar.core.utilities.results.Result;
import com.etiya.renACar.core.utilities.results.SuccessResult;
import com.etiya.renACar.model.entities.concretes.IndividualCustomer;
import com.etiya.renACar.repository.abstracts.IndividualCustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IndividualCustomerManager implements IndividualCustomerService {


    @Autowired
    private IndividualCustomerRepository individualCustomerRepository;
    @Autowired
    private ModelMapperService modelMapperService;


    @Override
    public Result add(CreateIndividualCustomerRequest createIndividualCustomerRequest) {

        IndividualCustomer customer= this.modelMapperService.forRequest()
                .map(createIndividualCustomerRequest, IndividualCustomer.class);
        this.individualCustomerRepository.save(customer);
        return new SuccessResult(BusinessMessages.IndividualCustomerMessages.INDIVIDUAL_CUSTOMER_ADDED_SUCCESSFULLY);

    }
}
