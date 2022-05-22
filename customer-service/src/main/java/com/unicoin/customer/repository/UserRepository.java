package com.unicoin.customer.repository;

import com.unicoin.customer.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("select u from User u where (u.phoneNumer is null or u.phoneNumer = :phonenumber)" +
            " and (u.fullName is null or u.fullName = :fullname)" +
            "and (u.email is null  or u.email = :email)")
    Page<User> searchAllCustomer(@Param("phonenumber") String phoneNumber, String fullName, String email, Pageable pageable);

    Optional<User> findByPhoneNumer(String phoneNumber);
}
