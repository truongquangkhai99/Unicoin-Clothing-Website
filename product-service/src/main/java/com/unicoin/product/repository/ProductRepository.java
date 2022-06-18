package com.unicoin.product.repository;

import com.unicoin.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsProductByProductCode(String productCode);
    List<Product> getAllByStatus(Integer status);
}
