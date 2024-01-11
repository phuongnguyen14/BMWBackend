package com.example.mockspring.dto;

import com.example.mockspring.entity.Account;
import com.example.mockspring.entity.Car;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Data
@Getter
@Setter
@NoArgsConstructor
public class CustomerTestDrivingDto {
    private int id;
//    private Account account;
    private String fullName;
    private String phoneNumber;
    private Date dateTestDriving;
    private Car car;
//    private String email;
}
