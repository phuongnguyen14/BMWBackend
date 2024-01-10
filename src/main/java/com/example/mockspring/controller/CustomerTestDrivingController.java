package com.example.mockspring.controller;

import com.example.mockspring.entity.CustomerTestDriving;
import com.example.mockspring.service.CustomerTestDrivingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customerTestDriving")
public class CustomerTestDrivingController {

    @Autowired
    private CustomerTestDrivingService customerTestDrivingService;

    @PostMapping("/create")
    public ResponseEntity<CustomerTestDriving> saveCustomerTestDriving(@RequestBody CustomerTestDriving customerTestDriving) {
        CustomerTestDriving createdCustomerTestDriving = customerTestDrivingService.saveCustomerTestDriving(customerTestDriving);
        if (createdCustomerTestDriving != null) {
            return new ResponseEntity<>(createdCustomerTestDriving, HttpStatus.CREATED);
        } else {
            // Handle the case where the creation was not successful
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerTestDriving> getCustomerTestDrivingById(@PathVariable int id) {
        CustomerTestDriving customerTestDriving = customerTestDrivingService.getCustomerTestDrivingById(id);
        if (customerTestDriving != null) {
            return new ResponseEntity<>(customerTestDriving, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<CustomerTestDriving>> getAllCustomerTestDrivings() {
        List<CustomerTestDriving> customerTestDrivings = customerTestDrivingService.getAllCustomerTestDrivings();
        return new ResponseEntity<>(customerTestDrivings, HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<CustomerTestDriving> updateCustomerTestDriving(
            @PathVariable int id, @RequestBody CustomerTestDriving updatedCustomerTestDriving) {
        CustomerTestDriving updatedEntity = customerTestDrivingService.updateCustomerTestDriving(id, updatedCustomerTestDriving);
        if (updatedEntity != null) {
            return new ResponseEntity<>(updatedEntity, HttpStatus.OK);
        } else {
            // Handle the case where the update was not successful
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomerTestDriving(@PathVariable int id) {
        customerTestDrivingService.deleteCustomerTestDriving(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
