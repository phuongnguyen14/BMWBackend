package com.example.mockspring.service.impliments;

import com.example.mockspring.entity.Car;
import com.example.mockspring.entity.CustomerTestDriving;
import com.example.mockspring.form.customerTestDriving.CreateTestDrivingForm;
import com.example.mockspring.form.customerTestDriving.TestDrivingFilterForm;
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

//    @Override
//    public Page<CustomerTestDriving> getAllCustomerTestDrivings(Pageable pageable) {
//        return customerTestDrivingRepository.findAll(pageable);
//    }
@Override
public Page<CustomerTestDriving> getAllCustomerTestDrivings(Pageable pageable, TestDrivingFilterForm filterForm) {
    if (filterForm != null && filterForm.getSearch() != null && !filterForm.getSearch().isEmpty()) {
        return customerTestDrivingRepository.findByFullNameContainingIgnoreCase(filterForm.getSearch(), pageable);
    } else {
        return customerTestDrivingRepository.findAll(pageable);
    }
}
    @Override
    public CustomerTestDriving createCustomerTestDriving(CreateTestDrivingForm form) {
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

            existingEntity.setFullName(form.getFullName());
            existingEntity.setPhoneNumber(form.getPhoneNumber());
            existingEntity.setDateTestDriving(form.getDateTestDriving());

            Optional<Car> optionalCar = carRepository.findByName(form.getCarName());
            if (optionalCar.isPresent()) {
                Car car = optionalCar.get();
                existingEntity.setCar(car);

                return customerTestDrivingRepository.save(existingEntity);
            } else {
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
