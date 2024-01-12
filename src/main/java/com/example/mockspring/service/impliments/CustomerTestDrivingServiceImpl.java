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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
//    public Page<CustomerTestDriving> getAllCustomerTestDrivings(Pageable pageable, TestDrivingFilterForm filterForm) {
//        if (filterForm != null) {
//            if (filterForm.getSearch() != null && !filterForm.getSearch().isEmpty()) {
//                return customerTestDrivingRepository.findByFullNameContainingIgnoreCaseOrPhoneNumberContaining(filterForm.getSearch(), filterForm.getSearch(), pageable);
//            }
//        }
//        return customerTestDrivingRepository.findAll(pageable);
//    }
    @Override
    public Page<Map<String, Object>> getAllCustomerTestDrivings(Pageable pageable, TestDrivingFilterForm filterForm) {
        Page<CustomerTestDriving> result;
        if (filterForm != null) {
            if (filterForm.getSearch() != null && !filterForm.getSearch().isEmpty()) {
                result = customerTestDrivingRepository.findByFullNameContainingIgnoreCaseOrPhoneNumberContaining(filterForm.getSearch(), filterForm.getSearch(), pageable);
            } else {
                result = customerTestDrivingRepository.findAll(pageable);
            }
        } else {
            result = customerTestDrivingRepository.findAll(pageable);
        }

        return result.map(this::mapToResponse);
    }


    @Override
    public CustomerTestDriving getCustomerTestDrivingById(int id) {
        Optional<CustomerTestDriving> optionalCustomerTestDriving = customerTestDrivingRepository.findById(id);
        return optionalCustomerTestDriving.orElse(null);
    }

    @Override
    public Map<String, Object> createCustomerTestDriving(CreateTestDrivingForm form) {
        Optional<Car> optionalCar = carRepository.findByName(form.getCarName());
        if (optionalCar.isPresent()) {
            Car car = optionalCar.get();

            CustomerTestDriving customerTestDriving = new CustomerTestDriving();
            customerTestDriving.setFullName(form.getFullName());
            customerTestDriving.setPhoneNumber(form.getPhoneNumber());
            customerTestDriving.setDateTestDriving(form.getDateTestDriving());
            customerTestDriving.setCar(car);

            CustomerTestDriving createdEntity = customerTestDrivingRepository.save(customerTestDriving);
            return mapToResponse(createdEntity); // Return the mapped response
        } else {
            return null;
        }
    }

    @Override
    public Map<String, Object> updateCustomerTestDriving(int id, UpdateTestDrivingForm form) {
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

                CustomerTestDriving updatedEntity = customerTestDrivingRepository.save(existingEntity);
                return mapToResponse(updatedEntity); // Return the mapped response
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    private Map<String, Object> mapToResponse(CustomerTestDriving customerTestDriving) {
        Map<String, Object> response = new HashMap<>();
        response.put("id", customerTestDriving.getId());
        response.put("fullName", customerTestDriving.getFullName());
        response.put("phoneNumber", customerTestDriving.getPhoneNumber());
        response.put("dateTestDriving", customerTestDriving.getDateTestDriving());
        response.put("car", mapCarToResponse(customerTestDriving.getCar()));
        return response;
    }

    private Map<String, Object> mapCarToResponse(Car car) {
        Map<String, Object> carResponse = new HashMap<>();
        carResponse.put("name", car.getName());
        return carResponse;
    }


    @Override
    public void deleteCustomerTestDriving(int id) {
        customerTestDrivingRepository.deleteById(id);
    }
}
