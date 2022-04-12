package com.etiya.renACar.business.abstracts;

import com.etiya.renACar.business.model.requests.createRequest.CreateUserRequest;
import com.etiya.renACar.business.model.requests.deleteRequest.DeleteRentalRequest;
import com.etiya.renACar.business.model.requests.updateRequest.UpdateRentalRequest;
import com.etiya.renACar.core.utilities.results.Result;

public interface UserService {

    Result add(CreateUserRequest createUserRequest);

}
