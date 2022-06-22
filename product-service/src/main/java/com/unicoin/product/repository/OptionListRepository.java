package com.unicoin.product.repository;

import com.unicoin.product.entity.OptionList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionListRepository extends JpaRepository<OptionList, Long> {
    boolean existsOptionListByOptionCode(String code);
    boolean existsOptionListByOptionName(String name);
}
