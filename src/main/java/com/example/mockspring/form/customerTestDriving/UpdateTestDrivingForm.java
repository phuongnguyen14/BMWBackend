package com.example.mockspring.form.customerTestDriving;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
@Getter
@Setter
@NoArgsConstructor
public class UpdateTestDrivingForm {
    private int id;
    private String fullName;
    private String carName;  // Thay vì carId, bạn có thể trực tiếp nhập tên xe
    private String phoneNumber;
    private Date dateTestDriving;
}
