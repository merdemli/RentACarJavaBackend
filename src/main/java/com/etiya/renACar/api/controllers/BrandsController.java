package com.etiya.renACar.api.controllers;

import com.etiya.renACar.business.abstracts.BrandService;
import com.etiya.renACar.business.model.requests.createRequest.CreateBrandRequest;
import com.etiya.renACar.business.model.responses.listDtos.ListBrandDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/brands")
public class BrandsController {

    private BrandService brandService;

    //@Autowired
    public BrandsController(BrandService brandService) {
        this.brandService = brandService;
    }

    @PostMapping("/add")
    public void add(@RequestBody CreateBrandRequest createBrandRequest){
        this.brandService.add(createBrandRequest);
    }

    @GetMapping("/getAll")
    public List<ListBrandDto> getAll(){
       return this.brandService.getAll();
    }
}
