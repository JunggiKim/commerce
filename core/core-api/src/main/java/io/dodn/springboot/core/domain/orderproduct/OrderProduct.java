package io.dodn.springboot.core.domain.orderproduct;


import io.dodn.springboot.core.domain.order.Order;
import io.dodn.springboot.core.domain.product.Product;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access =  AccessLevel.PROTECTED)
@Builder
public class OrderProduct {

    private Long id;

    private Product product;



    private OrderProduct(Long id, Product product) {
        this.id = id;
        this.product = product;
    }

    private OrderProduct(Product product) {
        this.product =product;
    }

    public static OrderProduct of(Order order, Product product) {
    return  OrderProduct.builder()
        .order(order)
        .product(product)
        .build();
    }



}
