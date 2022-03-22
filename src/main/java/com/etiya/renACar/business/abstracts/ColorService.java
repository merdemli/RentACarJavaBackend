package com.etiya.renACar.business.abstracts;

import com.etiya.renACar.business.model.request.createRequest.CreateColorRequest;

public interface ColorService {
    void add(CreateColorRequest createColorRequest);
}
