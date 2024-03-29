package com.etiya.renACar.business.abstracts;

import com.etiya.renACar.business.model.requests.createRequest.CreateColorRequest;
import com.etiya.renACar.business.model.responses.listResponseDto.ColorListResponse;

import java.util.List;

public interface ColorService {
    void add(CreateColorRequest createColorRequest);

    List<ColorListResponse>getAll();
}
