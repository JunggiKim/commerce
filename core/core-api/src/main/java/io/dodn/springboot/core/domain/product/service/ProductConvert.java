package io.dodn.springboot.core.domain.product.service;


import io.dodn.springboot.core.domain.product.Product;
import io.dodn.springboot.storage.db.core.entity.product.dto.OrderCreateProductResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductConvert {


    public List<Product> toProductDomainList(List<OrderCreateProductResponse> orderCreateProductResponseList) {
        return orderCreateProductResponseList.stream()
                .map(ProductConvert::toProductDomain)
                .toList();


    }

    public static Product toProductDomain(OrderCreateProductResponse orderCreateProductResponse) {
        return Product.builder()
                .productNumber(orderCreateProductResponse.productNumber())
                .type(orderCreateProductResponse.type())
                .sellingStatus(orderCreateProductResponse.sellingStatus())
                .name(orderCreateProductResponse.name())
                .price(orderCreateProductResponse.price())
                .quantity(orderCreateProductResponse.quantity())
                .build();

    }

    public static Product toProductDomain(OrderCreateProductResponse orderCreateProductResponse) {
        return Product.builder()
                .productNumber(orderCreateProductResponse.productNumber())
                .type(orderCreateProductResponse.type())
                .sellingStatus(orderCreateProductResponse.sellingStatus())
                .name(orderCreateProductResponse.name())
                .price(orderCreateProductResponse.price())
                .quantity(orderCreateProductResponse.quantity())
                .build();

    }
}
