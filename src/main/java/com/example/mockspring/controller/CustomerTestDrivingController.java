package com.example.mockspring.controller;

import com.example.mockspring.entity.Car;
import com.example.mockspring.entity.CustomerTestDriving;
import com.example.mockspring.service.CustomerTestDrivingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public ResponseEntity<CustomerTestDriving> createCustomerTestDriving(@RequestBody CustomerTestDriving customerTestDriving) {
        CustomerTestDriving createdCustomerTestDriving = customerTestDrivingService.saveCustomerTestDriving(customerTestDriving);

        if (createdCustomerTestDriving != null) {
            // Set Car to contain only id and name
            Car car = new Car();
            car.setId(createdCustomerTestDriving.getCar().getId());
            car.setName(createdCustomerTestDriving.getCar().getName());
            createdCustomerTestDriving.setCar(car);

            return new ResponseEntity<>(createdCustomerTestDriving, HttpStatus.CREATED);
        } else {
            // Handle the case where Car is not found or other validation fails
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<CustomerTestDriving> getCustomerTestDriving(@PathVariable int id) {
        CustomerTestDriving customerTestDriving = customerTestDrivingService.getCustomerTestDrivingById(id);

        if (customerTestDriving != null) {
            // Set Car to contain only id and name
            Car car = new Car();
            car.setId(customerTestDriving.getCar().getId());
            car.setName(customerTestDriving.getCar().getName());
            customerTestDriving.setCar(car);

            return new ResponseEntity<>(customerTestDriving, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<Page<CustomerTestDriving>> getAllCustomerTestDrivings(Pageable pageable) {
        Page<CustomerTestDriving> customerTestDrivingsPage = customerTestDrivingService.getAllCustomerTestDrivings(pageable);

        // Set Car for each item to contain only id and name
        for (CustomerTestDriving customerTestDriving : customerTestDrivingsPage.getContent()) {
            Car car = new Car();
            car.setId(customerTestDriving.getCar().getId());
            car.setName(customerTestDriving.getCar().getName());
            customerTestDriving.setCar(car);
        }

        return new ResponseEntity<>(customerTestDrivingsPage, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerTestDriving> updateCustomerTestDriving(
            @PathVariable int id,
            @RequestBody CustomerTestDriving updatedCustomerTestDriving) {

        CustomerTestDriving updatedCustomerTestDrivingResult = customerTestDrivingService.updateCustomerTestDriving(id, updatedCustomerTestDriving);

        if (updatedCustomerTestDrivingResult != null) {
            // Set Car to contain only id and name
            Car car = new Car();
            car.setId(updatedCustomerTestDrivingResult.getCar().getId());
            car.setName(updatedCustomerTestDrivingResult.getCar().getName());
            updatedCustomerTestDrivingResult.setCar(car);

            return new ResponseEntity<>(updatedCustomerTestDrivingResult, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomerTestDriving(@PathVariable int id) {
        customerTestDrivingService.deleteCustomerTestDriving(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
