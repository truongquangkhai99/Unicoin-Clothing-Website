package com.unicoin.product.service;

import com.unicoin.product.common.RestResponsePage;
import com.unicoin.product.dto.ProductDTO;

public interface ShopService {
    RestResponsePage<ProductDTO> getAllProduct();
}
