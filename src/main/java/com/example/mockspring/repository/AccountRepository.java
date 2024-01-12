package com.example.mockspring.repository;


import com.example.mockspring.entity.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    Optional<Account> findByUsername(String username);

    Account save(Account account);

    Page<Account> findByUsernameContainingIgnoreCase(String username, Pageable pageable);
}
