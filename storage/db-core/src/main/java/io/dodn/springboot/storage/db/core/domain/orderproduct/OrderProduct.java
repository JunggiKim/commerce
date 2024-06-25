package io.dodn.springboot.storage.db.core.domain.orderproduct;

import io.dodn.springboot.storage.db.core.BaseEntity;
import io.dodn.springboot.storage.db.core.domain.order.Order;
import io.dodn.springboot.storage.db.core.domain.product.Product;
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
public class OrderProduct extends BaseEntity{


    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;


    private OrderProduct(Order order, Product product) {
        this.order = order;
        this.product =product;
    }

    public static OrderProduct of(Order order, Product product) {
    return  OrderProduct.builder()
        .order(order)
        .product(product)
        .build();
    }



}
