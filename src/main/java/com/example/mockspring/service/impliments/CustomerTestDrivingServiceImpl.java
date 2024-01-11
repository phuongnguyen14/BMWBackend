package com.example.mockspring.service.impliments;

import com.example.mockspring.entity.Car;
import com.example.mockspring.entity.CustomerTestDriving;
import com.example.mockspring.repository.CustomerTestDrivingRepository;
import com.example.mockspring.repository.ICarRepository;
import com.example.mockspring.service.CustomerTestDrivingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerTestDrivingServiceImpl implements CustomerTestDrivingService {

    @Autowired
    private CustomerTestDrivingRepository customerTestDrivingRepository;

    @Autowired
    private ICarRepository carRepository;

    @Override
    public Page<CustomerTestDriving> getAllCustomerTestDrivings(Pageable pageable) {
        return customerTestDrivingRepository.findAll(pageable);
    }
    @Override
    public CustomerTestDriving saveCustomerTestDriving(CustomerTestDriving customerTestDriving) {
        Optional<Car> optionalCar = carRepository.findByName(customerTestDriving.getCar().getName());

        if (optionalCar.isPresent()) {
            customerTestDriving.setCar(optionalCar.get());
            return customerTestDrivingRepository.save(customerTestDriving);
        } else {
            // Handle the case where Car is not found
            return null;
        }
    }

    @Override
    public CustomerTestDriving getCustomerTestDrivingById(int id) {
        Optional<CustomerTestDriving> optionalCustomerTestDriving = customerTestDrivingRepository.findById(id);
        return optionalCustomerTestDriving.orElse(null);
    }



    @Override
    public CustomerTestDriving updateCustomerTestDriving(int id, CustomerTestDriving updatedCustomerTestDriving) {
        Optional<CustomerTestDriving> optionalExistingCustomerTestDriving = customerTestDrivingRepository.findById(id);

        if (optionalExistingCustomerTestDriving.isPresent()) {
            CustomerTestDriving existingCustomerTestDriving = optionalExistingCustomerTestDriving.get();
            existingCustomerTestDriving.setFullName(updatedCustomerTestDriving.getFullName());
            existingCustomerTestDriving.setPhoneNumber(updatedCustomerTestDriving.getPhoneNumber());
            existingCustomerTestDriving.setDateTestDriving(updatedCustomerTestDriving.getDateTestDriving());

            Optional<Car> optionalCar = Optional.ofNullable(updatedCustomerTestDriving.getCar())
                    .flatMap(car -> carRepository.findByName(car.getName()));

            if (optionalCar.isPresent()) {
                existingCustomerTestDriving.setCar(optionalCar.get());
                return customerTestDrivingRepository.save(existingCustomerTestDriving);
            } else {
                // Handle the case where Car is not found
                return null;
            }
        } else {
            return null;
        }
    }

    @Override
    public void deleteCustomerTestDriving(int id) {
        customerTestDrivingRepository.deleteById(id);
    }
}
