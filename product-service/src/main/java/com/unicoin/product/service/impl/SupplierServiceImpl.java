package com.unicoin.product.service.impl;

import com.unicoin.product.common.RestResponsePage;
import com.unicoin.product.dto.SupplierDTO;
import com.unicoin.product.entity.Supplier;
import com.unicoin.product.ex.AppException;
import com.unicoin.product.ex.ExceptionCode;
import com.unicoin.product.form.AddSupplierForm;
import com.unicoin.product.repository.SupplierRepository;
import com.unicoin.product.service.SupplierService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    SupplierRepository supplierRepository;

    @Override
    public RestResponsePage<SupplierDTO> getAllSupplier() {
      log.info("Start getAllSupplier");
        List<Supplier> supplierList = supplierRepository.findAllByStatus(1);
        List<SupplierDTO> dtoList = supplierList.stream().map(item ->
                SupplierDTO.builder()
                        .supplierId(item.getId())
                        .supplierName(item.getSupplierName())
                        .supplierCode(item.getSupplierCode())
                        .phoneNumber(item.getPhoneNumber())
                        .address(item.getAddress())
                        .email(item.getEmail())
                        .memo(item.getMemo())
                        .build()
                ).collect(Collectors.toList());
      log.info("End getAllSupplier");
        return new RestResponsePage<>(dtoList);
    }

    @Override
    public void addSupplier(AddSupplierForm form) {
        log.info("Start addSupplier");
        if (supplierRepository.existsSupplierBySupplierCode(form.getSupplierCode()))
            throw new AppException(ExceptionCode.SUPPLIER_CODE_IS_EXIST);
        Supplier supplier = new Supplier();
        BeanUtils.copyProperties(form, supplier);
        supplier.setRegistStamp(new Timestamp(new Date().getTime()));
        supplier.setUpdateStamp(new Timestamp(new Date().getTime()));
        supplier.setStatus(1);
        log.info("save supplier {}", supplier);
        supplierRepository.save(supplier);
        log.info("End addSupplier");
    }
}
