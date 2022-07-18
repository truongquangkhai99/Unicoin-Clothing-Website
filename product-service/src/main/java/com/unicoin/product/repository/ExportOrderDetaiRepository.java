package com.unicoin.product.repository;

import com.unicoin.product.entity.ExportOrder;
import com.unicoin.product.entity.ExportOrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface ExportOrderDetaiRepository extends JpaRepository<ExportOrderDetail,Long> {
    List<ExportOrderDetail> findAllByExportOrderId(ExportOrder ExportOrderId);

    List<ExportOrderDetail> findAllByVariantId(Long variantId);
}
