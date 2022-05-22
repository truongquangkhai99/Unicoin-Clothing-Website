package com.unicoin.customer.repository;

import com.unicoin.customer.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("select u from User u where (u.phoneNumber is null or u.phoneNumber = :phonenumber)" +
            " and (u.fullName is null or u.fullName = :fullname)" +
            "and (u.email is null  or u.email = :email)")
    Page<User> searchAllCustomer(@Param("phonenumber") String phoneNumber,
                                 @Param("fullname") String fullName,
                                 @Param("email") String email,
                                 Pageable pageable);

    Optional<User> findByPhoneNumber(String phoneNumber);
}
