package io.dodn.springboot.storage.db.core.entity.orderproduct;

import io.dodn.springboot.storage.db.core.BaseEntity;
import io.dodn.springboot.storage.db.core.entity.order.OrderEntity;
import io.dodn.springboot.storage.db.core.entity.product.ProductEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
    private ProductEntity productEntity;


    @ManyToOne(fetch = FetchType.LAZY)
    private OrderEntity orderEntity;





    private OrderProductEntity(ProductEntity product, OrderEntity orderEntity) {
        this.productEntity =product;
        this.orderEntity = orderEntity;
    }

    public static OrderProductEntity of(ProductEntity product, OrderEntity orderEntity) {
    return  OrderProductEntity.builder()
        .productEntity(product)
        .orderEntity(orderEntity)
        .build();
    }



}
