package com.example.mockspring.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "`name`" ,length = 50, nullable = false, unique = true)
    private String name;

    @Column(name = "image" ,length = 50, nullable = false)
    private String image;

    @Column(name = "price" ,length = 50, nullable = false)
    private String price;

    @Column(name = "information" ,length = 500, nullable = false)
    private String information;

    @Column(name = "detail_information" ,length = 500, nullable = false)
    private String detailInformation;

    @Column(name = "yearOfManufacturer" , nullable = false)
    private int yearOfManufacturer;

    @Column(name = "seating_capacity" , nullable = false)
    private int seatingCapacity;

    @Column(name = "car_fuel" ,length = 50, nullable = false)
    private String carFuel;

    @Column(name = "placeOfManufacture" ,length = 50, nullable = false)
    private String placeOfManufacture;

    @Column(name = "transmission" ,length = 50, nullable = false)
    private String transmission;

    @Column(name = "`status`" ,length = 50, nullable = false)
    private String status;

    @Column(name = "create_at")
    private Date createAt;

    @ManyToOne
    @JoinColumn(name="category_id")
    private CarCategory carCategory;
}
