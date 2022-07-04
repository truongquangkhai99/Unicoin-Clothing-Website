package com.unicoin.product.repository;

import com.unicoin.product.entity.Option;
import com.unicoin.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OptionRepository  extends JpaRepository<Option, Long> {

    Optional<Option> findOptionByOptionNameAndOptionCodeAndProduct(String optionName, String optionCode, Product product);
    Optional<Option> findOptionByOptionCodeAndProduct(String code, Product product);
    List<Option> findAllByProduct(Product product);
}
