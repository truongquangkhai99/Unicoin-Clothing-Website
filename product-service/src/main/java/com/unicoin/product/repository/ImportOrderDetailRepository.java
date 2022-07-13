package com.unicoin.product.repository;

import com.unicoin.product.entity.ImportOrderDetail;
import com.unicoin.product.entity.ImportOrders;
import com.unicoin.product.entity.Variant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImportOrderDetailRepository extends JpaRepository<ImportOrderDetail , Long> {
    List<ImportOrderDetail> findAllByVariantIdAndAndImportOrdersId(Variant variantId , ImportOrders importOrdersId);
    List<ImportOrderDetail>findAllByImportOrdersId(ImportOrders importOrdersId);
}
