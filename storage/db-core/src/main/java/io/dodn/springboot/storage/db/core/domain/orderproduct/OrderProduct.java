package io.dodn.springboot.storage.db.core.domain.orderproduct;

import io.dodn.springboot.storage.db.core.BaseEntity;
import io.dodn.springboot.storage.db.core.domain.order.Order;
import io.dodn.springboot.storage.db.core.domain.order.OrderEntity;
import io.dodn.springboot.storage.db.core.domain.product.ProductEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access =  AccessLevel.PROTECTED)
@Builder
public class OrderProductEntity extends BaseEntity{


    @ManyToOne(fetch = FetchType.LAZY)
    private OrderEntity orderEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    private ProductEntity productEntity;


    private OrderProductEntity(OrderEntity orderEntity, ProductEntity product) {
        this.orderEntity = orderEntity;
        this.productEntity =product;
    }

    public static OrderProductEntity of(OrderEntity orderEntity, ProductEntity product) {
    return  OrderProductEntity.builder()
        .orderEntity(orderEntity)
        .productEntity(product)
        .build();
    }



}
