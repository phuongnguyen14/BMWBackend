package com.example.mockspring.service;

import com.example.mockspring.entity.CustomerTestDriving;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomerTestDrivingService {

    CustomerTestDriving saveCustomerTestDriving(CustomerTestDriving customerTestDriving);

    CustomerTestDriving getCustomerTestDrivingById(int id);

    Page<CustomerTestDriving> getAllCustomerTestDrivings(Pageable pageable);

    CustomerTestDriving updateCustomerTestDriving(int id, CustomerTestDriving updatedCustomerTestDriving);

    void deleteCustomerTestDriving(int id);
}
