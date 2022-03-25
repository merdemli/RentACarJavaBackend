package com.etiya.renACar.business.model.requests.createRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBrandRequest {  //insert

    private String name; //id'ye gerek yok, zaten sisteme eklenirken otomatik atanıyor
}
