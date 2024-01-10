package com.example.mockspring.form.car;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class CarFilterForm {
    private String search;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date createAt;
}