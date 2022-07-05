package com.unicoin.order.repository;

import com.unicoin.order.entity.ExportOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExportOrderRepository extends JpaRepository<ExportOrder,Long> {

    List<ExportOrder> findAllByUsedId(Integer userId);
}
