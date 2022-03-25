package com.etiya.renACar.api.controllers;

import com.etiya.renACar.business.abstracts.ColorService;
import com.etiya.renACar.business.model.requests.createRequest.CreateColorRequest;
import com.etiya.renACar.business.model.responses.listDtos.ListColorDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/colors")
public class ColorsController {

    private ColorService colorService;

    public ColorsController(ColorService colorService) {
        this.colorService = colorService;
    }

    @PostMapping("/add")
    public void add(@RequestBody CreateColorRequest createColorRequest){

        this.colorService.add(createColorRequest);
    }

    @GetMapping("/getAll")
    public List<ListColorDto>getAll(){
        return this.colorService.getAll();
    }
}
