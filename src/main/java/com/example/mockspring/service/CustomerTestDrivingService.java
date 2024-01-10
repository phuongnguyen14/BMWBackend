package com.example.mockspring.service;

import com.example.mockspring.entity.CustomerTestDriving;



import java.util.List;

public interface CustomerTestDrivingService {
    CustomerTestDriving saveCustomerTestDriving(CustomerTestDriving customerTestDriving);
    CustomerTestDriving getCustomerTestDrivingById(int id);
    List<CustomerTestDriving> getAllCustomerTestDrivings();
    CustomerTestDriving updateCustomerTestDriving(int id, CustomerTestDriving updatedCustomerTestDriving);

    void deleteCustomerTestDriving(int id);
}
