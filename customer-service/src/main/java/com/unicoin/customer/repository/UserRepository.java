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
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u where (:phoneNumber is null or u.phoneNumber like concat('%', :phoneNumber, '%') )" +
            " and (:fullName is null or u.fullName like concat('%',  :fullName, '%'))" +
            "and (:email is null  or u.email like concat('%',  :email, '%'))")
    Page<User> searchAllCustomer(@Param("phoneNumber") String phoneNumber,
                                 @Param("fullName") String fullName,
                                 @Param("email") String email,
                                 Pageable pageable);

     Optional<User> findByPhoneNumber(String phoneNumber);

    Optional<User> findByEmail(String email);
}
