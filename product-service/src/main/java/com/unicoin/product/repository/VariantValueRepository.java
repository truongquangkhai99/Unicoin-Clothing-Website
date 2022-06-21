package com.unicoin.product.repository;

import com.unicoin.product.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VariantValueRepository extends JpaRepository<VariantValue, VariantValueId> {
    List<VariantValue> findAllByVariant(Variant variant);

    Optional<VariantValue> findVariantValueByOptionAndOptionValueAndVariant(Option option, OptionValue optionValue, Variant variant);

    List<VariantValue> findAllByOptionValue(OptionValue optionValue);

    List<VariantValue> findAllByOption(Option option);

    boolean existsByOptionValue(OptionValue optionValue);

}