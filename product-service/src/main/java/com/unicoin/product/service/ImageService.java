package com.unicoin.product.service;

import com.unicoin.product.entity.Image;
import com.unicoin.product.form.AddImageForm;

import java.util.List;

public interface ImageService {
    List<Image> addImages(Long productId, List<AddImageForm> images);

    void deleteImage(Long id);
}
