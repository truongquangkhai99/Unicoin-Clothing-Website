package com.unicoin.order.repository;

import com.unicoin.order.entity.ImportOrders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository

public interface ImportOrdersRepository extends JpaRepository<ImportOrders, Long> {
    @Query("select i from ImportOrders i where (:status is null or i.status = :status)")
    List<ImportOrders> searchImportOrdersByStatus(@Param("status") Integer status);
}
