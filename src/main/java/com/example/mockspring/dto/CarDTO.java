package com.example.mockspring.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CarDTO {
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
    @JsonFormat(pattern = "dd-MM-yyyy")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @CreationTimestamp
    @UpdateTimestamp
    private Date createAt;
    private String carCategoryName;
}