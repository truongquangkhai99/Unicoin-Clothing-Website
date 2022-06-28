package com.unicoin.product.service;

import com.unicoin.product.common.RestResponsePage;
import com.unicoin.product.dto.VariantDTO;
import com.unicoin.product.form.AddImageForm;
import com.unicoin.product.form.AddOptionValueForm;
import com.unicoin.product.dto.ProductDTO;
import com.unicoin.product.form.AddProductForm;
import com.unicoin.product.form.UpdatePriceForm;

import java.util.List;

public interface ProductService{
    RestResponsePage<ProductDTO> addProduct(AddProductForm form);

    RestResponsePage<ProductDTO> viewProduct();

    RestResponsePage<VariantDTO> generateVariant(AddOptionValueForm form);

    void deleteProduct(Long productId);

    RestResponsePage<VariantDTO> viewVariantsByProduct(Long productId);

    void addImagesForProduct(Long productId, List<AddImageForm> imageUrls);

    RestResponsePage<VariantDTO> updatePrice(List<UpdatePriceForm> updatePriceForms);
}
