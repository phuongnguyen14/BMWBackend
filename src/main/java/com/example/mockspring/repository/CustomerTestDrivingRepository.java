package com.example.mockspring.repository;

import com.example.mockspring.entity.CustomerTestDriving;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerTestDrivingRepository extends JpaRepository<CustomerTestDriving, Integer> {
}
