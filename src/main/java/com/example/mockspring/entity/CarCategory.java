package com.example.mockspring.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class CarCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "`name`",length = 50, nullable = false, unique = true)
    private String name;

//    @OneToMany(mappedBy = "carCategory")
//    private List<Car> cars;
}