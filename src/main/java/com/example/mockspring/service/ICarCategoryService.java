package com.example.mockspring.service;

import com.example.mockspring.entity.CarCategory;
import com.example.mockspring.form.carCategory.CreatingCarCategoryForm;
import com.example.mockspring.form.carCategory.UpdatingCarCategoryForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICarCategoryService {
    Page<CarCategory> getAllCarCategories(Pageable pageable);
    CarCategory getCarCategoryById(int id);
    void createCarCategory(CreatingCarCategoryForm form);
    void updateCarCategory(UpdatingCarCategoryForm form);
    void deleteCarCategory(int id);
}