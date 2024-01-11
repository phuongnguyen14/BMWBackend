package com.example.mockspring.service;

import com.example.mockspring.entity.CustomerTestDriving;
import com.example.mockspring.form.customerTestDriving.CreateTestDrivingForm;
import com.example.mockspring.form.customerTestDriving.UpdateTestDrivingForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomerTestDrivingService {

    CustomerTestDriving createCustomerTestDriving(CreateTestDrivingForm form);

    CustomerTestDriving getCustomerTestDrivingById(int id);

    Page<CustomerTestDriving> getAllCustomerTestDrivings(Pageable pageable);

    CustomerTestDriving updateCustomerTestDriving(int id, UpdateTestDrivingForm form);


    void deleteCustomerTestDriving(int id);
}
