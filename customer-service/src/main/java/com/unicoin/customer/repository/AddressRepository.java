package com.unicoin.customer.repository;

import com.unicoin.customer.entity.Address;
import com.unicoin.customer.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {

    List<Address> findAllByUserId(User user);
}
