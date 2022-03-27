package com.etiya.renACar.business.model.responses.ResponseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseCarDamageDto {

    private int id;

    //@JsonFormat(shape= JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private String damageDate;

    private String description;


    private int carId;




}
