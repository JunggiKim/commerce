package io.dodn.springboot.storage.db.core.entity.product.dto;

import io.dodn.springboot.core.enums.ProductType.ProductSellingStatus;
import io.dodn.springboot.core.enums.ProductType.ProductType;
import io.dodn.springboot.storage.db.core.entity.product.ProductEntity;

import java.util.List;

public record OrderCreateProductResponse(

        int productNumber,

        ProductType type,

        ProductSellingStatus sellingStatus,

        String name,

        int price,

        int quantity
) {


    public static OrderCreateProductResponse of(ProductEntity productEntity) {
        return new OrderCreateProductResponse(
                productEntity.getProductNumber(),
                productEntity.getType(),
                productEntity.getSellingStatus(),
                productEntity.getName(),
                productEntity.getPrice(),
                productEntity.getQuantity()
        );
    }


    public static List<OrderCreateProductResponse> toList(List<ProductEntity> ProductEntitys) {
        return ProductEntitys.stream().map(OrderCreateProductResponse::of).toList();
    }
}
