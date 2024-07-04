package io.dodn.springboot.core.domain.orderproduct;

import io.dodn.springboot.core.domain.order.Order;
import io.dodn.springboot.core.domain.product.Product;
import io.dodn.springboot.core.enums.ProductType.ProductSellingStatus;
import io.dodn.springboot.core.enums.ProductType.ProductType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderProduct {

    private Long id;

    private Long orderId;

    private Long productId;

    private ProductType type;

    private String name;

    private BigDecimal price;

    @Builder
    private OrderProduct(Long id, Long orderId, Long productId, ProductType type, String name, BigDecimal price) {
        this.id = id;
        this.productId = productId;
        this.type = type;
        this.name = name;
        this.price = price;
        this.orderId = orderId;
    }

    public static OrderProduct of( Long productId, ProductType type, String name,
            BigDecimal price) {
        return OrderProduct.builder()
            .productId(productId)
            .type(type)
            .name(name)
            .price(price)
            .build();
    }

}
