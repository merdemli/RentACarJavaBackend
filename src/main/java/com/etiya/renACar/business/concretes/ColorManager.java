package com.etiya.renACar.business.concretes;

import com.etiya.renACar.business.abstracts.ColorService;
import com.etiya.renACar.business.model.requests.createRequest.CreateColorRequest;
import com.etiya.renACar.business.model.responses.ResponseDto.ResponseColorDto;
import com.etiya.renACar.core.utilities.mapping.ModelMapperService;
import com.etiya.renACar.model.entities.concretes.Color;
import com.etiya.renACar.repository.abstracts.ColorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ColorManager implements ColorService {

    private ColorRepository colorRepository;
    private ModelMapperService modelMapperService;

    public ColorManager(ColorRepository colorRepository, ModelMapperService modelMapperService )
    {
        this.colorRepository = colorRepository;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public void add(CreateColorRequest createColorRequest) {
//        Color color = new Color();
//        color.setName(createColorRequest.getName());
        Color color = this.modelMapperService.forRequest().map(createColorRequest, Color.class);
        this.colorRepository.save(color);
    }

    @Override
    public List<ResponseColorDto> getAll() {
        List<Color>colors = this.colorRepository.findAll();
        List<ResponseColorDto> response = colors.stream()
                .map(color -> this.modelMapperService.forDto().map(color, ResponseColorDto.class))
                .collect(Collectors.toList());
        return response;
    }

}
