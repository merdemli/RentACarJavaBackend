package com.etiya.renACar.business.abstracts;

import com.etiya.renACar.business.model.request.createRequest.CreateCarRequest;

public interface CarService {
    void add(CreateCarRequest createCarRequest);
}
