package com.example.mockspring.service.impliments;

import com.example.mockspring.entity.Account;
import com.example.mockspring.entity.Car;
import com.example.mockspring.entity.CustomerTestDriving;
import com.example.mockspring.repository.AccountRepository;
import com.example.mockspring.repository.CustomerTestDrivingRepository;
import com.example.mockspring.repository.ICarRepository;
import com.example.mockspring.service.CustomerTestDrivingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerTestDrivingServiceImpl implements CustomerTestDrivingService {

    @Autowired
    private CustomerTestDrivingRepository customerTestDrivingRepository;
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ICarRepository carRepository;
    @Override
    public CustomerTestDriving saveCustomerTestDriving(CustomerTestDriving customerTestDriving) {
        // Retrieve Account and Car entities
        Optional<Account> optionalAccount = accountRepository.findById(customerTestDriving.getAccount().getId());
        Optional<Car> optionalCar = carRepository.findById(customerTestDriving.getCar().getId());

        // Check if Account and Car entities exist
        if (optionalAccount.isPresent() && optionalCar.isPresent()) {
            Account account = optionalAccount.get();
            Car car = optionalCar.get();

            // Set the retrieved entities to the CustomerTestDriving object
            customerTestDriving.setAccount(account);
            customerTestDriving.setCar(car);

            // Now save the CustomerTestDriving object with associated entities
            return customerTestDrivingRepository.save(customerTestDriving);
        } else {
            // Handle the case where either Account or Car does not exist
            // You might want to throw an exception or handle it based on your business logic
            return null;
        }
    }

    @Override
    public CustomerTestDriving getCustomerTestDrivingById(int id) {
        Optional<CustomerTestDriving> optionalCustomerTestDriving = customerTestDrivingRepository.findById(id);
        return optionalCustomerTestDriving.orElse(null);
    }

    @Override
    public List<CustomerTestDriving> getAllCustomerTestDrivings() {
        return customerTestDrivingRepository.findAll();
    }
    @Override
    public CustomerTestDriving updateCustomerTestDriving(int id, CustomerTestDriving updatedCustomerTestDriving) {
        // Get the existing CustomerTestDriving object
        Optional<CustomerTestDriving> optionalExistingCustomerTestDriving = customerTestDrivingRepository.findById(id);

        if (optionalExistingCustomerTestDriving.isPresent()) {
            CustomerTestDriving existingCustomerTestDriving = optionalExistingCustomerTestDriving.get();

            // Retrieve Account and Car entities
            Optional<Account> optionalAccount = accountRepository.findById(updatedCustomerTestDriving.getAccount().getId());
            Optional<Car> optionalCar = carRepository.findById(updatedCustomerTestDriving.getCar().getId());

            // Check if Account and Car entities exist
            if (optionalAccount.isPresent() && optionalCar.isPresent()) {
                Account account = optionalAccount.get();
                Car car = optionalCar.get();

                // Set the retrieved entities to the updatedCustomerTestDriving object
                existingCustomerTestDriving.setAccount(account);
                existingCustomerTestDriving.setCar(car);

                // Set other properties if needed
                existingCustomerTestDriving.setFullName(updatedCustomerTestDriving.getFullName());
                existingCustomerTestDriving.setPhoneNumber(updatedCustomerTestDriving.getPhoneNumber());
                existingCustomerTestDriving.setDateTestDriving(updatedCustomerTestDriving.getDateTestDriving());
                existingCustomerTestDriving.setEmail(updatedCustomerTestDriving.getEmail());

                // Now save the updated CustomerTestDriving object with associated entities
                return customerTestDrivingRepository.save(existingCustomerTestDriving);
            } else {
                // Handle the case where either Account or Car does not exist
                // You might want to throw an exception or handle it based on your business logic
                return null;
            }
        } else {
            // Handle the case where the CustomerTestDriving with the provided id does not exist
            // You might want to throw an exception or handle it based on your business logic
            return null;
        }
    }


    @Override
    public void deleteCustomerTestDriving(int id) {
        customerTestDrivingRepository.deleteById(id);
    }
}
