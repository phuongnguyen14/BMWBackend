package com.example.mockspring.controller;

import com.example.mockspring.entity.CustomerTestDriving;
import com.example.mockspring.form.customerTestDriving.CreateTestDrivingForm;
import com.example.mockspring.form.customerTestDriving.TestDrivingFilterForm;
import com.example.mockspring.form.customerTestDriving.UpdateTestDrivingForm;
import com.example.mockspring.service.CustomerTestDrivingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customerTestDriving")
public class CustomerTestDrivingController {

    @Autowired
    private CustomerTestDrivingService customerTestDrivingService;

//        @GetMapping("/all")
//    public ResponseEntity<Page<CustomerTestDriving>> getAllCustomerTestDrivings(Pageable pageable) {
//        Page<CustomerTestDriving> customerTestDrivings = customerTestDrivingService.getAllCustomerTestDrivings(pageable);
//        return ResponseEntity.ok(customerTestDrivings);
//    }
    @GetMapping
    public ResponseEntity<Page<CustomerTestDriving>> getAllCustomerTestDrivings(Pageable pageable,
                                                                                @ModelAttribute TestDrivingFilterForm filterForm) {
        Page<CustomerTestDriving> customerTestDrivings = customerTestDrivingService.getAllCustomerTestDrivings(pageable, filterForm);
        return ResponseEntity.ok(customerTestDrivings);
    }

    @PostMapping("/create")
    public ResponseEntity<CustomerTestDriving> createCustomerTestDriving(@RequestBody CreateTestDrivingForm form) {
        CustomerTestDriving createdCustomerTestDriving = customerTestDrivingService.createCustomerTestDriving(form);
        if (createdCustomerTestDriving != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomerTestDriving);
        } else {
            // Handle the case where Car is not found or other errors
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerTestDriving> getCustomerTestDrivingById(@PathVariable int id) {
        CustomerTestDriving customerTestDriving = customerTestDrivingService.getCustomerTestDrivingById(id);
        if (customerTestDriving != null) {
            return ResponseEntity.ok(customerTestDriving);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CustomerTestDriving> updateCustomerTestDriving(@PathVariable int id, @RequestBody UpdateTestDrivingForm form) {
        CustomerTestDriving updatedCustomerTestDriving = customerTestDrivingService.updateCustomerTestDriving(id, form);
        if (updatedCustomerTestDriving != null) {
            return ResponseEntity.ok(updatedCustomerTestDriving);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomerTestDriving(@PathVariable int id) {
        customerTestDrivingService.deleteCustomerTestDriving(id);
        return ResponseEntity.noContent().build();
    }
}
