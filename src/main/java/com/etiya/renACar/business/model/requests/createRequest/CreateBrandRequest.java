package com.etiya.renACar.business.model.requests.createRequest;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBrandRequest {  //insert

    @JsonIgnore
    private int id;

    @NotNull
    @Length(min = 2, message = "Length must be at least 2 characters") //responsebody mesajı 400error,swagger
    private String name; //id'ye gerek yok, zaten sisteme eklenirken otomatik atanıyor
}
