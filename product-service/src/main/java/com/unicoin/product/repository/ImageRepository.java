package com.unicoin.product.repository;

import com.unicoin.product.entity.Image;
import com.unicoin.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> findAllByProduct(Product product);
}
