package com.example.mockspring.specification;

import com.example.mockspring.entity.Account;
import org.springframework.data.jpa.domain.Specification;

public class AccountSpecification {

    public static Specification<Account> usernameEquals(String username) {
        return (root, query, builder) -> builder.equal(root.get("username"), username);
    }
}