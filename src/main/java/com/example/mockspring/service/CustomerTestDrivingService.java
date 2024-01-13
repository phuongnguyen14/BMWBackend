package com.example.mockspring.service;

import com.example.mockspring.entity.CustomerTestDriving;
import com.example.mockspring.form.customerTestDriving.CreateTestDrivingForm;
import com.example.mockspring.form.customerTestDriving.TestDrivingFilterForm;
import com.example.mockspring.form.customerTestDriving.UpdateTestDrivingForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface CustomerTestDrivingService {

    Map<String, Object> createCustomerTestDriving(CreateTestDrivingForm form);

    Map<String, Object> getCustomerTestDrivingById(int id);
    Page<Map<String, Object>> getAllCustomerTestDrivings(Pageable pageable, TestDrivingFilterForm filterForm);

    Map<String, Object> updateCustomerTestDriving(int id, UpdateTestDrivingForm form);

    void deleteCustomerTestDriving(int id);
}
