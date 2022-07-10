package com.unicoin.order.repository;

import com.unicoin.order.entity.ImportOrderDetail;
import com.unicoin.order.entity.ImportOrders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImportOrderDetailRepository extends JpaRepository<ImportOrderDetail, Long> {
    List<ImportOrderDetail> findAllByImportOrdersId(ImportOrders importOrders);
}
