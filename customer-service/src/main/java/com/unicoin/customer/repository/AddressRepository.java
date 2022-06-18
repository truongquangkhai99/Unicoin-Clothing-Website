package com.unicoin.customer.repository;

import com.unicoin.customer.entity.Address;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AddressRepository extends JpaRepository<Address, Long> {

    @Query(value = "SELECT q  from Address q where  q.status = true")
    Page<Address> findAllByUser(@Param("userId") Long userId , Pageable pageable);
}
