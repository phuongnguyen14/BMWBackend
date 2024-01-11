package com.example.mockspring.form.customerTestDriving;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
@Getter
@Setter
@NoArgsConstructor

public class CreateTestDrivingForm {
    private String fullName;
    private String carName;
    private String phoneNumber;
    private Date dateTestDriving;


}
