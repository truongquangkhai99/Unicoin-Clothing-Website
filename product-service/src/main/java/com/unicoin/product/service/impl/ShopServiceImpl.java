package com.unicoin.product.service.impl;

import com.unicoin.product.common.CommonsUtils;
import com.unicoin.product.common.RestResponsePage;
import com.unicoin.product.dto.*;
import com.unicoin.product.entity.Image;
import com.unicoin.product.entity.Product;
import com.unicoin.product.entity.Variant;
import com.unicoin.product.entity.VariantValue;
import com.unicoin.product.repository.ImageRepository;
import com.unicoin.product.repository.ProductRepository;
import com.unicoin.product.repository.VariantRepository;
import com.unicoin.product.repository.VariantValueRepository;
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

    @Autowired
    VariantValueRepository variantValueRepository;

    @Override
    public RestResponsePage<ProductDTO> getAllProduct() {
        List<Product> productList = productRepository.getAllByStatus(1);
        List<ProductDTO> productDTOS = new ArrayList<>();
        for (Product product :
                productList) {
            List<Variant> variantList = variantRepository.findAllByProduct(product, Sort.by(Sort.Direction.DESC, "price"));
            List<Image> imageSubs = imageRepository.findAllByProductAndImageType(product, CommonsUtils.TYPE_SUB);
            List<Image> imageMains = imageRepository.findAllByProductAndImageType(product, CommonsUtils.TYPE_MAIN);
            List<ImageDTO> imageDTOS = new ArrayList<>();
            if (imageSubs.size() > 0) {
                imageDTOS = imageSubs.stream().map(item ->
                                ImageDTO.builder()
                                        .imageId(item.getId())
                                        .imageUrl(item.getImageUrl())
                                        .imageType(item.getImageType())
                                        .build())
                        .collect(Collectors.toList());
            }
            ImageDTO imageMain = new ImageDTO();
            if (imageMains.size() > 0) {
                imageMain = ImageDTO.builder()
                        .imageId(imageMains.get(0).getId())
                        .imageUrl(imageMains.get(0).getImageUrl())
                        .imageType(imageMains.get(0).getImageType())
                        .build();
            }else {
                imageMain = null;
            }
            if (variantList.size() > 0) {
                List<VariantDTO> variantDTOs = variantList.stream().map(item ->
                        VariantDTO.builder()
                                .variantName(item.getVariantName())
                                .variantId(item.getId())
                                .productName(item.getProduct().getProductName())
                                .price(item.getPrice())
                                .qty(item.getQty())
                                .skuID(item.getSkuId())
                                .option(variantValueRepository.findAllByVariant(item).stream().map(
                                        value -> OptionVariantDTO.builder()
                                                .optionId(value.getOption().getId())
                                                .optionName(value.getOption().getOptionName())
                                                .optionCode(value.getOption().getOptionCode())
                                                .optionValueId(value.getOptionValue().getId())
                                                .optionValue(value.getOptionValue().getOptionValue())
                                                .build()
                                ).collect(Collectors.toList()))
                                .build()).collect(Collectors.toList());
                productDTOS.add(ProductDTO.builder()
                        .productId(product.getId())
                        .productName(product.getProductName())
                        .productCode(product.getProductCode())
                        .priceMax(variantList.get(0).getPrice())
                        .priceMin(variantList.get(variantList.size() - 1).getPrice())
                        .imageSubs(imageDTOS)
                        .imageMain(imageMain)
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
                        .status(product.getStatus())
                        .registStamp(product.getRegistStamp())
                        .updateUser(product.getUpdateUser())
                        .build());
            }
        }
        return new RestResponsePage<ProductDTO>(productDTOS, 1, productList.size(), productList.size(), 1);
    }
}
