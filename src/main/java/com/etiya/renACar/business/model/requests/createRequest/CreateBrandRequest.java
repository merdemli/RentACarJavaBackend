package com.etiya.renACar.business.model.requests.createRequest;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBrandRequest {  //insert

    @JsonIgnore
    private int id;

    private String name; //id'ye gerek yok, zaten sisteme eklenirken otomatik atanıyor
}
