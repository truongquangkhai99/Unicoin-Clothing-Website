package com.unicoin.product.service;

import com.unicoin.product.common.RestResponsePage;
import com.unicoin.product.dto.ProductShopDTO;

import java.util.List;

public interface ShopService {
    RestResponsePage<ProductShopDTO> getAllProduct();
}
