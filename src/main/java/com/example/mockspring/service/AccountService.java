package com.example.mockspring.service;

import com.example.mockspring.entity.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface AccountService {

    Page<Account> getAll(Pageable pageable, String search);

    Account createAccount(Account account);

    Optional<Account> findByUsername(String username);

    Optional<Account> getAccountById(Integer id);

    Optional<Account> updateAccount(Integer id, Account updatedAccount);

    boolean deleteAccount(Integer id);
}