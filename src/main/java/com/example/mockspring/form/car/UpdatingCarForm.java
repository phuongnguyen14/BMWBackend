package com.example.mockspring.form.car;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
public class UpdatingCarForm {
    private int id;
    private String name;
    private String image;
    private String price;
    private String information;
    private String detailInformation;
    private int yearOfManufacturer;
    private int seatingCapacity;
    private String carFuel;
    private String placeOfManufacture;
    private String transmission;
    private String status;
//    @JsonDeserialize(using = com.vti.testing.json.CustomDateDeserializer.class)
    private Date createAt;
    private String carCategoryName;
}