package com.example.mockspring.service.impliments;

import com.example.mockspring.entity.Car;
import com.example.mockspring.entity.CustomerTestDriving;
import com.example.mockspring.form.customerTestDriving.CreateTestDrivingForm;
import com.example.mockspring.form.customerTestDriving.UpdateTestDrivingForm;
import com.example.mockspring.repository.CustomerTestDrivingRepository;
import com.example.mockspring.repository.ICarRepository;
import com.example.mockspring.service.CustomerTestDrivingService;
import org.modelmapper.ModelMapper;
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

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Page<CustomerTestDriving> getAllCustomerTestDrivings(Pageable pageable) {
        return customerTestDrivingRepository.findAll(pageable);
    }
    @Override
    public CustomerTestDriving createCustomerTestDriving(CreateTestDrivingForm form) {
        // Assume carId is a valid identifier for Car
        Optional<Car> optionalCar = carRepository.findByName(form.getCarName());
        if (optionalCar.isPresent()) {
            Car car = optionalCar.get();

            CustomerTestDriving customerTestDriving = new CustomerTestDriving();
            customerTestDriving.setFullName(form.getFullName());
            customerTestDriving.setPhoneNumber(form.getPhoneNumber());
            customerTestDriving.setDateTestDriving(form.getDateTestDriving());
            customerTestDriving.setCar(car);

            return customerTestDrivingRepository.save(customerTestDriving);
        } else {
            // Handle the case when Car with given name is not found
            return null;
        }
    }


    @Override
    public CustomerTestDriving getCustomerTestDrivingById(int id) {
        Optional<CustomerTestDriving> optionalCustomerTestDriving = customerTestDrivingRepository.findById(id);
        return optionalCustomerTestDriving.orElse(null);
    }


    @Override
    public CustomerTestDriving updateCustomerTestDriving(int id, UpdateTestDrivingForm form) {
        Optional<CustomerTestDriving> optionalEntity = customerTestDrivingRepository.findById(id);

        if (optionalEntity.isPresent()) {
            CustomerTestDriving existingEntity = optionalEntity.get();

            // Update only specific fields
            existingEntity.setFullName(form.getFullName());
            existingEntity.setPhoneNumber(form.getPhoneNumber());
            existingEntity.setDateTestDriving(form.getDateTestDriving());

            // Update car information
            Optional<Car> optionalCar = carRepository.findByName(form.getCarName());
            if (optionalCar.isPresent()) {
                Car car = optionalCar.get();
                existingEntity.setCar(car);

                // Save the updated entity
                return customerTestDrivingRepository.save(existingEntity);
            } else {
                // Handle the case where Car is not found
                return null;
            }
        } else {
            // Handle the case when CustomerTestDriving with given id is not found
            return null;
        }
    }


    @Override
    public void deleteCustomerTestDriving(int id) {
        customerTestDrivingRepository.deleteById(id);
    }
}
