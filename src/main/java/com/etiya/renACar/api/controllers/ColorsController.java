package com.etiya.renACar.api.controllers;

import com.etiya.renACar.business.abstracts.ColorService;
import com.etiya.renACar.business.model.request.createRequest.CreateColorRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
