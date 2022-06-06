package com.etiya.renACar.core.adapters.abstracts;

import com.etiya.renACar.model.entities.concretes.IndividualCustomer;
import com.etiya.renACar.model.entities.concretes.User;

public interface ValidatePersonService {

        boolean CheckIfRealPerson(IndividualCustomer individualCustomer) throws Exception;

}
