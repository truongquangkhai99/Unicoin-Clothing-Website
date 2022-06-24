package com.unicoin.product.service;

import com.unicoin.product.entity.Image;

import java.util.List;

public interface ImageService {
    List<Image> addImages(Long productId, List<String> images);

    void deleteImage(Long id);
}
