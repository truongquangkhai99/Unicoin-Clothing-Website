package com.unicoin.order.repository;

import com.unicoin.order.entity.ImportOrders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImportOrdersRepository extends JpaRepository<ImportOrders, Long> {
}
