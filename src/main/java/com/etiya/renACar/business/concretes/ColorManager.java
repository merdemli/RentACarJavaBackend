package com.etiya.renACar.business.concretes;

import com.etiya.renACar.business.abstracts.ColorService;
import com.etiya.renACar.business.model.request.createRequest.CreateColorRequest;
import com.etiya.renACar.model.entities.concretes.Color;
import com.etiya.renACar.repository.abstracts.ColorRepository;
import org.springframework.stereotype.Service;

@Service
public class ColorManager implements ColorService {

    private ColorRepository colorRepository;

    public ColorManager(ColorRepository colorRepository) {
        this.colorRepository = colorRepository;
    }

    @Override
    public void add(CreateColorRequest createColorRequest) {

        Color color = new Color();
        color.setName(createColorRequest.getName());
        this.colorRepository.save(color);







    }
}
