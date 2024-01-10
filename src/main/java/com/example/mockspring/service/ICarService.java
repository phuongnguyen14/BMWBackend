package com.example.mockspring.service;

import com.example.mockspring.entity.Car;
import com.example.mockspring.form.car.CarFilterForm;
import com.example.mockspring.form.car.CreatingCarForm;
import com.example.mockspring.form.car.UpdatingCarForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICarService {
    Page<Car> getAllCars(Pageable pageable, CarFilterForm form);
    Car getCarById(int id);
    Car createCar(CreatingCarForm form);
    Car updateCar(UpdatingCarForm form);
    void deleteCar(int id);
}