package com.example.mockspring.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "CustomerTestDriving")
public class CustomerTestDriving {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="account_id",nullable = false)
    private Account account;

    @Column(name = "fullName",nullable = false)
    private String fullName;

    @Column(name = "phoneNumber",nullable = false)
    private String phoneNumber;

    @Column(name = "dateTestDriving", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private Date dateTestDriving;

    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

    @Column(name = "email", nullable = false)
    private String email;


}
