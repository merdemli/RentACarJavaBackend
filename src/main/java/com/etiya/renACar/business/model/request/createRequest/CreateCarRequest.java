package com.etiya.renACar.business.model.request.createRequest;

import com.etiya.renACar.model.entities.concretes.Brand;
import com.etiya.renACar.model.entities.concretes.Color;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CreateCarRequest {


    private double dailyPrice;

    private String description;

    private String modelYear;

    private int colorId;

    private int brandId;
}
