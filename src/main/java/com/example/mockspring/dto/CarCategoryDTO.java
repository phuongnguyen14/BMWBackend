package com.example.mockspring.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CarCategoryDTO {
    private int id;
    private String name;


//    private List<CarDTO> cars;
//    @Getter
//    @Setter
//    @NoArgsConstructor
//    public static class CarDTO{
//        private String name;
//    }
}