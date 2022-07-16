package com.unicoin.order.repository;

import com.unicoin.order.entity.ExportOrder;
import com.unicoin.order.entity.ExportOrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExportOrderDetaiRepository extends JpaRepository<ExportOrderDetail,Long> {
    List<ExportOrderDetail> findAllByExportOrderId(ExportOrder exportOrder);
}
