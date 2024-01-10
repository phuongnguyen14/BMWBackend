package com.example.mockspring.repository;


import com.example.mockspring.entity.CarCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICarCategoryRepository extends JpaRepository<CarCategory, Integer> {

    CarCategory findByName(String carCategoryName);

    void deleteById(int id);
}