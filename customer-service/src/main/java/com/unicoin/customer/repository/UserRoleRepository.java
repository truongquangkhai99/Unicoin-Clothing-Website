package com.unicoin.customer.repository;

import com.unicoin.customer.entity.User;
import com.unicoin.customer.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {
    List<UserRole> findAllByUserId(User userId);
}
