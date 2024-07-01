package io.dodn.springboot.storage.db.core.entity.orderproduct;

import io.dodn.springboot.core.enums.ProductType.ProductType;
import io.dodn.springboot.storage.db.core.BaseEntity;
import io.dodn.springboot.storage.db.core.entity.order.OrderEntity;
import io.dodn.springboot.storage.db.core.entity.product.ProductEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Getter
@NoArgsConstructor(access =  AccessLevel.PROTECTED)
public class OrderProductEntity extends BaseEntity{



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long orderId;

    private Long productId;

    private ProductType type;

    private String name;

    private Long price;



    @Builder
    private OrderProductEntity(
            Long productId,
            ProductType type,
            String name,
            Long price,
            Long orderId
    ) {
        this.orderId = orderId;
        this.productId = productId;
        this.type= type;
        this.name = name;
        this.price = price;

    }

    public static OrderProductEntity of(
            Long productId,
            ProductType type,
            String name,
            Long price,
            Long orderId
    ) {
    return  OrderProductEntity.builder()
            .orderId(orderId)
            .productId(productId)
            .type(type)
            .name(name)
            .price(price)
        .build();
    }



}
