package io.dodn.springboot.core.domain.order;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import io.dodn.springboot.core.domain.product.Product;
import io.dodn.springboot.core.domain.orderproduct.OrderProduct;
import io.dodn.springboot.core.enums.OrderStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order  {

    private Long id;

    private Long orderNumber;

    private OrderStatus orderStatus;

    private int totalPrice;

    private LocalDateTime registeredDateTime;

    private String userEmail;


    private List<OrderProduct> orderProducts = new ArrayList<>();

    @Builder
    private Order(Long orderNumber, String userEmail  , List<Product> products, OrderStatus orderStatus, LocalDateTime registeredDateTime) {
        this.orderNumber = orderNumber;
        this.orderStatus = orderStatus;
        this.totalPrice = calculateTotalPrice(products);
        this.registeredDateTime = registeredDateTime;
        this.orderProducts = products.stream()
                .map(product -> OrderProduct.of(this , product))
                .collect(Collectors.toList());
        this.userEmail = userEmail;
    }


    public static Order create(List<Product>  products , LocalDateTime registeredDateTime,String userEmail) {
    return Order.builder()
            .orderStatus(OrderStatus.INIT)
            .products(products)
            .registeredDateTime(registeredDateTime)
            .userEmail( userEmail)
            .build();
    }

    public static Order create(List<Product>  products , LocalDateTime registeredDateTime) {
    return Order.builder()
            .orderStatus(OrderStatus.INIT)
            .products(products)
            .registeredDateTime(registeredDateTime)
            .build();
    }


    // private static int calculateTotalPrice(List<Product> products) {
    //     return products.stream()
    //             .map(Product::getPrice)
    //             .sum();
    // }


}
