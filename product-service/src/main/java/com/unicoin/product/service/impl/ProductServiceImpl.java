package com.unicoin.product.service.impl;

import com.unicoin.product.common.CommonsUtils;
import com.unicoin.product.common.RestResponsePage;
import com.unicoin.product.dto.*;
import com.unicoin.product.entity.*;
import com.unicoin.product.ex.AppException;
import com.unicoin.product.ex.ExceptionCode;
import com.unicoin.product.form.*;
import com.unicoin.product.repository.*;
import com.unicoin.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

//    status = 1 -> active
//    status = 0 -> in-active
//    status = 2 -> wait-active

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    SupplierRepository supplierRepository;


    @Autowired
    VariantRepository variantRepository;

    @Autowired
    OptionRepository optionRepository;

    @Autowired
    OptionValueRepository optionValueRepository;

    @Autowired
    OptionListRepository optionListRepository;

    @Autowired
    VariantValueRepository variantValueRepository;

    @Autowired
    ImageRepository imageRepository;

    @Autowired
    PasswordEncoder encoder;

    @Override
    public RestResponsePage<ProductDTO> addProduct(AddProductForm form) {
        log.info("Start addProduct:  {}", form);

        //Kiểm tra supplier có tồn tại không
        Optional<Supplier> supplierOptional = supplierRepository.findById(form.getSupplier());
        if (supplierOptional.isEmpty())
            throw new AppException(ExceptionCode.SUPPLIER_NOT_EXIST);

        //khởi tạo product code
        form.setProductCode(form.getProductCode() + supplierOptional.get().getSupplierCode());

        //kiểm tra product code đã được sử dụng chưa
        if (productRepository.existsProductByProductCode(form.getProductCode()))
            throw new AppException(ExceptionCode.PRODUCT_IS_EXIST);


        Product product = new Product();
        BeanUtils.copyProperties(form, product);
        product.setSupplier(supplierOptional.get());
        product.setStatus(1);
        product.setRegistStamp(new Timestamp(new Date().getTime()));
        //config security must be fix updateUser = user loging
        String userPhoneNumber = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        product.setUpdateUser(userPhoneNumber);
        log.info("Product entity: {}", product);
        Product entity = productRepository.save(product);
        ProductDTO dto = new ProductDTO();
        BeanUtils.copyProperties(entity, dto);
        dto.setSupplier(SupplierDTO.builder()
                .supplierId(entity.getSupplier().getId())
                .supplierName(entity.getSupplier().getSupplierName())
                .supplierCode(entity.getSupplier().getSupplierCode())
                .address(entity.getSupplier().getAddress())
                .email(entity.getSupplier().getEmail())
                .memo(entity.getSupplier().getMemo())
                .phoneNumber(entity.getSupplier().getPhoneNumber())
                .build());
        List<ProductDTO> dtoList = new ArrayList<>();
        dtoList.add(dto);
        log.info("End addProduct");
        return new RestResponsePage<>(dtoList);
    }


    @Override
    public RestResponsePage<ProductDTO> viewProduct() {
        log.info("Start viewProduct");
        List<Product> productList = productRepository.getAllByStatus(1);

        List<ProductDTO> dtoList = new ArrayList<>();
        for (Product item : productList) {
            List<Image> imageMains = imageRepository.findAllByProductAndImageType(item, CommonsUtils.TYPE_MAIN);
            List<Image> imageSubs = imageRepository.findAllByProductAndImageType(item, CommonsUtils.TYPE_SUB);
            ImageDTO imageMainDTO = new ImageDTO();
            if (imageMains.size() > 0) {
                imageMainDTO = ImageDTO.builder()
                        .imageId(imageMains.get(0).getId())
                        .imageUrl(imageMains.get(0).getImageUrl())
                        .build();
            } else {
                imageMainDTO = null;
            }
            List<ImageDTO> imageSubDTOs = new ArrayList<>();
            if (imageSubs.size() > 0) {
                imageSubDTOs = imageSubs.stream().map(
                        image -> ImageDTO.builder()
                                .imageId(image.getId())
                                .imageUrl(image.getImageUrl())
                                .build()
                ).collect(Collectors.toList());
            }
            ProductDTO productDTO = ProductDTO.builder()
                    .id(item.getId())
                    .productCode(item.getProductCode())
                    .productName(item.getProductName())
                    .registStamp(item.getRegistStamp())
                    .status(item.getStatus())
                    .supplier(SupplierDTO.builder()
                            .supplierId(item.getSupplier().getId())
                            .supplierName(item.getSupplier().getSupplierName())
                            .supplierCode(item.getSupplier().getSupplierCode())
                            .address(item.getSupplier().getAddress())
                            .email(item.getSupplier().getEmail())
                            .memo(item.getSupplier().getMemo())
                            .phoneNumber(item.getSupplier().getPhoneNumber())
                            .build())
                    .updateUser(item.getUpdateUser())
                    .imageMain(imageMainDTO)
                    .imageSubs(imageSubDTOs)
                    .build();
            dtoList.add(productDTO);
        }
        log.info("End viewProduct");
        return new RestResponsePage<>(dtoList, 1, productList.size(), productList.size(), 1);
    }

    @Override
    public RestResponsePage<VariantDTO> generateVariant(AddOptionValueForm form) {
        //check product
        Optional<Product> optionalProduct = productRepository.findById(form.getProductId());
        if (optionalProduct.isEmpty())
            throw new AppException(ExceptionCode.PRODUCT_IS_NOT_EXIST);

        Optional<Option> optionalOption = optionRepository.findOptionByOptionNameAndOptionCodeAndProduct(form.getOption().getOptionName(), form.getOption().getOptionCode(), optionalProduct.get());
        Option option = new Option();
        if (optionalOption.isEmpty()) {
            //save option of product to db
            option = optionRepository.save(Option.builder()
                    .optionName(form.getOption().getOptionName())
                    .optionCode(form.getOption().getOptionCode())
                    .product(optionalProduct.get())
                    .status(1)
                    .build());
        } else {
            option = optionalOption.get();
        }

        //save option value
        List<OptionValue> optionValueList = new ArrayList<>();
        OptionValue optionValue1 = new OptionValue();
        for (ValueForm itemValue :
                form.getOptionValue()) {
            if (optionValueRepository.existsByOption(option)) {
                if (!optionValueRepository.existsByOptionAndOptionValue(option, itemValue.getValue())) {
                    optionValue1 = optionValueRepository.save(OptionValue.builder()
                            .optionValue(itemValue.getValue())
                            .option(option)
                            .status(2)
                            .build());
                    optionValueList.add(optionValue1);
                }
            } else {
                optionValue1 = optionValueRepository.save(OptionValue.builder()
                        .optionValue(itemValue.getValue())
                        .option(option)
                        .status(1)
                        .build());
                optionValueList.add(optionValue1);
            }
        }
        this.generateVariantOfProductByOptionValueListAndOption(optionValueList, optionalProduct.get(), option);
        return this.getVariantByProductId(optionalProduct.get());
    }

    @Override
    public void deleteProduct(Long productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isEmpty())
            throw new AppException(ExceptionCode.PRODUCT_IS_NOT_EXIST);
        List<Variant> variantList = variantRepository.findAllByProduct(optionalProduct.get());
        for (Variant variant : variantList) {
            List<VariantValue> variantValueList = variantValueRepository.findAllByVariant(variant);
            for (VariantValue variantValue : variantValueList) {
                variantValueRepository.delete(variantValue);
            }
            variantRepository.delete(variant);
        }
        List<Option> optionList = optionRepository.findAllByProduct(optionalProduct.get());
        for (Option option : optionList) {
            List<OptionValue> optionValueList = optionValueRepository.findAllByOption(option);
            for (OptionValue optionValue : optionValueList) {
                optionValueRepository.delete(optionValue);
            }
            optionRepository.delete(option);
        }
        List<Image> images = imageRepository.findAllByProduct(optionalProduct.get());
        for (Image image: images){
            imageRepository.delete(image);
        }
        productRepository.delete(optionalProduct.get());
    }

    @Override
    public RestResponsePage<VariantDTO> viewVariantsByProduct(Long productId) {
        //check product
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isEmpty())
            throw new AppException(ExceptionCode.PRODUCT_IS_NOT_EXIST);
        return getVariantByProductId(optionalProduct.get());
    }

    @Override
    public void addImagesForProduct(Long productId, List<AddImageForm> imageUrls) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isEmpty())
            throw new AppException(ExceptionCode.PRODUCT_IS_NOT_EXIST);

        for (AddImageForm image : imageUrls) {
            if (CommonsUtils.TYPE_MAIN.equals(image.getImageType())) {
                List<Image> imagesMains = imageRepository.findAllByProductAndImageType(optionalProduct.get(), CommonsUtils.TYPE_MAIN);
                if (imagesMains.size() > 0) {
                    Image imageMain = imagesMains.get(0);
                    imageMain.setImageUrl(image.getImageUrl());
                    imageRepository.save(imageMain);
                } else {
                    imageRepository.save(Image.builder()
                            .product(optionalProduct.get())
                            .imageUrl(image.getImageUrl())
                            .imageType(image.getImageType())
                            .registStamp(new Timestamp(new Date().getTime()))
                            .status(true)
                            .build());
                }
            } else {
                if (!imageRepository.existsByImageUrlAndImageType(image.getImageUrl(), CommonsUtils.TYPE_SUB)) {
                    imageRepository.save(Image.builder()
                            .imageUrl(image.getImageUrl())
                            .imageType(image.getImageType())
                            .status(true)
                            .product(optionalProduct.get())
                            .registStamp(new Timestamp(new Date().getTime()))
                            .build());
                }
            }
        }
    }

    @Override
    public RestResponsePage<VariantDTO> updatePrice(List<UpdatePriceForm> updatePriceForms) {
        List<VariantDTO> variantDTOS = new ArrayList<>();
        for (UpdatePriceForm form :
                updatePriceForms) {
            Optional<Variant> variantOptional = variantRepository.findById(form.getVariantId());
            if (variantOptional.isEmpty()) {
                throw new AppException(ExceptionCode.VARIANT_IS_NOT_EXIST);
            }
            Variant variant = variantOptional.get();
            variant.setPrice(form.getPrice());
            variant.setQty(form.getQty());
            List<VariantValue> variantValues = variantValueRepository.findAllByVariant(variant);
            List<OptionVariantDTO> optionVariantDTOS = new ArrayList<>();
            for (VariantValue value :
                    variantValues) {
                optionVariantDTOS.add(OptionVariantDTO.builder()
                        .optionId(value.getOption().getId())
                        .optionName(value.getOption().getOptionName())
                        .optionCode(value.getOption().getOptionCode())
                        .optionValueId(value.getOptionValue().getId())
                        .optionValue(value.getOptionValue().getOptionValue())
                        .build());
            }
            variantRepository.save(variant);
            variantDTOS.add(VariantDTO.builder()
                    .variantId(variant.getId())
                    .productId(variant.getProduct().getId())
                    .qty(variant.getQty())
                    .price(variant.getPrice())
                    .productName(variant.getProduct().getProductName())
                    .option(optionVariantDTOS)
                    .skuID(variant.getSkuId())
                    .variantName(variant.getVariantName())
                    .status(variant.getStatus())
                    .build());
        }
        return new RestResponsePage<>(variantDTOS, 1, variantDTOS.size(), variantDTOS.size(), 1);
    }


    public RestResponsePage<VariantDTO> getVariantByProductId(Product product) {
        //find all variant
        List<Variant> variants = variantRepository.findAllByProduct(product);
        List<VariantDTO> variantDTOS = new ArrayList<>();
        for (Variant variant :
                variants) {
            List<VariantValue> variantValues = variantValueRepository.findAllByVariant(variant);
            List<OptionVariantDTO> optionVariantDTOS = new ArrayList<>();
            for (VariantValue value :
                    variantValues) {
                optionVariantDTOS.add(OptionVariantDTO.builder()
                        .optionId(value.getOption().getId())
                        .optionName(value.getOption().getOptionName())
                        .optionCode(value.getOption().getOptionCode())
                        .optionValueId(value.getOptionValue().getId())
                        .optionValue(value.getOptionValue().getOptionValue())
                        .build());
            }
            variantDTOS.add(VariantDTO.builder()
                    .variantId(variant.getId())
                    .productId(variant.getProduct().getId())
                    .qty(variant.getQty())
                    .price(variant.getPrice())
                    .priceDiscount(variant.getPriceDiscount())
                    .productName(variant.getProduct().getProductName())
                    .option(optionVariantDTOS)
                    .skuID(variant.getSkuId())
                    .variantName(variant.getVariantName())
                    .status(variant.getStatus())
                    .build());
        }
        RestResponsePage<VariantDTO> restResponsePage = new RestResponsePage<>(variantDTOS, 1, variantDTOS.size(), variantDTOS.size(), 1);
        return restResponsePage;
    }

    public void generateVariantOfProductByOptionValueListAndOption(List<OptionValue> optionValueList, Product product, Option option) {
        //create variant
        List<Variant> variantList = variantRepository.findAllByProduct(product);

        //truong hop product chua co variant nao thi se tao truc tiep
        if (variantList.size() == 0) {
            for (OptionValue item :
                    optionValueList) {
                Variant variant = variantRepository.save(Variant.builder()
                        .product(product)
                        .price(0L)
                        .priceDiscount(0L)
                        .qty(0)
                        .skuId(product.getProductCode() + "-" + option.getOptionCode() + item.getId())
                        .variantName(product.getProductName() + "-" + item.getOptionValue())
                        .status(CommonsUtils.STATUS_NEW_PRODUCT)
                        .build());
                VariantValue variantValue = variantValueRepository.save(VariantValue.builder()
                        .id(VariantValueId.builder()
                                .variantId(variant.getId())
                                .optionId(option.getId())
                                .build())
                        .optionValue(item)
                        .variant(variant)
                        .option(option)
                        .build());
            }
        } else {
            //neu product da co variant co nghia la dang them thuoc tinh cho san pham
            // can phai update thuoc tinh cho cac variant cu va generate cac variant moi
            for (int i = 0; i < optionValueList.size(); i++) {
                OptionValue item = optionValueList.get(i);

                //tim kiem cac variant chua option i
                List<VariantValue> variantValues = variantValueRepository.findAllByOption(item.getOption());

                //neu co variantValue co chua option i co nghia la them moi cac value cua option
                //nguoc lai bo sung them value cua option
                if (variantValues.size() > 0 && item.getStatus() == 2) {
                    //tim kiem tat ca cac variantValue co chua 1 optionValue
                    //=> value moi cung can generate cac variant tuong ung va cac variantValue tuong ung
                    List<VariantValue> variantValueItems = variantValueRepository.findAllByOptionValue(variantValues.get(0).getOptionValue());
                    for (VariantValue value : variantValueItems) {
                        String[] optionCodes = value.getVariant().getSkuId().split("-");
                        String skuId = product.getProductCode();
                        String[] variantNames = value.getVariant().getVariantName().split("-");
                        String variantName = product.getProductName();
                        for (int j = 1; j < optionCodes.length; j++) {
                            String optionCodeFeature = "";
                            String variantNameFeature = "";
                            String optionCode = optionCodes[j].substring(0, 2);
                            if (optionCode.equalsIgnoreCase(item.getOption().getOptionCode())) {
                                optionCodeFeature = optionCode + item.getId();
                                variantNameFeature = item.getOptionValue();
                            } else {
                                optionCodeFeature = optionCodes[j];
                                variantNameFeature = variantNames[j];
                            }
                            skuId = skuId + "-" + optionCodeFeature;
                            variantName = variantName + "-" + variantNameFeature;
                        }
                        //luu variant
                        Variant variant = variantRepository.save(Variant.builder()
                                .product(product)
                                .qty(0)
                                .price(0L)
                                .priceDiscount(0L)
                                .skuId(skuId)
                                .variantName(variantName)
                                .status(CommonsUtils.STATUS_NEW_PRODUCT)
                                .build());
                        String[] variantSkuIdArr = variant.getSkuId().split("-");
                        //add them cac option cua variant da duoc them tu truoc
                        for (int j = 1; j < variantSkuIdArr.length; j++) {
                            String optionCode = variantSkuIdArr[j].substring(0, 2);
                            String optionValueId = variantSkuIdArr[j].substring(2);
                            Optional<Option> optional = optionRepository.findOptionByOptionCodeAndProduct(optionCode, product);
                            if (optional.isEmpty())
                                throw new AppException(ExceptionCode.OPTION_IS_NOT_EXIST);
                            Optional<OptionValue> valueOptional = optionValueRepository.findById(Long.valueOf(optionValueId));
                            if (valueOptional.isEmpty())
                                throw new AppException(ExceptionCode.OPTIONVALUE_IS_NOT_EXIST);
                            VariantValue variantValue = variantValueRepository.save(VariantValue.builder()
                                    .id(VariantValueId.builder()
                                            .variantId(variant.getId())
                                            .optionId(optional.get().getId())
                                            .build())
                                    .optionValue(valueOptional.get())
                                    .variant(variant)
                                    .option(optional.get())
                                    .build());
                            OptionValue optionValue = valueOptional.get();
                            optionValue.setStatus(1);
                            optionValueRepository.save(optionValue);
                        }
                    }
                } else {
                    //them option cho cac variant cua product da duoc tao truoc do
                    for (Variant variant :
                            variantList) {
                        String skuId = variant.getSkuId();
                        String variantName = variant.getVariantName();

                        //kiem tra xem variantValue da duoc them vao truoc do chua
                        Optional<VariantValue> optionalVariantValue = variantValueRepository.findVariantValueByOptionAndOptionValueAndVariant(option, item, variant);

                        //neu chua thi them <=> co roi thi bo qua
                        if (optionalVariantValue.isEmpty()) {

                            if (i == 0) {
                                //add option cho variant da duoc them truoc do
                                Variant variantEntity = variantRepository.save(Variant.builder()
                                        .id(variant.getId())
                                        .product(product)
                                        .price(0L)
                                        .priceDiscount(0L)
                                        .qty(0)
                                        .skuId(skuId + "-" + option.getOptionCode() + item.getId())
                                        .variantName(variantName + "-" + item.getOptionValue())
                                        .status(CommonsUtils.STATUS_NEW_PRODUCT)
                                        .build());
                                VariantValue variantValue = variantValueRepository.save(VariantValue.builder()
                                        .id(VariantValueId.builder()
                                                .variantId(variantEntity.getId())
                                                .optionId(option.getId())
                                                .build())
                                        .optionValue(item)
                                        .variant(variantEntity)
                                        .option(option)
                                        .build());
                            } else {
                                //tao moi cac variant de generate cac truong hop con lai
                                Variant variantEntity = variantRepository.save(Variant.builder()
                                        .product(product)
                                        .price(0L)
                                        .priceDiscount(0L)
                                        .qty(0)
                                        .skuId(skuId + "-" + option.getOptionCode() + item.getId())
                                        .variantName(variantName + "-" + item.getOptionValue())
                                        .status(CommonsUtils.STATUS_NEW_PRODUCT)
                                        .build());

                                VariantValue variantValue = variantValueRepository.save(VariantValue.builder()
                                        .id(VariantValueId.builder()
                                                .variantId(variantEntity.getId())
                                                .optionId(option.getId())
                                                .build())
                                        .optionValue(item)
                                        .variant(variantEntity)
                                        .option(option)
                                        .build());

                                //add cac option khac cua variant da duoc add truoc do
                                String[] optionCodes = variantEntity.getSkuId().split("-");
                                for (int j = 1; j < (optionCodes.length - 1); j++) {
                                    String optionCode = optionCodes[j].substring(0, 2);
                                    String optionValueId = optionCodes[j].substring(2);
                                    Optional<Option> optional = optionRepository.findOptionByOptionCodeAndProduct(optionCode, product);
                                    if (optional.isEmpty())
                                        throw new AppException(ExceptionCode.OPTION_IS_NOT_EXIST);
                                    Optional<OptionValue> valueOptional = optionValueRepository.findById(Long.valueOf(optionValueId));
                                    if (valueOptional.isEmpty())
                                        throw new AppException(ExceptionCode.OPTIONVALUE_IS_NOT_EXIST);
                                    variantValueRepository.save(VariantValue.builder()
                                            .id(VariantValueId.builder()
                                                    .variantId(variantEntity.getId())
                                                    .optionId(optional.get().getId())
                                                    .build())
                                            .optionValue(valueOptional.get())
                                            .variant(variantEntity)
                                            .option(optional.get())
                                            .build());
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}
