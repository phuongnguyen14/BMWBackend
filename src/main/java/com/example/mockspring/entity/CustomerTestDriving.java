package com.example.mockspring.entity;

import com.example.mockspring.dto.CarDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.sql.Date;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "CustomerTestDriving")
public class CustomerTestDriving {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(name = "fullName",nullable = false)
    private String fullName;

    @Column(name = "phoneNumber",nullable = false)
    private String phoneNumber;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @Column(name = "dateTestDriving", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDate dateTestDriving;

    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false, referencedColumnName = "id")
    @JsonIgnoreProperties({"image", "price", "information", "detailInformation", "yearOfManufacturer", "carCategory"})
    private Car car;



}
