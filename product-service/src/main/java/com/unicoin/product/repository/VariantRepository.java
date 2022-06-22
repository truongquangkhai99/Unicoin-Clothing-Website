package com.unicoin.product.repository;

import com.unicoin.product.entity.Product;
import com.unicoin.product.entity.Variant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VariantRepository extends JpaRepository<Variant, Long> {
    List<Variant> findAllByProduct(Product product);

   boolean existsByProductAndStatus(Product product, Integer status);
}
