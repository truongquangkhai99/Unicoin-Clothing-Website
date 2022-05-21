package com.unicoin.customer.repository;

import com.unicoin.customer.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Userrepository extends JpaRepository<User, String> {
}
