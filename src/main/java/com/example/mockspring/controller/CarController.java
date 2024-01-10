package com.example.mockspring.controller;

import com.example.mockspring.dto.CarDTO;
import com.example.mockspring.entity.Car;
import com.example.mockspring.form.car.CarFilterForm;
import com.example.mockspring.form.car.CreatingCarForm;
import com.example.mockspring.form.car.UpdatingCarForm;
import com.example.mockspring.service.ICarService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/cars")
public class CarController {
    @Autowired
    private ICarService carService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public Page<CarDTO> getAllCars(Pageable pageable, CarFilterForm form){
        Page<Car> carPage = carService.getAllCars(pageable, form);
        List<Car> cars = carPage.getContent();
        List<CarDTO> carDTOS =
                cars.stream().map(car -> modelMapper.map(car, CarDTO.class))
                        .collect(Collectors.toList());
        return new PageImpl<>(carDTOS, pageable, carPage.getTotalElements());
    }

    @PostMapping ("/create")
    public ResponseEntity<CarDTO> createCar(@RequestBody CreatingCarForm form) {
//        carService.createCar(form);
//        return new ResponseEntity<>("Create successfully", HttpStatus.CREATED);
        Car car = carService.createCar(form);
        CarDTO carDTO = modelMapper.map(car, CarDTO.class);
        return new ResponseEntity<>(carDTO, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<CarDTO> updateCar(@PathVariable int id, @RequestBody UpdatingCarForm form) {
        form.setId(id);
//        carService.updateCar(form);
//        return new ResponseEntity<>("Update successfully", HttpStatus.CREATED);
        Car car = carService.updateCar(form);
        CarDTO carDTO = modelMapper.map(car, CarDTO.class);
        return new ResponseEntity<>(carDTO, HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCar(@PathVariable int id) {
        carService.deleteCar(id);
        return new ResponseEntity<>("Delete successfully", HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getCarById(@PathVariable int id) {
        try {
            Car car = carService.getCarById(id);
            CarDTO carDTO = modelMapper.map(car, CarDTO.class);
            return new ResponseEntity<>(carDTO, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}