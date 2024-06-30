package io.dodn.springboot.core.domain.orderproduct;



import io.dodn.springboot.core.domain.order.Order;
import io.dodn.springboot.core.domain.product.Product;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access =  AccessLevel.PROTECTED)
// @Builder
public class OrderProduct {

    private Long id;

    private Order order;

    private Product product;

    @Builder
    private OrderProduct(Long id, Product product,Order order) {
        this.id = id;
        this.product = product;
        this.order = order;
    }

    private OrderProduct(Product product, Order order) {
        this.product = product;
        this.order = order;
    }







    public static OrderProduct of(Product product,Order order) {
    return  OrderProduct.builder()
        .product(product)
        .order(order)
        .build();
    }



}
