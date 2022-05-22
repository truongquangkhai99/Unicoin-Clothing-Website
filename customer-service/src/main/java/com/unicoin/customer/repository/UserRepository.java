package com.unicoin.customer.repository;

import com.unicoin.customer.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
     Optional<User> findByPhoneNumber(String phoneNumber);

    Optional<User> findByEmail(String email);
}
