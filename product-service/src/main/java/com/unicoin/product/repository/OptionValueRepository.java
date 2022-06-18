package com.unicoin.product.repository;

import com.unicoin.product.entity.Option;
import com.unicoin.product.entity.OptionValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OptionValueRepository extends JpaRepository<OptionValue, Long> {
    List<OptionValue> findAllByOption(Option option);
    Optional<OptionValue> findOptionValueByOptionAndOptionValue(Option option, String optionValue);

    boolean existsByOptionAndOptionValue(Option option, String optionValue);

    boolean existsByOption(Option option);
}
