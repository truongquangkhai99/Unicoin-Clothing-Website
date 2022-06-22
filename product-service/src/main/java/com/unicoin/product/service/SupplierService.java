package com.unicoin.product.service;

import com.unicoin.product.common.RestResponsePage;
import com.unicoin.product.dto.SupplierDTO;
import com.unicoin.product.form.AddSupplierForm;

public interface SupplierService {
    RestResponsePage<SupplierDTO> getAllSupplier();

    void addSupplier(AddSupplierForm form);
}
