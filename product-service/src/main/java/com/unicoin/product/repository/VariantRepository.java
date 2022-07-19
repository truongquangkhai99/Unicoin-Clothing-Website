package com.unicoin.product.repository;

import com.unicoin.product.entity.Product;
import com.unicoin.product.entity.Variant;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VariantRepository extends JpaRepository<Variant, Long> {
    List<Variant> findAllByProduct(Product product);

    @Query("select v from Variant v where v.product = :product and v.qty > 0")
    List<Variant> findAllByProduct(@Param("product")Product product, Sort sort);

   boolean existsByProductAndStatus(Product product, Integer status);

   @Query("select v from Variant v where v.variantName like variantName")
   List<Variant> findAllByVariantNameLike(String variantName);

}
