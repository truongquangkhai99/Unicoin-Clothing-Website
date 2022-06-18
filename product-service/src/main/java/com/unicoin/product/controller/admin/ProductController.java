package com.unicoin.product.controller.admin;


import com.unicoin.product.form.AddOptionValueForm;
import com.unicoin.product.form.AddOptionListForm;
import com.unicoin.product.form.AddProductForm;
import com.unicoin.product.resstresponse.ApiResponse;
import com.unicoin.product.resstresponse.SuccessResponse;
import com.unicoin.product.service.OptionService;
import com.unicoin.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    OptionService optionService;

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

    @DeleteMapping("/option-value/{optionValueId}")
    public ApiResponse deleteOptionValue(@PathVariable Long optionValueId){
        optionService.deleteOptionValue(optionValueId);
        return new SuccessResponse();
    }

    @DeleteMapping("/{productId}")
    public ApiResponse deleteProduct(@PathVariable Long productId){
        productService.deleteProduct(productId);
        return new SuccessResponse();
    }

    @GetMapping("/view-variants/{productId}")
    public ApiResponse viewVariantsByProduct(@PathVariable Long productId){
        return new SuccessResponse(productService.viewVariantsByProduct(productId));
    }

}
