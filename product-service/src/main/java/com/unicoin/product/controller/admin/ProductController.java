package com.unicoin.product.controller.admin;


import com.unicoin.product.form.*;
import com.unicoin.product.resstresponse.ApiResponse;
import com.unicoin.product.resstresponse.SuccessResponse;
import com.unicoin.product.service.ExportOrderService;
import com.unicoin.product.service.OptionService;
import com.unicoin.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/product")
@CrossOrigin("*")
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    OptionService optionService;

    @Autowired
    ExportOrderService exportOrderService;
    @PostMapping("/add-product")
    public ApiResponse addProduct(@RequestBody AddProductForm form){
        return new SuccessResponse(productService.addProduct(form));
    }

    @GetMapping("/view-products")
    public ApiResponse viewProduct(){
        return new SuccessResponse(productService.viewProduct());
    }

    @PostMapping("/add-option")
    public ApiResponse addOption(@RequestBody AddOptionListForm form){
        return new SuccessResponse(optionService.addOptionList(form));
    }

    @GetMapping("/view-options")
    public ApiResponse viewOptions(){
        return new SuccessResponse(optionService.viewOptionList());
    }

    @PostMapping("/generate-variant")
    public ApiResponse generateVariant(@RequestBody AddOptionValueForm form){
        return new SuccessResponse(productService.generateVariant(form));
    }

    @PostMapping("/variant/update-price")
    public ApiResponse updatePriceForVariant(@RequestBody List<UpdatePriceForm> updatePriceForms){
        return new SuccessResponse(productService.updatePrice(updatePriceForms));
    }

    @DeleteMapping("/option-value/{optionValueId}")
    public ApiResponse deleteOptionValue(@PathVariable Long optionValueId){
        optionService.deleteOptionValue(optionValueId);
        return new SuccessResponse(exportOrderService.addExportOrder());
    }

    @DeleteMapping("/{productId}")
    public ApiResponse deleteProduct(@PathVariable Long productId){
        productService.deleteProduct(productId);
        return new SuccessResponse(exportOrderService.addExportOrder());
    }

    @GetMapping("/view-variants/{productId}")
    public ApiResponse viewVariantsByProduct(@PathVariable Long productId){
        return new SuccessResponse(productService.viewVariantsByProduct(productId));
    }

    @PostMapping("/add-image/{productId}")
    public ApiResponse addImagesForProduct(@PathVariable Long productId,
                                           @RequestBody List<AddImageForm> imageUrls){
        productService.addImagesForProduct(productId, imageUrls);
        return new SuccessResponse(exportOrderService.addExportOrder());
    }

}
