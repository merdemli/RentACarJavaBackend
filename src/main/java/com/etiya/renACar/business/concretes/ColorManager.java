package com.etiya.renACar.business.concretes;

import com.etiya.renACar.business.abstracts.ColorService;
import com.etiya.renACar.business.constants.messages.BusinessMessages;
import com.etiya.renACar.business.model.requests.createRequest.CreateColorRequest;
import com.etiya.renACar.business.model.responses.listResponseDto.ColorListResponse;
import com.etiya.renACar.core.crossCuttingConcerns.exceptionHandling.BusinessException;
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
        if(checkIfColorNameExists(createColorRequest.getName())) {
            throw new BusinessException(BusinessMessages.ColorMessages.COLOR_ALREADY_EXISTS);
        }
        else{
        Color color = this.modelMapperService.forRequest().map(createColorRequest, Color.class);
        this.colorRepository.save(color); }//refactor

    }

    @Override
    public List<ColorListResponse> getAll() {
        List<Color>colors = this.colorRepository.findAll();
        List<ColorListResponse> response = colors.stream()
                .map(color -> this.modelMapperService.forDto().map(color, ColorListResponse.class))
                .collect(Collectors.toList());
        return response;
    }

    //--------------------business rules----------------------------------

    private boolean checkIfColorNameExists(String colorName){
        return this.colorRepository.existsColorByNameIgnoreCase(colorName);
    }

}
