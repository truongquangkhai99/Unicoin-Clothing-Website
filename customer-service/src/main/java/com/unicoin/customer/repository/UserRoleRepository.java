package com.unicoin.customer.repository;

import com.unicoin.customer.entity.Role;
import com.unicoin.customer.entity.User;
import com.unicoin.customer.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {
    List<UserRole> findAllByUserId(Integer userId);

    Optional<UserRole> findByUserAndRole(User user, Role role);

    @Query(value = "select * from user_role where status= ?1", nativeQuery = true)
    List<UserRole> findAllByStatus(boolean status);

    List<UserRole> findAllByUser(User user);

    ArrayList<UserRole> findByUserAndStatus(User user, Boolean status);

    @Override
    Optional<UserRole> findById(Integer id);
}
