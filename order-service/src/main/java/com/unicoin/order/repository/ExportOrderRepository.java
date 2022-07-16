package com.unicoin.order.repository;

import com.unicoin.order.entity.ExportOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExportOrderRepository extends JpaRepository<ExportOrder,Long> {

    @Query("select e from ExportOrder e where (:status is null or e.status = :status)")
    List<ExportOrder> searchExportOrderByStatus(@Param("status") Integer status);

    List<ExportOrder> findAllByUserPhoneNumber(String userPhoneNumber);

    List<ExportOrder> findExportOrderById(Long id);

}
