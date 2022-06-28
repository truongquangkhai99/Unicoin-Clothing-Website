package com.unicoin.product.service.impl;

import com.unicoin.product.entity.Image;
import com.unicoin.product.entity.Product;
import com.unicoin.product.ex.AppException;
import com.unicoin.product.ex.ExceptionCode;
import com.unicoin.product.form.AddImageForm;
import com.unicoin.product.repository.ImageRepository;
import com.unicoin.product.repository.ProductRepository;
import com.unicoin.product.service.ImageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ImageServiceImpl implements ImageService {

    @Autowired
    ImageRepository imageRepo;

    @Autowired
    ProductRepository productRepo;

    @Override
    public List<Image> addImages(Long productId, List<AddImageForm> images) {
        Optional<Product> optionalProduct = productRepo.findById(productId);
        if (optionalProduct.isEmpty()) {
            throw new AppException(ExceptionCode.PRODUCT_IS_NOT_EXIST);
        }
        List<Image> imageList = new ArrayList<>();
        for (AddImageForm imageForm :
                images) {
            Image image = imageRepo.save(Image.builder()
                    .imageUrl(imageForm.getImageUrl())
                    .imageType(imageForm.getImageType())
                    .product(optionalProduct.get())
                    .status(true)
                    .registStamp(new Timestamp(new Date().getTime()))
                    .build());
            imageList.add(image);
        }
        return imageList;
    }

    @Override
    public void deleteImage(Long id) {
        Optional<Image> optional = imageRepo.findById(id);
        if (optional.isEmpty()) {
//            throw new AppException(ExceptionCode.IMAGE_IS_NOT_EXIST);
        }
        imageRepo.delete(optional.get());
    }
}
