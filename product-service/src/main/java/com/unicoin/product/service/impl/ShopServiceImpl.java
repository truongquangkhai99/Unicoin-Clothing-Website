package com.unicoin.product.service.impl;

import com.unicoin.product.common.RestResponsePage;
import com.unicoin.product.dto.ImageDTO;
import com.unicoin.product.dto.ProductShopDTO;
import com.unicoin.product.dto.SupplierDTO;
import com.unicoin.product.dto.VariantDTO;
import com.unicoin.product.entity.Image;
import com.unicoin.product.entity.Product;
import com.unicoin.product.entity.Variant;
import com.unicoin.product.repository.ImageRepository;
import com.unicoin.product.repository.ProductRepository;
import com.unicoin.product.repository.VariantRepository;
import com.unicoin.product.service.ShopService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ShopServiceImpl implements ShopService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    VariantRepository variantRepository;

    @Autowired
    ImageRepository imageRepository;

    @Override
    public RestResponsePage<ProductShopDTO> getAllProduct() {
        List<Product> productList = productRepository.getAllByStatus(1);
        List<ProductShopDTO> productShopDTOList = new ArrayList<>();
        for (Product product :
                productList) {
            List<Variant> variantList = variantRepository.findAllByProduct(product, Sort.by(Sort.Direction.DESC, "price"));
            List<Image> images = imageRepository.findAllByProduct(product);
            List<ImageDTO> imageDTOS = images.stream().map(item ->
                    ImageDTO.builder()
                            .imageId(item.getId())
                            .imageUrl(item.getImageUrl())
                            .build())
                    .collect(Collectors.toList());
            List<VariantDTO> variantDTOs = variantList.stream().map(item ->
                    VariantDTO.builder()
                            .variantName(item.getVariantName())
                            .variantId(item.getId())
                            .productName(item.getProduct().getProductName())
                            .price(item.getPrice())
                            .qty(item.getQty())
                            .skuID(item.getSkuId())
                            .build()).collect(Collectors.toList());
            productShopDTOList.add(ProductShopDTO.builder()
                    .productId(product.getId())
                    .priceMax(variantList.get(0).getPrice())
                    .priceMin(variantList.get(variantList.size() - 1).getPrice())
                    .images(imageDTOS)
                    .supplier(SupplierDTO.builder()
                            .supplierCode(product.getSupplier().getSupplierCode())
                            .supplierName(product.getSupplier().getSupplierName())
                            .memo(product.getSupplier().getMemo())
                            .email(product.getSupplier().getEmail())
                            .address(product.getSupplier().getAddress())
                            .phoneNumber(product.getSupplier().getPhoneNumber())
                            .supplierId(product.getSupplier().getId())
                            .build())
                    .variantList(variantDTOs)
                    .build());
        }
        return new RestResponsePage<ProductShopDTO>(productShopDTOList, 1, productList.size(), productList.size(), 1);
    }
}
