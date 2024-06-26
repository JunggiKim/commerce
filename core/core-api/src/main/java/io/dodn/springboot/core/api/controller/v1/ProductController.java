package io.dodn.springboot.core.api.controller.v1;


import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.dodn.springboot.core.api.domain.product.request.ProductCreateRequest;
import io.dodn.springboot.core.api.domain.product.response.ProductResponse;
import io.dodn.springboot.core.api.support.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/api/v1/products/new")
    public ApiResponse<ProductResponse> createProduct(@Valid @RequestBody ProductCreateRequest request){
        return ApiResponse.success(productService.createProduct(request.toServiceRequest()));
    }

    @GetMapping("/api/v1/products/selling")
    public ApiResponse<List<ProductResponse>> getSellingProducts(){
        return ApiResponse.success(productService.getSellingProducts());
    }


}
