package com.unicoin.product.repository;

import com.unicoin.product.entity.ExportOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExportOrderRepository extends JpaRepository<ExportOrder,Long> {

    List<ExportOrder> findAllByUserPhoneNumber(String userPhoneNumber);
}
